package project_task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notification.NotificationManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import propose_project_task.ProposeProjectTaskManager;
import user.UserBean;

import common.CommonFunction;
import common.Constant;

import employee.EmployeeBean;
import employee.EmployeeManager;

public class ProjectInvolvedTaskHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		ProjectTaskForm tsForm = (ProjectTaskForm) form;
		ProjectTaskManager tsMan = new ProjectTaskManager();

		ProposeProjectTaskManager pProjtaskMan = new ProposeProjectTaskManager();

		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		ProjectManager projManager = new ProjectManager();
		
		request.setAttribute("user", us.getEmployeeId());
		tsForm.setEmpId(us.getEmployeeId());
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		NotificationManager noMan = new NotificationManager();

		ProjectTaskBean pTaskBean = new ProjectTaskBean();

		Integer projId = (Integer) session.getAttribute("projectId");
		request.setAttribute("viewAddEdit", "hide");

		EmployeeBean empBean = empMan.getEmployeeByEmpId(us.getEmployeeId());
		tsForm.setAllowAdd(empBean.getSupervisorId() != null);
		
		if (tsForm.getPrjBean() == null) {
			if (session.getAttribute("projectId") != null) {
				Integer projectId = (Integer) session.getAttribute("projectId");
				tsForm.setPrjBean(projManager.getProjectByID(projectId));
				tsForm.setTmpProjectStatus(tsForm.getPrjBean().getProjectStatus());
				tsForm.setEstStartProj(tsForm.getPrjBean().getEstStartDateInString());
				tsForm.setEstEndProj(tsForm.getPrjBean().getEstEndDateInString());
				tsForm.setProjectId(projectId);
				tsForm.setProjectManagerId(tsForm.getPrjBean().getEmployeeId());
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

		// Handling Task Project Task
		if ("startTask".equalsIgnoreCase(tsForm.getTask())) {// TASK TO START
							// TASK
			int taskId = tsForm.getTestingId();
			//String taskStatus = Constant.GeneralCode.TASK_STATUS_ONGOING;
			//tsMan.startProjectTask(taskId, us.getUserId(), taskStatus);
			pTaskBean = tsMan.getTaskById(taskId);
			pTaskBean.setActStartDate(now);
			pTaskBean.setTaskStatus(Constant.GeneralCode.TASK_STATUS_ONGOING);
			pTaskBean.setUpdatedBy(us.getUserId());

			tsMan.updateTaskStat(pTaskBean);

		} else if ("submitTask".equalsIgnoreCase(tsForm.getTask())) {// TASK TO
																		// SUBMIT
																		// TASK
			/*int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL;
			tsMan.submitProjectTask(taskId, us.getUserId(), taskStatus);

			tsForm.setTkBean(tsMan.getTaskById(taskId));
			noMan.createNotificationProjectTask(us.getEmployeeId(), tsForm
					.getTkBean().getAssignedBy(), tsForm.getTkBean()
					.getTaskId());*/
			
			int taskId = tsForm.getTestingId();
			pTaskBean = tsMan.getTaskById(taskId);
			pTaskBean.setActEndDate(now);
			pTaskBean.setTaskStatus(Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL);
			pTaskBean.setUpdatedBy(us.getUserId());
			

			tsMan.updateTaskStat(pTaskBean);
			
			noMan.createNotificationProjectTask(us.getEmployeeId(), pTaskBean.getAssignedBy(), pTaskBean.getTaskId());
		} else if ("pauseTask".equalsIgnoreCase(tsForm.getTask())) { // TASK TO
																		// PAUSE
																		// TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_ON_HOLD;
			String remarks = tsForm.getRemarksRecord();
			tsMan.editStatusRemarksProjectTask(taskId, us.getUserId(),
					taskStatus, remarks);
			
		} else if ("resumeTask".equalsIgnoreCase(tsForm.getTask())) { // TASK TO
																		// RESUME
																		// TASK
			int taskId = tsForm.getTestingId();
			String taskStatus = Constant.GeneralCode.TASK_STATUS_ONGOING;
			tsMan.editStatusRemarksProjectTask(taskId, us.getUserId(), taskStatus,"");

		}

		// Handling Task Propose Task
		if ("edit".equalsIgnoreCase(tsForm.getTaskForProp())) {
			tsForm.setIsAdd(false);
			tsForm.setBean(pProjtaskMan.getPropProjTaskByTaskId(tsForm
					.getSelectTaskId()));
			request.setAttribute("viewAddEdit", "show");
		} else if ("delete".equalsIgnoreCase(tsForm.getTaskForProp())) {
			pProjtaskMan.delPropProjTask(us.getEmployeeId(),
					tsForm.getSelectTaskId());
		} else if ("cancel".equalsIgnoreCase(tsForm.getTaskForProp())) {

			tsForm.setShowDiv("false");
			request.setAttribute("viewAddEdit", "hide");
		} else if ("save".equalsIgnoreCase(tsForm.getTaskForProp())) {
			Boolean isAdd = tsForm.getIsAdd();
			if (isAdd) {
				tsForm.getBean().setProjectId(projId);
				tsForm.getBean().setCreatedBy(us.getUserId());
				tsForm.getBean().setPropBy(us.getEmployeeId());
				pProjtaskMan.insertPropProjTask(tsForm.getBean());
				
				tsForm.setBean(pProjtaskMan.getPropProjTaskByTaskId(tsForm.getBean().getPropTaskId()));
				noMan.createNotificationProposeTaskProject(us.getEmployeeId(), tsForm.getBean().getPropTo(), tsForm.getBean().getPropTaskId());
			} else {
				tsForm.getBean().setProjectId(projId);
				tsForm.getBean().setUpdatedBy(us.getUserId());
				pProjtaskMan.editPropProjTask(tsForm.getBean());
			}
			response.sendRedirect("projectInvolvedTask.do");
			return null;
		}

		// Default View
		CommonFunction.initializeHeader(
				Constant.MenuCode.PROJECT_INVOLVED_TASK, us, request);

		tsForm.setTask("");
		tsForm.setTaskForProp("");
		
		

		//paging Project Task
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		tsForm.setListCount(tsMan.getCountAssignTaskByProjectId(
				tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(),
				projId));

		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount()
				/ (double) Constant.PAGE_SIZE));
		
		tsForm.setArrList(tsMan.getListProjectTaskByProjectId(tsForm
				.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm
				.getCurrPage(), Constant.PAGE_SIZE, tsForm.getPrjBean()
				.getProjectId()));
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(tsForm.getPageCount(),
						tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());
		
		
		
		//paging Propose Task
		tsForm.setSearchField2(tsForm.getCurrSearchField2());
		tsForm.setSearchValue2(tsForm.getCurrSearchValue2());
		tsForm.setListCount2(pProjtaskMan.getCountPropProjTask(
				tsForm.getCurrSearchField2(), tsForm.getCurrSearchValue2(),
				projId,us.getEmployeeId()));

		tsForm.setPageCount2((int) Math.ceil((double) tsForm.getListCount2()
				/ (double) Constant.PAGE_SIZE));

		tsForm.setArrListProp(pProjtaskMan.getAllPropProjTask(
				tsForm.getCurrSearchField2(), tsForm.getCurrSearchValue2(),
				tsForm.getCurrPage2(), Constant.PAGE_SIZE, projId,us.getEmployeeId()));

		request.setAttribute("pageNavigator2", CommonFunction
				.createPagingNavigatorList(tsForm.getPageCount2(),
						tsForm.getCurrPage2()));

		request.setAttribute("pageCount2", tsForm.getPageCount2());
		request.setAttribute("currPage2", tsForm.getCurrPage2());
		request.setAttribute("rowCount2", tsForm.getListCount2());
		return mapping.findForward("list");
		

	}
}
