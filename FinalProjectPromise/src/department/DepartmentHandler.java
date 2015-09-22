package department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.crystaldecisions.a.d;

import user.UserBean;
import common.CommonFunction;
import common.Constant;
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


		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);
			request.setAttribute("lstDeptHead",
					eMan.getAllEmployeeForDeptHead(dForm.getSelectedId(),"",""));
				
			CommonFunction.initializeHeader(Constant.MenuCode.DEPARTMENT_ENTRY,
					us, request);
			
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			request.setAttribute("lstDeptHead",
					eMan.getAllEmployeeForDeptHead(dForm.getSelectedId(),"",""));
		
			dForm.setSelectedDept(dMan.getDepartmentByDeptId(dForm
					.getSelectedId()));
			
			CommonFunction.initializeHeader(Constant.MenuCode.DEPARTMENT_ENTRY,
					us, request);
			
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("delete")) {
			
			if(!dMan.deleteDepartment(dForm.getSelectedId(), us.getUserId())){
				session.setAttribute("validationMessage",
						"Failed To Delete Department!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Delete Department!");
				session.setAttribute("validationType", "success");
			}

		}

		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();
			
			if (isAdd) {
				dForm.getSelectedDept().setCreatedBy(us.getUserId());
				if(!dMan.insertDepartment(dForm.getSelectedDept())){
					session.setAttribute("validationMessage",
							"Failed To Add New Department!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Add New Department!");
					session.setAttribute("validationType", "success");
				}
			} else {
				if (dForm.getSelectedDept().getDeptHeadId() == 0)
					dForm.getSelectedDept().setDeptHeadId(null);
				
				dForm.getSelectedDept().setUpdatedBy(us.getUserId());
				dMan.updateDepartment(dForm.getSelectedDept());
				
				if(!dMan.updateDepartment(dForm.getSelectedDept())){
					session.setAttribute("validationMessage",
							"Failed To Edit Department!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Edit Department!");
					session.setAttribute("validationType", "success");
				}
			}

			response.sendRedirect("department.do");
			CommonFunction.initializeHeader(Constant.MenuCode.DEPARTMENT,
					us, request);
			return null;
		}

		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());

		int rowCount;

		dForm.setArrList(dMan.getAllDepartmentsFiltered(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.PAGE_SIZE));
		rowCount = dMan.getCountDepartment(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue());
		
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.DEPARTMENT,
				us, request);

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}