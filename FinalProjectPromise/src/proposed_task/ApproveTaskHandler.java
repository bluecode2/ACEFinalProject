package proposed_task;

import independent_task.IndependentTaskBean;
import independent_task.IndependentTaskManager;

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

public class ApproveTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ApproveTaskForm aForm = (ApproveTaskForm) form;
		ApproveTaskManager aManager = new ApproveTaskManager();
		IndependentTaskManager atManager = new IndependentTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		NotificationManager noMan = new NotificationManager();
		
		CommonFunction.initializeHeader(Constant.MenuCode.APPROVE_PROPOSED_INDEPENDENT_TASK, us, request);

		aForm.setEmpId(us.getEmployeeId());
		if (aForm.getTask().equals("approve")) {
			aForm.setBean(aManager.getApproveTaskById(aForm.getSelectedId()));
			
			//Insert new Task
			IndependentTaskBean taskBean = new IndependentTaskBean();
			taskBean.setTaskName(aForm.getBean().getPropTaskName());
			taskBean.setTaskDesc(aForm.getBean().getPropTaskDesc());
			taskBean.setEstStartDate(aForm.getBean().getEstStartDate());
			taskBean.setEstEndDate(aForm.getBean().getEstEndDate());
			taskBean.setAssignedBy(aForm.getBean().getPropTo());
			taskBean.setAssignedTo(aForm.getAssignTo());
			taskBean.setCreatedBy(us.getUserId());
			
			atManager.createNewAssignTask(taskBean);
			noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), aForm.getBean().getPropBy(), aForm.getBean().getPropTaskId());
			
			//Update Proposed Task
			aForm.getBean().setUpdatedBy(us.getUserId());
			aForm.getBean().setTaskId(taskBean.getTaskId());
			aManager.approveTask(aForm.getBean());
			noMan.createNotificationProposeIndependentTask(us.getEmployeeId(), aForm.getBean().getPropBy(), aForm.getBean().getPropTaskId());
			
			response.sendRedirect("approveTask.do");
			return null;
		}
		else if (aForm.getTask().equals("decline")) {
			aForm.setIsAdd(false);

			aForm.getBean().setPropTaskId(aForm.getSelectedId());
			aManager.addRemarksProposedTask(us.getUserId(), aForm.getSelectedId(), aForm.getRemarksRecord());
			noMan.createNotificationProposeIndependentTask(us.getEmployeeId(), aForm.getBean().getPropBy(), aForm.getBean().getPropTaskId());
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
		
		aForm.seteBean(aManager.getEmployeeBySpvId(us.getEmployeeId()));
		
		aForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(aForm.getPageCount(),
						aForm.getCurrPage()));

		request.setAttribute("pageCount", aForm.getPageCount());
		request.setAttribute("currPage", aForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("approveTaskList");
	}
}
