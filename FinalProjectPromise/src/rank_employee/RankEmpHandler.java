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

import rank_employee.RankEmpBean;
import rank_employee.RankEmpManager;

public class RankEmpHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RankEmpForm dForm = (RankEmpForm) form;
		RankEmpManager dMan = new RankEmpManager();
		HttpSession session = request.getSession(true);

		CommonFunction.createAllowedMenu(null, request);

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);

			request.setAttribute("pageTitle", "Employee Rank Entry");
			return mapping.findForward("employeeRankEntry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			
			request.setAttribute("pageTitle", "Employee Rank Entry");

			dForm.setBean(dMan.getRankByRankId(dForm.getSelectedId()));

			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("delete")) {
			dMan.deleteEmployeeRank(dForm.getSelectedId());
		}

		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (isAdd) {
				dForm.getBean().setCreatedBy(1);
				dMan.insertEmployeeRank(dForm.getBean());
			} else {
				dForm.getBean().setUpdatedBy(1);
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