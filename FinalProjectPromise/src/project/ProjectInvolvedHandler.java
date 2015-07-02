package project;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project_member.ProjectMemberBean;
import project_member.ProjectMemberForm;
import project_member.ProjectMemberManager;
import project_task.ProjectTaskForm;
import common.CommonFunction;
import common.Constant;
import user.UserBean;

public class ProjectInvolvedHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		ProjectInvolvedForm projectForm = (ProjectInvolvedForm) form;
		ProjectManager projectManager = new ProjectManager();
		ProjectMemberManager projectMemberManager = new ProjectMemberManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_INVOLVED, us,request);

		if ("listMembers".equals(projectForm.getTask())) {
			projectForm.setArrMember(projectMemberManager.getPopUpProjMember(projectForm.getSelectedId()));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ProjectMemberBean> arrMember = projectForm.getArrMember();

			for (ProjectMemberBean pmBean : arrMember) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + pmBean.getEmpName() + "</td>");
				out.println("<td>" + pmBean.getProjRoleName() + "</td>");
				out.println("</tr>");
			}	

			out.flush();
			return null;
		}
		else if ("toTask".equalsIgnoreCase(projectForm.getTask())){
			System.out.println(projectForm.getSelectedId());
			session.setAttribute("projectId", projectForm.getSelectedId());
			
			response.sendRedirect("projectInvolvedTask.do");
			return null;
		}
		
		projectForm.setTask("");
		projectForm.setSearchField(projectForm.getCurrSearchField());
		projectForm.setSearchValue(projectForm.getCurrSearchValue());
	
		int rowCount;
		rowCount = projectManager.getCountProjectInvolved(us.getEmployeeId(),
				projectForm.getCurrSearchValue());
		
		projectForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		
		projectForm.setListOfProjectInvolved(projectManager.getProjectInvolved(
				projectForm.getCurrSearchField(), projectForm.getCurrSearchValue(),
				projectForm.getCurrPage(), Constant.pageSize,  us.getUserId()));
		
		request.setAttribute("pageTitle", "Project Involved");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(projectForm.getPageCount(),projectForm.getCurrPage()));

		request.setAttribute("pageCount", projectForm.getPageCount());
		request.setAttribute("currPage", projectForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectInvolvedList");
	}
}
