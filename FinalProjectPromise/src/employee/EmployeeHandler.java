package employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import rank_employee.RankEmpManager;
import user.UserBean;
import user.UserManager;
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
		RankEmpManager reMan = new RankEmpManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		UserManager usMan = new UserManager();
		
		if (eForm.getTask().equals("add")) {
			eForm.setIsAdd(true);
			eForm.setSelectedId(0);
			eForm.getSelectedEmp().setGender("M");
			request.setAttribute("pageTitle", "Employee Entry");
			request.setAttribute("listRank",  reMan.getListRankForSearch("", ""));
			request.setAttribute("listOfDepartment",dMan.getListDepartmentForSearchDialog("",""));
			request.setAttribute("listOfSupervisor", eMan.getListEmployeeForSupervisor(eForm.getSelectedEmp().getDeptId(),eForm.getSelectedEmp().getRankLevel(),"",""));
			
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_ENTRY,
					us, request);
			
			return mapping.findForward("entry");
		}
		else if (eForm.getTask().equals("edit")) {
			eForm.setIsAdd(false);
			
			eForm.setSelectedEmp(eMan.getEmployeeByEmpId(eForm
					.getSelectedId()));
			
			eForm.setSelectedRankId(eForm.getSelectedEmp().getRankId());
			request.setAttribute("listRank",  reMan.getListRankForSearch("",""));
			request.setAttribute("listOfDepartment",dMan.getListDepartmentForSearchDialog("",""));
			request.setAttribute("listOfSupervisor", eMan.getListEmployeeForSupervisor(eForm.getSelectedEmp().getDeptId() ,eForm.getSelectedEmp().getRankLevel(),"",""));
			
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_ENTRY, us, request);
			return mapping.findForward("entry");
		}
		else if (eForm.getTask().equals("delete")) {
			usMan.deleteUserByEmpId(eForm.getSelectedId(),us.getUserId());
			if(eMan.deleteEmployee(eForm.getSelectedId(),us.getUserId())){
				session.setAttribute("validationMessage",
						"Succeed to Delete Employee!");
				session.setAttribute("validationType", "success");
			}
			else{
				session.setAttribute("validationMessage", "Failed to Delete Employee " + eForm.getSelectedEmp().getEmployeeName() + "!");
				session.setAttribute("validationType", "danger");
			}
		}
		else if (eForm.getTask().equals("save")) {
			Boolean isAdd = eForm.getIsAdd();
			
			eForm.getSelectedEmp().setRankId(eForm.getSelectedRankId());
			
			if(eForm.getSelectedEmp().getSupervisorId()==0){
				eForm.getSelectedEmp().setSupervisorId(null);
			}
				
			if (isAdd) 
			{
				eForm.getSelectedEmp().setCreatedBy(us.getUserId());
				if(eMan.insertEmployee(eForm.getSelectedEmp())){
					session.setAttribute("validationMessage",
							"Succeed to Add New Employee!");
					session.setAttribute("validationType", "success");
				}
				else{
					session.setAttribute("validationMessage", "Failed to Add New Employee!");
					session.setAttribute("validationType", "danger");
				}
			} 
			else 
			{
				eForm.getSelectedEmp().setUpdatedBy(us.getUserId());
				if(eMan.updateEmployee(eForm.getSelectedEmp())){
					session.setAttribute("validationMessage",
							"Succeed to Edit Employee " + eForm.getSelectedEmp().getEmployeeName() + "!");
					session.setAttribute("validationType", "success");
				}
				else{
					session.setAttribute("validationMessage", "Failed to Edit Employee " + eForm.getSelectedEmp().getEmployeeName() + "!");
					session.setAttribute("validationType", "danger");
				}
			}
			
			response.sendRedirect("employee.do");
			return null;
		}
		
		eForm.setTask("");
		eForm.setSearchField(eForm.getCurrSearchField());
		eForm.setSearchValue(eForm.getCurrSearchValue());

		int rowCount;

		eForm.setListOfEmployee(eMan.getAllEmployee(
				eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
				eForm.getCurrPage(), Constant.PAGE_SIZE));
		rowCount = eMan.getCountEmployee(eForm.getCurrSearchField(),
				eForm.getCurrSearchValue());
		
		eForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE,
				us, request);

		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
	}
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(eForm.getPageCount(),
						eForm.getCurrPage()));

		request.setAttribute("pageCount", eForm.getPageCount());
		request.setAttribute("currPage", eForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}
