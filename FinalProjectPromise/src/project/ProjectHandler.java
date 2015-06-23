package project;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project_member.ProjectMemberManager;
import project_role.ProjectRoleManager;
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
//		CommonFunction.createAllowedMenu(null, request);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();
		EmployeeBean eBean = new EmployeeBean();
		
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
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForDeptHead(eBean.getDeptId(), 
					eForm.getCurrSearchField(), eForm.getCurrSearchValue()));
			request.setAttribute("pageTitle", "Project Entry");
			request.setAttribute("show", false);
			return mapping.findForward("projectEntry");
		}
		else if ("edit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("edit");
			request.setAttribute("pageTitle", "Project Edit");
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForDeptHead(eBean.getDeptId(), 
					eForm.getCurrSearchField(), eForm.getCurrSearchValue()));
			request.setAttribute("show", true);
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ENTRY,
					us, request);
			return mapping.findForward("projectEntry");
		}
		else if ("start".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("start");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus("PR_STAT_02");
			pForm.getpBean().setProjectProgress((float) 0);
			pForm.getpBean().setActStartDateInString(sdf.format(date));
			
			pMan.updateProject(pForm.getpBean());
			
		}
		else if ("submit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("submit");
			pForm.setpBean(pMan.getProjectByID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus("PR_STAT_03");
			
			pForm.getpBean().setActStartDate(sdf.parse(pForm.getpBean().getActStartDateDateInString()));
			pForm.getpBean().setActEndDate(date);
			Integer actMainDays = pForm.getpBean().getActEndDate().getDate() - pForm.getpBean().getActStartDate().getDate();
			
			pForm.getpBean().setActEndDateInString(sdf.format(date));
			pForm.getpBean().setActMainDays(actMainDays);
			
			
			pMan.updateProject(pForm.getpBean());
			
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
			pForm.getpBean().setProjectStatus("PR_STAT_02");
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
			System.out.println(isProc);
			if (isProc.equalsIgnoreCase("add")) {
				System.out.println("masuk save");
				pMan.insertProject(pForm.getpBean());
				pMemberMan.insertProjectMember(pRoleMan.getProjectRoleIdByCode(), pForm.getpBean().getEmployeeId(), pForm.getpBean().getProjectId());
				System.out.println("berhasil save");
			} 
			else if (isProc.equalsIgnoreCase("edit")){
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("cancel")){
				pForm.getpBean().setProjectStatus("PR_STAT_99");
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("pause")){
				pForm.getpBean().setProjectStatus("PR_STAT_05");
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("forceClose")){
				pForm.getpBean().setActStartDate(sdf.parse(pForm.getpBean().getActStartDateDateInString()));
				pForm.getpBean().setActEndDate(date);
				Integer actMainDays = pForm.getpBean().getActEndDate().getDate() - pForm.getpBean().getActStartDate().getDate();
				
				pForm.getpBean().setActEndDateInString(sdf.format(date));
				pForm.getpBean().setActMainDays(actMainDays);
				
				pForm.getpBean().setProjectStatus("PR_STAT_06");
				pMan.updateProject(pForm.getpBean());
			}
			else {
				System.out.println("gagal");
			}

			response.sendRedirect("project.do");
			return null;
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT, us,
				request);
		
		pForm.setTask("");
		pForm.setSearchField(pForm.getCurrSearchField());
		pForm.setSearchValue(pForm.getCurrSearchValue());

		int rowCount;
		rowCount = pMan.getCountProject(pForm.getCurrSearchField(),
				pForm.getCurrSearchValue());
		
		pForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		System.out.println("pageCount end");
		pForm.setListOfProject(pMan.getAllProject(
				pForm.getCurrSearchField(), pForm.getCurrSearchValue(),
				pForm.getCurrPage(), Constant.pageSize));
		System.out.println("isi list selesai");
		
		
		request.setAttribute("pageTitle", "Project List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pForm.getPageCount(),pForm.getCurrPage()));

		request.setAttribute("pageCount", pForm.getPageCount());
		request.setAttribute("currPage", pForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectList");
		
	}
}
