package project;

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

import project_member.ProjectMemberBean;
import project_member.ProjectMemberManager;
import project_role.ProjectRoleManager;
import project_task.ProjectTaskBean;
import project_task.ProjectTaskManager;
import user.UserBean;

import common.CommonFunction;
import common.Constant;

import employee.EmployeeBean;
import employee.EmployeeForm;
import employee.EmployeeManager;

public class ProjectHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectForm pForm = (ProjectForm) form;
		ProjectManager pMan = new ProjectManager();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		pForm.setDateInString(sdf.format(now));
		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();
		EmployeeBean eBean = new EmployeeBean();
		NotificationManager nMan = new NotificationManager();
		ProjectRoleManager pRoleMan = new ProjectRoleManager();
		ProjectMemberManager pMemberMan = new ProjectMemberManager();
		
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		eBean = eMan.getEmployeeByEmpId(us.getEmployeeId());
		
		if ("add".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("add");
			pForm.setSelectedId(0);
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			session.setAttribute("deptId", eBean.getDeptId());
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPM("","",eBean.getDeptId()));
			request.setAttribute("show", false);
			return mapping.findForward("projectEntry");
		}
		else if ("edit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("edit");
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPM("","",eBean.getDeptId()));
			request.setAttribute("show", true);
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			return mapping.findForward("projectEntry");
		}
		else if ("start".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("start");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_ONGOING);
			pForm.getpBean().setActStartDate(now);
			pForm.getpBean().setUpdatedBy(us.getUserId());
			
			
			if(!pMan.updateProject(pForm.getpBean())){
				session.setAttribute("validationMessage",
						"Failed To Start Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Start Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "success");
			}

		}
		else if ("submit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("submit");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_WAITING_FOR_APPROVAL);
			pForm.getpBean().setActEndDate(now);
			pForm.getpBean().setUpdatedBy(us.getUserId());
			
			pMan.updateProject(pForm.getpBean());
			
			if(!pMan.updateProject(pForm.getpBean())){
				session.setAttribute("validationMessage",
						"Failed To Approve Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Approve Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "success");
			}
			
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			nMan.createNotificationSubmitedProject(us.getEmployeeId(), pForm.getpBean().getDept_id(), pForm.getpBean().getProjectId());
		}
		else if ("cancel".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("cancel");
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			
			if(!pMan.updateProject(pForm.getpBean())){
				session.setAttribute("validationMessage",
						"Failed To Cancel Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Cancel Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "success");
			}
			request.setAttribute("show", true);
			return mapping.findForward("projectEntry");
			
		}
		else if ("pause".equalsIgnoreCase(pForm.getTask())){
			//bisa pause bisa submit
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			pForm.setIsProc("pause");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			request.setAttribute("show", true);
			return mapping.findForward("projectEntry");
		}
		else if ("resume".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("resume");
			pForm.getpBean().setUpdatedBy(us.getUserId());
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setRemarks("");
			pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_ONGOING);
			if(!pMan.updateProject(pForm.getpBean())){
				session.setAttribute("validationMessage",
						"Failed To Resume Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Resume Project "+pForm.getpBean().getProjectName()+ " !");
				session.setAttribute("validationType", "success");
			}
			
		}
		else if ("forceClose".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("forceClose");
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			request.setAttribute("pageTitle", "Project Force Close");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			request.setAttribute("show", true);
			
			return mapping.findForward("projectEntry");
		}
		else if ("toTask".equalsIgnoreCase(pForm.getTask())){
			session.setAttribute("projectId", pForm.getSelectedId());
			
			response.sendRedirect("projectTask.do");
			return null;
		}
		else if ("toMember".equalsIgnoreCase(pForm.getTask())){
			session.setAttribute("projectId", pForm.getSelectedId());
			
			response.sendRedirect("projectMember.do");
			return null;
		}
		else if ("save".equalsIgnoreCase(pForm.getTask())){
			String isProc = pForm.getIsProc();
			if (isProc.equalsIgnoreCase("add")) {
				pForm.getpBean().setCreatedBy(us.getUserId());
				
				//Insert new project
				
				if(!pMan.insertProject(pForm.getpBean())){
					session.setAttribute("validationMessage",
							"Failed To Add New Project!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Add New Project!");
					session.setAttribute("validationType", "success");
				}
				
				
				//Insert PM into Projet Member
				ProjectMemberBean memberBean = new ProjectMemberBean();
				memberBean.setEmployeeId(pForm.getpBean().getEmployeeId());
				memberBean.setProjectRoleId(pRoleMan.getProjectManagerRoleId());
				memberBean.setProjectId(pForm.getpBean().getProjectId());
				pMemberMan.insertProjectMember(memberBean);
				
				//Create notification 
				nMan.createNotificationProjectMember(us.getEmployeeId(),pForm.getpBean().getEmployeeId(), pForm.getpBean().getProjectId(),pRoleMan.getProjectManagerRoleId());
			} 
			else if (isProc.equalsIgnoreCase("edit")){
				ProjectBean oldBean = pMan.getProjectByID(pForm.getpBean().getProjectId());
				
				//Update Project
				pForm.getpBean().setUpdatedBy(us.getUserId());
				
				
				if(!pMan.updateProject(pForm.getpBean())){
					session.setAttribute("validationMessage", "Failed To Edit project!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Edit Employee Rank!");
					session.setAttribute("validationType", "success");
				}
				
				//Condition if PM is changed
				if(pForm.getpBean().getEmployeeId() != oldBean.getEmployeeId()){
					//Insert new PM to Project Member
					ProjectMemberBean memberBean = new ProjectMemberBean();
					memberBean.setEmployeeId(pForm.getpBean().getEmployeeId());
					memberBean.setProjectRoleId(pRoleMan.getProjectManagerRoleId());
					memberBean.setProjectId(pForm.getpBean().getProjectId());
					pMemberMan.insertProjectMember(memberBean);
					
					//Create notification
					nMan.createNotificationProjectMember(us.getEmployeeId(),pForm.getpBean().getEmployeeId(), pForm.getpBean().getProjectId(),pRoleMan.getProjectManagerRoleId());
					nMan.createNotificationRemoveProjectMember(us.getEmployeeId(),oldBean.getEmployeeId(), pForm.getpBean().getProjectId(),pRoleMan.getProjectManagerRoleId());
				}
			}
			else if (isProc.equalsIgnoreCase("cancel")){
				pForm.getpBean().setUpdatedBy(us.getUserId());
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_CANCELLED);
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("pause")){
				pForm.getpBean().setUpdatedBy(us.getUserId());
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_ON_HOLD);
					
				if(!pMan.updateProject(pForm.getpBean())){
					session.setAttribute("validationMessage",
							"Failed To Pause Project "+pForm.getpBean().getProjectName()+ " !");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Pause Project "+pForm.getpBean().getProjectName()+ " !");
					session.setAttribute("validationType", "success");
				}
				
				ProjectTaskManager taskMan = new ProjectTaskManager();
				List<ProjectTaskBean> lstTask = taskMan.getListProjectTaskByProjectId("", "", 1, Integer.MAX_VALUE, pForm.getpBean().getProjectId());
				
				for (ProjectTaskBean projectTaskBean : lstTask) {
					if(projectTaskBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING)){
						projectTaskBean.setUpdatedBy(us.getUserId());
						projectTaskBean.setTaskStatus(Constant.GeneralCode.TASK_STATUS_ON_HOLD);
						projectTaskBean.setRemarks("Project paused");
						taskMan.updateTaskStat(projectTaskBean);
					}
				}
				
			}
			else if (isProc.equalsIgnoreCase("forceClose")){
				pForm.getpBean().setActEndDate(now);
				
				pForm.getpBean().setUpdatedBy(us.getUserId());
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_FORCE_CLOSED);
					
				if(!pMan.updateProject(pForm.getpBean())){
					session.setAttribute("validationMessage",
							"Failed To Force Close Project "+pForm.getpBean().getProjectName()+ " !");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Force Close Project "+pForm.getpBean().getProjectName()+ " !");
					session.setAttribute("validationType", "success");
				}
				
				ProjectTaskManager taskMan = new ProjectTaskManager();
				List<ProjectTaskBean> lstTask = taskMan.getListProjectTaskByProjectId("", "", 1, Integer.MAX_VALUE, pForm.getpBean().getProjectId());
				
				for (ProjectTaskBean projectTaskBean : lstTask) {
					if(!projectTaskBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_COMPLETED)){
						projectTaskBean.setUpdatedBy(us.getUserId());
						projectTaskBean.setTaskStatus(Constant.GeneralCode.TASK_STATUS_FORCE);
						projectTaskBean.setActEndDate(now);
						projectTaskBean.setRemarks("Project closed!");
						taskMan.updateTaskStat(projectTaskBean);
					}
				}
			}

			response.sendRedirect("project.do");
			return null;
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT, us,
				request);
		
		boolean addBtn = (Boolean) request.getAttribute("btnAddVisible");
		
		
		pForm.setTask("");
		pForm.setSearchField(pForm.getCurrSearchField());
		pForm.setSearchValue(pForm.getCurrSearchValue());

		int rowCount;
		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
		}
		
		//untuk dept head atau PM
		if (addBtn){
			rowCount = pMan.getCountProjectListForRole("DEPT_ID", us.getDeptId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue());
			pForm.setListOfProject(pMan.getProjectListForRole("DEPT_ID", us.getDeptId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue(), pForm.getCurrPage(), Constant.PAGE_SIZE));
		}
		else {
			rowCount = pMan.getCountProjectListForRole("EMPLOYEE_ID", us.getEmployeeId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue());
			pForm.setListOfProject(pMan.getProjectListForRole("EMPLOYEE_ID", us.getEmployeeId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue(), pForm.getCurrPage(), Constant.PAGE_SIZE));
		}
		
		pForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pForm.getPageCount(),pForm.getCurrPage()));

		request.setAttribute("pageCount", pForm.getPageCount());
		request.setAttribute("currPage", pForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectList");
	}
}
