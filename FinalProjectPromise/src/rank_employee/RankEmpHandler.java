package rank_employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.crystaldecisions.a.d;

import common.CommonFunction;
import common.Constant;
import rank_employee.RankEmpManager;
import user.UserBean;

public class RankEmpHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RankEmpForm dForm = (RankEmpForm) form;
		RankEmpManager dMan = new RankEmpManager();
		HttpSession session = request.getSession(true);	
		UserBean us = (UserBean) session.getAttribute("currUser");

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);

			
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK_ENTRY,
					us, request);
			
			return mapping.findForward("employeeRankEntry");
		}
		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			

			dForm.setBean(dMan.getRankByRankId(dForm.getSelectedId()));
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK_ENTRY,
					us, request);
			return mapping.findForward("employeeRankEntry");
		}
		else if (dForm.getTask().equals("delete")) {
			dForm.getBean().setUpdatedBy(us.getUserId());
			dForm.getBean().setRankId(dForm.getSelectedId());
			
			if(!dMan.deleteEmployeeRank(dForm.getBean())){
				session.setAttribute("validationMessage", "Failed To Delete Employee Rank " + dForm.getBean().getRankName() + "!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Delete Employee Rank !");
				session.setAttribute("validationType", "success");
			}
		}
		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (isAdd) {
				dForm.getBean().setCreatedBy(us.getUserId());
				if(!dMan.insertEmployeeRank(dForm.getBean())){
					session.setAttribute("validationMessage",
							"Failed To Add New Employee Rank!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Add New Employee Rank!");
					session.setAttribute("validationType", "success");
				}

			} else {
				dForm.getBean().setUpdatedBy(us.getUserId());
				if(!dMan.updateEmployeeRank(dForm.getBean())){
					session.setAttribute("validationMessage", "Failed To Edit Employee Rank!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Edit Employee Rank!");
					session.setAttribute("validationType", "success");
				}
				
			}

			response.sendRedirect("rankEmployee.do");
			return null;
		}
		
		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());
		dForm.setIsComplete(0);
		int rowCount;

		dForm.setArrList(dMan.getAllEmployeeRank(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.PAGE_SIZE));
		rowCount = dMan.getCountRankEmp(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue());
		
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK,
				us, request);
		
		if(session.getAttribute("validationMessage") != null){
				request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
				request.setAttribute("validationType", session.getAttribute("validationType").toString());
				session.removeAttribute("validationMessage");
				session.removeAttribute("validationType");
		}
		

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("employeeRankList");
	}
}