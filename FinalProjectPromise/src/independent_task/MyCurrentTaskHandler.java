package independent_task;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notification.NotificationManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;
import common.CommonFunction;
import common.Constant;

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
		tsForm.getTkBean().setAssignedBy(us.getEmployeeId());
		NotificationManager noMan = new NotificationManager();
		Date now = new Date();
		
		if ("start".equals(tsForm.getTask())) {

			
			if(!tsMan.startMyCurrentTask(tsForm.getSelectedId(), us.getUserId(), Constant.GeneralCode.TASK_STATUS_ONGOING)){
				session.setAttribute("validationMessage", "Failed To Start Task!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Start Task!");
				session.setAttribute("validationType", "success");
			}
		}
		else if ("pause".equals(tsForm.getTask())) {
			
			if(!tsMan.updateStatusMyCurrentTask(tsForm.getSelectedId(), us.getUserId(), Constant.GeneralCode.TASK_STATUS_ON_HOLD)){
				session.setAttribute("validationMessage", "Failed To Pause Task!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Pause Task!");
				session.setAttribute("validationType", "success");
			}
			tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
			noMan.createNotificationPauseIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedBy(), tsForm.getSelectedId());
		}
		else if ("submit".equals(tsForm.getTask())) {
			tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
			tsForm.getTkBean().setTaskStatus(Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL);
			tsForm.getTkBean().setUpdatedBy(us.getUserId());
			tsForm.getTkBean().setActEndDate(now);
			
			if(!tsMan.updateStatusMyCurrentTaskToWaitingApproval(tsForm.getTkBean())){
				session.setAttribute("validationMessage", "Failed To Submit Task!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Submit Task!");
				session.setAttribute("validationType", "success");
			}
			tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
			noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedBy(), tsForm.getTkBean().getTaskId());
		}
	
		CommonFunction.initializeHeader(Constant.MenuCode.CURRENT_TASK_LIST, us, request);
		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
	}
		tsForm.setTask("");
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		
		tsForm.setListCount(tsMan.getCountMyCurrentTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),us.getEmployeeId()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount() / (double) Constant.PAGE_SIZE));
		
		tsForm.setArrList(tsMan.getListMyCurrentTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm.getCurrPage(), Constant.PAGE_SIZE, us.getEmployeeId()));

		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(tsForm.getPageCount(), tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("myCurrentTaskList");
	}
}
