package department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class DepartmentHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DepartmentForm dForm = (DepartmentForm) form;
		DepartmentManager dMan = new DepartmentManager();
		EmployeeManager eMan = new EmployeeManager();

		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		request.setAttribute("username", us.getUsername());

		CommonFunction.createAllowedMenu(us, request);

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);
			request.setAttribute("lstDeptHead",
					eMan.getAllEmployeeForDeptHead(dForm.getSelectedId(),"",""));
			request.setAttribute("pageTitle", "Department Entry");
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			request.setAttribute("lstDeptHead",
					eMan.getAllEmployeeForDeptHead(dForm.getSelectedId(),"",""));
			request.setAttribute("pageTitle", "Department Entry");

			dForm.setSelectedDept(dMan.getDepartmentByDeptId(dForm
					.getSelectedId()));
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("delete")) {
			dMan.deleteDepartment(dForm.getSelectedId(), us.getUserId());
		}

		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (dForm.getSelectedDept().getDeptHeadId() == 0)
				dForm.getSelectedDept().setDeptHeadId(null);

			if (isAdd) {
				dForm.getSelectedDept().setCreatedBy(us.getUserId());
				dMan.insertDepartment(dForm.getSelectedDept());
			} else {
				dForm.getSelectedDept().setUpdatedBy(us.getUserId());
				dMan.updateDepartment(dForm.getSelectedDept());
			}

			response.sendRedirect("department.do");
			return null;
		}

		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());

		int rowCount;

		dForm.setArrList(dMan.getAllDepartmentsFiltered(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.pageSize));
		rowCount = dMan.getCountDepartment(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue());
		//
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Department List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}