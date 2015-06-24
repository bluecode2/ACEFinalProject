package independent_task;

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
import employee.EmployeeManager;

public class MyCurrentTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		MyCurrentTaskForm tsForm = (MyCurrentTaskForm) form;
		IndependentTaskManager tsMan = new IndependentTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		tsForm.getTkBean().setAssignedBy(us.getEmployeeId());

		if ("start".equals(tsForm.getTask())) {
			tsMan.updateStatusMyCurrentTask(tsForm.getSelectedId(), us.getUserId(), "TA_STAT_03");
		}
		else if ("pause".equals(tsForm.getTask())) {
			tsMan.updateStatusMyCurrentTask(tsForm.getSelectedId(), us.getUserId(), "TA_STAT_06");
		}
		else if ("submit".equals(tsForm.getTask())) {
			tsMan.updateStatusMyCurrentTask(tsForm.getSelectedId(), us.getUserId(), "TA_STAT_04");
		}
	
		CommonFunction.initializeHeader(Constant.MenuCode.CURRENT_TASK_LIST,
				us, request);
		
		tsForm.setTask("");
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		
		tsForm.setListCount(tsMan.getCountMyCurrentTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),us.getEmployeeId()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount() / (double) Constant.pageSize));
		
		tsForm.setArrList(tsMan.getListMyCurrentTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));

		request.setAttribute("pageTitle", "Current Task List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(tsForm.getPageCount(), tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("myCurrentTaskList");
		
		
	}
}
