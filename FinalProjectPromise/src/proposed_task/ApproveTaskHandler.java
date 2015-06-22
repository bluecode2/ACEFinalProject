package proposed_task;

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

public class ApproveTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ApproveTaskForm aForm = (ApproveTaskForm) form;
		ApproveTaskManager aManager = new ApproveTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		CommonFunction.createAllowedMenu(us, request);

		if (aForm.getTask().equals("approve")) {
			request.setAttribute("pageTitle", "Approve Task List");
			
			aForm.getBean().setUpdatedBy(us.getUserId());
			aForm.getBean().setPropTaskId(aForm.getSelectedId());
			aManager.approveTask(aForm.getBean());
			
			response.sendRedirect("approveTask.do");
			return null;
		}
		else if (aForm.getTask().equals("decline")) {
			aForm.setIsAdd(false);
			System.out.println(aForm.getTask());
			request.setAttribute("pageTitle", "Approve Task List");

			aForm.getBean().setUpdatedBy(us.getUserId());
			aForm.getBean().setPropTaskId(aForm.getSelectedId());
			aManager.declineTask(aForm.getBean());

			response.sendRedirect("approveTask.do");
			return null;
		}

		aForm.setTask("");
		aForm.setSearchField(aForm.getCurrSearchField());
		aForm.setSearchValue(aForm.getCurrSearchValue());

		int rowCount;
		
		aForm.setArrList(aManager.getListApproveTask(
				aForm.getCurrSearchField(), aForm.getCurrSearchValue(),
				aForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));
		rowCount = aManager.getCountApproveTask(aForm.getCurrSearchField(),
				aForm.getCurrSearchValue(), us.getEmployeeId());
		
		aForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Approve Task List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(aForm.getPageCount(),
						aForm.getCurrPage()));

		request.setAttribute("pageCount", aForm.getPageCount());
		request.setAttribute("currPage", aForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("approveTaskList");
	}
}
