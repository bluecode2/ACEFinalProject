package rank_employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		//CommonFunction.createAllowedMenu(us, request);

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);

			request.setAttribute("pageTitle", "Employee Rank Entry");
			
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK_ENTRY,
					us, request);
			
			return mapping.findForward("employeeRankEntry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			
			request.setAttribute("pageTitle", "Employee Rank Entry");

			dForm.setBean(dMan.getRankByRankId(dForm.getSelectedId()));
			CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK_ENTRY,
					us, request);
			return mapping.findForward("employeeRankEntry");
		}

		else if (dForm.getTask().equals("delete")) {
			dForm.getBean().setUpdatedBy(us.getUserId());
			dForm.getBean().setRankId(dForm.getSelectedId());
			dMan.deleteEmployeeRank(dForm.getBean());
		}

		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (isAdd) {
				System.out.println("aa");
				dForm.getBean().setCreatedBy(us.getUserId());
				dMan.insertEmployeeRank(dForm.getBean());
			} else {
				dForm.getBean().setUpdatedBy(us.getUserId());
				dMan.updateEmployeeRank(dForm.getBean());
			}

			response.sendRedirect("rankEmployee.do");
			return null;
		}

		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());

		int rowCount;

		dForm.setArrList(dMan.getAllEmployeeRank(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.pageSize));
		rowCount = dMan.getCountRankEmp(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue());
		
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		CommonFunction.initializeHeader(Constant.MenuCode.EMPLOYEE_RANK,
				us, request);
		
		request.setAttribute("pageTitle", "Employee Rank List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("employeeRankList");
	}
}