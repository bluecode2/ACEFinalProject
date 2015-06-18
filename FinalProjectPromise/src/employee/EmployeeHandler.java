package employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;
import department.DepartmentManager;

public class EmployeeHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		EmployeeForm eForm = (EmployeeForm) form;
		DepartmentManager dMan = new DepartmentManager();
		EmployeeManager eMan = new EmployeeManager();

		HttpSession session = request.getSession(true);
		
		CommonFunction.createAllowedMenu(null, request);
		
		if (eForm.getTask().equals("add")) {
			eForm.setIsAdd(true);
			eForm.setSelectedId(0);
			
			return mapping.findForward("entry");
		}

		else if (eForm.getTask().equals("edit")) {
			eForm.setIsAdd(false);
			
			request.setAttribute("pageTitle", "Department Entry");

			eForm.setSelectedEmp(eMan.getEmployeeByEmpId(eForm
					.getSelectedId()));
			return mapping.findForward("entry");
		}

		else if (eForm.getTask().equals("delete")) {
			dMan.deleteDepartment(eForm.getSelectedId(), 1);
		}

//		else if (eForm.getTask().equals("save")) {
//			Boolean isAdd = eForm.getIsAdd();
//
//			if (isAdd) {
//				eForm.getSelectedDept().setCreatedBy(1);
//				dMan.insertDepartment(eForm.getSelectedDept());
//			} else {
//				eForm.getSelectedDept().setUpdatedBy(1);
//				dMan.updateDepartment(eForm.getSelectedDept());
//			}
//
//			response.sendRedirect("department.do");
//			return null;
//		}
		
		eForm.setTask("");
		eForm.setSearchField(eForm.getCurrSearchField());
		eForm.setSearchValue(eForm.getCurrSearchValue());

		int rowCount;

		eForm.setListOfEmployee(eMan.getAllEmployee(
				eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
				eForm.getCurrPage(), Constant.pageSize));
		rowCount = eMan.getCountEmployee(eForm.getCurrSearchField(),
				eForm.getCurrSearchValue());
		//
		eForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Employee List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(eForm.getPageCount(),
						eForm.getCurrPage()));

		request.setAttribute("pageCount", eForm.getPageCount());
		request.setAttribute("currPage", eForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}
