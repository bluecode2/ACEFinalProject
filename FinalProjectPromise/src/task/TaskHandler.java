package task;

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

public class TaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		TaskForm tsForm = (TaskForm) form;
		TaskManager tsMan = new TaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		CommonFunction.createAllowedMenu(us, request);
		
		
		tsForm.setTask("");
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		
		tsForm.setListCount(tsMan.getCountAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount() / (double) Constant.pageSize));
		
		tsForm.setArrList(tsMan.getListAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm.getCurrPage(), Constant.pageSize));

		request.setAttribute("pageTitle", "General Code List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(tsForm.getPageCount(), tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("list");
	}
}
