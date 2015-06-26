package project_task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import project_member.ProjectMemberManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeManager;

public class ProjectInvolvedTaskHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		ProjectTaskForm tsForm = (ProjectTaskForm) form;
		ProjectTaskManager tsMan = new ProjectTaskManager();
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		ProjectManager projManager = new ProjectManager();
		ProjectMemberManager projMbrMgr = new ProjectMemberManager();
		request.setAttribute("user", us.getEmployeeId());
		tsForm.setEmpId(us.getEmployeeId());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ProjectTaskBean pTaskBean = new ProjectTaskBean();
		
		if (tsForm.getPrjBean() == null) {
			if (session.getAttribute("projectId") != null) {
				Integer projectId = (Integer) session.getAttribute("projectId");
				tsForm.setPrjBean(projManager.getProjectByID(projectId));
				tsForm.setProjectId(projectId);
				session.setAttribute("projectId", projectId);
			} else {
				response.sendRedirect("project.do");
				return null;
			}

		} else {
			session.setAttribute("projectId", tsForm.getPrjBean()
					.getProjectId());
		}

		tsForm.getTkBean().setAssignedBy(us.getEmployeeId());

		if ("startTask".equalsIgnoreCase(tsForm.getTask())){// TASK TO START TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_ONGOING;
			tsMan.editStatusProjectTask(taskId, us.getUserId(), taskStatus);
		
		}
		else if ("submitTask".equalsIgnoreCase(tsForm.getTask())){//TASK TO SUBMIT TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_WAITING;
			tsMan.editStatusProjectTask(taskId, us.getUserId(), taskStatus);
	
		}
		else if ("pauseTask".equalsIgnoreCase(tsForm.getTask())){ //TASK TO PAUSE TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_ONHOLD;
			String remarks = tsForm.getRemarksRecord();
			System.out.println(taskStatus + " ");
			tsMan.editStatusRemarksProjectTask(taskId, us.getUserId(), taskStatus, remarks);
		}
		else if ("resumeTask".equalsIgnoreCase(tsForm.getTask())){ //TASK TO RESUME TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_ONGOING;
			tsMan.editStatusProjectTask(taskId, us.getUserId(), taskStatus);
	
		}


		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_INVOLVED_TASK, us,
				request);

		tsForm.setTask("");
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());

		tsForm.setListCount(tsMan.getCountAssignTaskByProjectId(
				tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),
				tsForm.getPrjBean().getProjectId()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount()
				/ (double) Constant.pageSize));

		tsForm.setArrList(tsMan.getListProjectTaskByProjectId(tsForm
				.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm
				.getCurrPage(), Constant.pageSize, tsForm.getPrjBean()
				.getProjectId()));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(tsForm.getPageCount(),
						tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("list");
		/* return mapping.findForward("assignTask"); */

	}
}
