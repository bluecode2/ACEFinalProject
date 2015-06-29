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
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeForm;
import employee.EmployeeManager;
import general.GeneralParamManager;

public class ProjectHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectForm pForm = (ProjectForm) form;
		ProjectManager pMan = new ProjectManager();
//		CommonFunction.createAllowedMenu(null, request);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
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
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPM(eBean.getDeptId()));
					/*getAllEmployeeForDeptHead(eBean.getDeptId(), 
					"", ""));*/
			request.setAttribute("pageTitle", "Project Entry");
			request.setAttribute("show", false);
			return mapping.findForward("projectEntry");
		}
		else if ("edit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("edit");
			request.setAttribute("pageTitle", "Project Edit");
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPM(eBean.getDeptId()));
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
			
			pMan.updateProject(pForm.getpBean());
			
		}
		else if ("submit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("submit");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_WAITING_FOR_APPROVAL);
			pForm.getpBean().setActEndDate(now);
			
			pMan.updateProject(pForm.getpBean());
			
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			nMan.createNotificationSubmitedProject(us.getEmployeeId(), pForm.getpBean().getEmployeeId(), pForm.getpBean().getProjectId());

			
		}
		else if ("cancel".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("cancel");
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			request.setAttribute("pageTitle", "Project Cancel");
			request.setAttribute("show", true);
			return mapping.findForward("projectEntry");
			
		}
		else if ("pause".equalsIgnoreCase(pForm.getTask())){
			//bisa pause bisa submit
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			pForm.setIsProc("pause");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			request.setAttribute("pageTitle", "Project Pause");
			request.setAttribute("show", true);
			return mapping.findForward("projectEntry");
		}
		else if ("resume".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("resume");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_ONGOING);
			pMan.updateProject(pForm.getpBean());
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
				pMan.insertProject(pForm.getpBean());
				
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
				pMan.updateProject(pForm.getpBean());
				
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
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_CANCELLED);
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("pause")){
				System.out.println(pForm.getpBean());
				System.out.println(pForm.getpBean().getProjectName() + " - " + pForm.getpBean().getProjectDesc());
				
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_ON_HOLD);
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("forceClose")){
				pForm.getpBean().setActEndDate(now);
				
				pForm.getpBean().setProjectStatus(Constant.GeneralCode.PROJECT_STATUS_FORCE_CLOSED);
				pMan.updateProject(pForm.getpBean());
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
		/*rowCount = pMan.getCountProject(pForm.getCurrSearchField(),
				pForm.getCurrSearchValue());*/
		
		//untuk dept head atau PM
		if (addBtn){
			rowCount = pMan.getCountProjectListForRole("DEPT_ID", us.getDeptId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue());
			pForm.setListOfProject(pMan.getProjectListForRole("DEPT_ID", us.getDeptId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue(), pForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));
		}
		else {
			rowCount = pMan.getCountProjectListForRole("EMPLOYEE_ID", us.getEmployeeId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue());
			pForm.setListOfProject(pMan.getProjectListForRole("EMPLOYEE_ID", us.getEmployeeId(), pForm.getCurrSearchField(), pForm.getCurrSearchValue(), pForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));
		}
		
		pForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		
		//untuk all project
				/*pForm.setListOfProject(pMan.getAllProject(
						pForm.getCurrSearchField(), pForm.getCurrSearchValue(),
						pForm.getCurrPage(), Constant.pageSize));*/
		
		request.setAttribute("pageTitle", "Project");
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pForm.getPageCount(),pForm.getCurrPage()));

		request.setAttribute("pageCount", pForm.getPageCount());
		request.setAttribute("currPage", pForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectList");
	}
}
