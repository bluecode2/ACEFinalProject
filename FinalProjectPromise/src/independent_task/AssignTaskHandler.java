package independent_task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notification.NotificationManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import activity.ActivityBean;
import activity.ActivityManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeManager;

public class AssignTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		AssignTaskForm tsForm = (AssignTaskForm) form;
		IndependentTaskManager tsMan = new IndependentTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		NotificationManager noMan = new NotificationManager();
		tsForm.getTkBean().setAssignedBy(us.getEmployeeId());
		ActivityManager actMan = new ActivityManager();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		tsForm.setDateInString(sdf.format(now));
		
		if ("add".equals(tsForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_ENTRY,us, request);
			tsForm.setIsAdd(true);
		
			request.setAttribute("listAssignTo", empMan.getEmpForAssignTask(us.getEmployeeId(),"",""));
			return mapping.findForward("assignTaskEntry");
		}

		else if ("save".equals(tsForm.getTask()	)) {
			Boolean isAdd = tsForm.getIsAdd();

			if (tsForm.getTkBean().getAssignedTo() == 0)
				tsForm.getTkBean().setAssignedTo(null);

			if (isAdd) {
				tsForm.getTkBean().setCreatedBy(us.getUserId());
				if (tsMan.createNewAssignTask(tsForm.getTkBean())){
				noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedTo(), tsForm.getTkBean().getTaskId());
				session.setAttribute("validationMessage",
						"Succeed to Add New Assign Task!");
				session.setAttribute("validationType", "success");
				}
				else {
					session.setAttribute("validationMessage", "Failed to Add New Assign Task!");
					session.setAttribute("validationType", "danger");
				}
			} else {
				tsForm.getTkBean().setUpdatedBy(us.getUserId());
				if (tsMan.editAssignTask(tsForm.getTkBean().getTaskId(), tsForm.getTkBean().getTaskName(), tsForm.getTkBean().getTaskDesc(), tsForm.getTkBean().getUpdatedBy())){
					session.setAttribute("validationMessage",
							"Succeed to Edit Task : " + tsForm.getTkBean().getTaskName() + "!");
					session.setAttribute("validationType", "success");
				}
				else {
					session.setAttribute("validationMessage", "Failed to Edit Task : " + tsForm.getTkBean().getTaskName() + "!");
					session.setAttribute("validationType", "danger");
				}
				
			}
			response.sendRedirect("assignTask.do");
			return null;
		}
		else if ("firstEdit".equals(tsForm.getTask())) {
			tsForm.setIsAdd(false);
			
			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
				CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_ENTRY,us, request);
				request.setAttribute("listAssignTo", empMan.getEmpForAssignTask(us.getEmployeeId(),"",""));
				return mapping.findForward("assignTaskEntry");
			}
			
			else if (tsForm.getSelectedEdit() == 1) { //APPROVED
				tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_APPROVE);
				tsMan.editStatusAssignTaskApprove(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),"");
				tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
				noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedTo(), tsForm.getTkBean().getTaskId());
			}	
		}
		else if ("secondEdit".equals(tsForm.getTask())) {
			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_CANCELLED);
				tsMan.editStatusAssignTask(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),tsForm.getRemarksRecord());
				tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
				noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedTo(), tsForm.getTkBean().getTaskId());
			}
			else if (tsForm.getSelectedEdit() == 1) {
				tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_ONGOING);
				tsMan.editStatusAssignTask(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),tsForm.getRemarksRecord());
				tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
				noMan.createNotificationAssignIndependentTask(us.getEmployeeId(), tsForm.getTkBean().getAssignedTo(), tsForm.getTkBean().getTaskId());
			}

		}
		else if ("listActivity".equals(tsForm.getTask())) {
			int selId = tsForm.getSelectedId();
			tsForm.setArrActivity(actMan.getActivityByTaskId(selId));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ActivityBean> arrActivity = tsForm.getArrActivity();

			for (ActivityBean actBean : arrActivity) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + actBean.getActivityDesc() + "</td>");
				if (actBean.getIsCompleted() == 1) {
					out.println("<td align=\"center\"> <input type=\"checkbox\" checked disabled> </td>");					
				}
				else {
					out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");		
				}

				out.println("</tr>");
			}	

			out.flush();
			return null;
		}

		CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_LIST,
				us, request);
		
		tsForm.setTask("");
		
		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
	}
		
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		
		tsForm.setListCount(tsMan.getCountAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),us.getEmployeeId()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount() / (double) Constant.PAGE_SIZE));
		
		tsForm.setArrList(tsMan.getListAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm.getCurrPage(), Constant.PAGE_SIZE, us.getEmployeeId()));

		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(tsForm.getPageCount(), tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("assignTaskList");
	}
}
