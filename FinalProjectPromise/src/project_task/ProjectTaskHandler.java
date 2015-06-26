package project_task;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import activity.ActivityBean;
import activity.ActivityManager;
import project.ProjectManager;
import project_member.ProjectMemberManager;
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
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		ProjectManager projManager = new ProjectManager();
		ProjectMemberManager projMbrMgr = new ProjectMemberManager();
		ActivityManager actMan = new ActivityManager();
		System.out.println(session.getAttribute("projectId"));
		if (tsForm.getPrjBean() == null) {
			if (session.getAttribute("projectId") != null) {
				Integer projectId = Integer.valueOf(session.getAttribute("projectId").toString());
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
				tsForm.getTkBean().setProjectId(
						tsForm.getPrjBean().getProjectId());
				tsForm.getTkBean().setCreatedBy(us.getUserId());
				tsMan.createNewOProjectTask(tsForm.getTkBean());
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
				tsForm.setTkBean(tsMan.getDataForEdit(tsForm.getSelectedId()));
				request.setAttribute("listProjMember", projMbrMgr
						.getProjectMemberToEvaluate(tsForm.getPrjBean()
								.getProjectId()));
				request.setAttribute("lstEmployeeId",
						empMan.getAllEmployee("", "", 1, Constant.pageSize));
				return mapping.findForward("entry");
			} else if (tsForm.getSelectedEdit() == 2) {
				tsForm.setStatusTask("TA_STAT_07");
				tsMan.editStatusProjectTask(tsForm.getSelectedId(),
						us.getUserId(), tsForm.getStatusTask());
			}
		} 
		else if ("listActivity".equals(tsForm.getTask())) {
			int selId = tsForm.getSelectedId();
			System.out.println(selId);
			tsForm.setArrActivity(actMan.getActivityByTaskId(selId));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ActivityBean> arrActivity = tsForm.getArrActivity();

			for (ActivityBean actBean : arrActivity) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + actBean.getActivityDesc() + "</td>");
				if (actBean.getIsCompleted() == 1) {
					out.println("<td> <input type=\"checkbox\" checked disabled> </td>");					
				}
				else {
					out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");		
				}

				out.println("</tr>");
			}	

			out.flush();
			return null;
		}
		else if ("secondEdit".equals(tsForm.getTask())) {
			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setStatusTask("TA_STAT_99");
				tsMan.editStatusRemarksProjectTask(tsForm.getSelectedId(),
						us.getUserId(), tsForm.getStatusTask(),
						tsForm.getRemarksRecord());

			} else if (tsForm.getSelectedEdit() == 1) {
				tsForm.setStatusTask("TA_STAT_98");
				tsMan.editStatusRemarksProjectTask(tsForm.getSelectedId(),
						us.getUserId(), tsForm.getStatusTask(),
						tsForm.getRemarksRecord());
			}

		}

		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_TASK, us,
				request);
		request.setAttribute("pageTitle", "Project Task");
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

	}
}
