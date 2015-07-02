package project_task;

import independent_task.IndependentTaskBean;
import independent_task.IndependentTaskManager;

import java.io.PrintWriter;
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
import project.ProjectManager;
import project_member.ProjectMemberManager;
import propose_project_task.ApprovePropProjManager;
import propose_project_task.ProposeProjectTaskManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeManager;

public class ProjectTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		ProjectTaskForm tsForm = (ProjectTaskForm) form;
		ProjectTaskManager tsMan = new ProjectTaskManager();

		ApprovePropProjManager aPropPMan = new ApprovePropProjManager();

		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		ProjectTaskBean pTaskBean = new ProjectTaskBean();
		EmployeeManager empMan = new EmployeeManager();
		ProjectManager projManager = new ProjectManager();
		ProjectMemberManager projMbrMgr = new ProjectMemberManager();
		ActivityManager actMan = new ActivityManager();

		IndependentTaskManager iTaskMan = new IndependentTaskManager();

		Integer projId = (Integer) session.getAttribute("projectId");
		NotificationManager noMan = new NotificationManager();

		Date now = new Date();

		if (tsForm.getPrjBean() == null) {
			if (session.getAttribute("projectId") != null) {
				Integer projectId = Integer.valueOf(session.getAttribute(
						"projectId").toString());
				tsForm.setPrjBean(projManager.getProjectByID(projectId));
				tsForm.setProjectId(projectId);
				tsForm.setTmpEstEndDateInString(tsForm.getPrjBean()
						.getEstEndDateInString());
				tsForm.setTmpEstStartDateInString(tsForm.getPrjBean()
						.getEstStartDateInString());
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

		if ("add".equals(tsForm.getTask())) {
			request.setAttribute("pageTitle", "Project Task Entry");
			CommonFunction.initializeHeader(
					Constant.MenuCode.PROJECT_TASK_ENTRY, us, request);
			tsForm.setIsAdd(true);
			tsForm.getTkBean().setIsOutsource(0);

			request.setAttribute("listProjMember", projMbrMgr
					.getProjectMemberToEvaluate(tsForm.getPrjBean()
							.getProjectId()));
			request.setAttribute("lstEmployeeId",
					empMan.getAllEmployee("", "", 1, Constant.pageSize));

			return mapping.findForward("entry");
		} else if ("save".equals(tsForm.getTask())) {
			Boolean isAdd = tsForm.getIsAdd();

			if (tsForm.getTkBean().getAssignedTo() == 0)
				tsForm.getTkBean().setAssignedTo(null);

			if (isAdd) {
				int tmpNewId = tsMan.getNewTaskId();
				tsForm.getTkBean().setProjectId(
						tsForm.getPrjBean().getProjectId());
				tsForm.getTkBean().setCreatedBy(us.getUserId());
				tsForm.getTkBean().setTaskId(tmpNewId);
				tsMan.createNewOProjectTask(tsForm.getTkBean());
				tsForm.setTkBean(tsMan.getTaskById(tmpNewId));
				noMan.createNotificationProjectTask(us.getEmployeeId(), tsForm
						.getTkBean().getAssignedTo(), tsForm.getTkBean()
						.getTaskId());
			} else {

				tsForm.getTkBean().setUpdatedBy(us.getUserId());
				tsMan.editProjectTask(tsForm.getTkBean());
			}

			response.sendRedirect("projectTask.do");
			return null;

		} else if ("firstEdit".equals(tsForm.getTask())) {

			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setIsAdd(false);
				CommonFunction.initializeHeader(
						Constant.MenuCode.PROJECT_TASK_ENTRY, us, request);
				tsForm.setTkBean(tsMan.getTaskById(tsForm.getSelectedId()));
				request.setAttribute("listProjMember", projMbrMgr
						.getProjectMemberToEvaluate(tsForm.getPrjBean()
								.getProjectId()));
				request.setAttribute("lstEmployeeId",
						empMan.getAllEmployee("", "", 1, Constant.pageSize));
				return mapping.findForward("entry");
			} else if (tsForm.getSelectedEdit() == 1) {
				/*
				 * tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_APPROVE)
				 * ; tsMan.editStatusProjectTask(tsForm.getSelectedId(),
				 * us.getUserId(), tsForm.getStatusTask());
				 */

				pTaskBean = tsMan.getTaskById(tsForm.getSelectedId());
				pTaskBean.setActEndDate(now);
				pTaskBean
						.setTaskStatus(Constant.GeneralCode.TASK_STATUS_COMPLETED);
				pTaskBean.setUpdatedBy(us.getUserId());

				tsMan.updateTaskStat(pTaskBean);
				tsForm.setTkBean(tsMan.getTaskById(tsForm.getSelectedId()));
				noMan.createNotificationProjectTask(us.getEmployeeId(), tsForm
						.getTkBean().getAssignedTo(), tsForm.getTkBean()
						.getTaskId());

			}
		} else if ("listActivity".equals(tsForm.getTask())) {
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
				} else {
					out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
				}

				out.println("</tr>");
			}

			out.flush();
			return null;
		} else if ("secondEdit".equals(tsForm.getTask())) {
			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_CANCELLED);
				tsMan.editStatusRemarksProjectTask(tsForm.getSelectedId(),
						us.getUserId(), tsForm.getStatusTask(),
						tsForm.getRemarksRecord());
				tsForm.setTkBean(tsMan.getTaskById(tsForm.getSelectedId()));
				noMan.createNotificationProjectTask(us.getEmployeeId(), tsForm
						.getTkBean().getAssignedTo(), tsForm.getTkBean()
						.getTaskId());

			} else if (tsForm.getSelectedEdit() == 1) {
				tsForm.setStatusTask(Constant.GeneralCode.TASK_STATUS_ONGOING);
				tsMan.editStatusRemarksProjectTask(tsForm.getSelectedId(),
						us.getUserId(), tsForm.getStatusTask(),
						tsForm.getRemarksRecord());
				tsForm.setTkBean(tsMan.getTaskById(tsForm.getSelectedId()));
				noMan.createNotificationProjectTask(us.getEmployeeId(), tsForm
						.getTkBean().getAssignedTo(), tsForm.getTkBean()
						.getTaskId());
			}

		}

		if ("approve".equalsIgnoreCase(tsForm.getTaskForProp())) {
			tsForm.setBean(aPropPMan.getApproveTaskById(tsForm
					.getSelectTaskId()));

			IndependentTaskBean iTaskBean = new IndependentTaskBean();
			iTaskBean.setTaskName(tsForm.getBean().getPropTaskName());
			iTaskBean.setTaskDesc(tsForm.getBean().getPropTaskDesc());
			iTaskBean.setAssignedBy(tsForm.getBean().getPropTo());
			iTaskBean.setAssignedTo(tsForm.getAssignTo());
			iTaskBean.setCreatedBy(us.getUserId());
			iTaskBean.setProjectId(tsForm.getBean().getProjectId());
			iTaskBean.setEstStartDateInString(tsForm.getBean()
					.getEstStartDateInString());
			iTaskBean.setEstEndDateInString(tsForm.getBean()
					.getEstEndDateInString());
			iTaskBean.setTaskId(iTaskMan.getNewTaskId());
			iTaskMan.createNewAssignTaskProj(iTaskBean);
			noMan.createNotificationProjectTask(us.getEmployeeId(),
					iTaskBean.getAssignedTo(), iTaskBean.getTaskId());

			aPropPMan.approveTask(tsForm.getBean());
			noMan.createNotificationProposeTaskProject(us.getEmployeeId(),
					tsForm.getBean().getPropBy(), tsForm.getBean()
							.getPropTaskId());
			response.sendRedirect("projectTask.do");
			return null;
		} else if ("decline".equalsIgnoreCase(tsForm.getTaskForProp())) {
			tsForm.getBean().setUpdatedBy(us.getUserId());
			tsForm.getBean().setPropTaskId(tsForm.getSelectTaskId());
			tsForm.getBean().setRemakrs(tsForm.getRemarksProp());
			aPropPMan.declineTask(tsForm.getBean());

			tsForm.setBean(aPropPMan.getApproveTaskById(tsForm
					.getSelectTaskId()));
			noMan.createNotificationProposeTaskProject(us.getEmployeeId(),
					tsForm.getBean().getPropBy(), tsForm.getBean()
							.getPropTaskId());
			response.sendRedirect("projectTask.do");
			return null;
		}

		// Default view
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_TASK, us,
				request);
		tsForm.setTask("");
		tsForm.setTaskForProp("");

		ProjectMemberManager pMemberMan = new ProjectMemberManager();
		tsForm.seteBean(pMemberMan.getProjectMemberToEvaluate(projId));

		tsForm.setEmpId(us.getEmployeeId());

		//Paging Project Task
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());

		tsForm.setArrList(tsMan.getListProjectTaskByProjectId(tsForm
				.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm
				.getCurrPage(), Constant.pageSize, tsForm.getPrjBean()
				.getProjectId()));
		tsForm.setListCount(tsMan.getCountAssignTaskByProjectId(
				tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),
				tsForm.getPrjBean().getProjectId()));

		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount()
				/ (double) Constant.pageSize));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(tsForm.getPageCount(),
						tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		//Paging Propose Task
		tsForm.setSearchField2(tsForm.getCurrSearchField2());
		tsForm.setSearchValue2(tsForm.getCurrSearchValue2());
		
		tsForm.setArrListProp(aPropPMan.getAllPropTask(
				tsForm.getCurrSearchField2(), tsForm.getCurrSearchValue2(),
				tsForm.getCurrPage2(), Constant.pageSize, us.getEmployeeId(),
				tsForm.getPrjBean().getProjectId()));
		tsForm.setListCount2(aPropPMan.getCountAllPropTask(
				tsForm.getCurrSearchField2(), tsForm.getCurrSearchValue2(),
				us.getEmployeeId(),tsForm.getPrjBean().getProjectId()));

		tsForm.setPageCount2((int) Math.ceil((double) tsForm.getListCount2()
				/ (double) Constant.pageSize));

		
		request.setAttribute("pageNavigator2", CommonFunction
				.createPagingNavigatorList(tsForm.getPageCount2(),
						tsForm.getCurrPage2()));
		
		request.setAttribute("pageCount2", tsForm.getPageCount2());
		request.setAttribute("currPage2", tsForm.getCurrPage2());
		request.setAttribute("rowCount2", tsForm.getListCount2());
		
		return mapping.findForward("list");

	}
}
