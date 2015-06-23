package project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_INVOLVED, us,
				request);
		
		///
		projectForm.setTask("");
		projectForm.setSearchField(projectForm.getCurrSearchField());
		projectForm.setSearchValue(projectForm.getCurrSearchValue());

		int rowCount;
		rowCount = projectManager.getCountProject(projectForm.getCurrSearchField(),
				projectForm.getCurrSearchValue());
		
		projectForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		System.out.println("pageCount end");
		
		projectForm.setListOfProjectInvolved(projectManager.getProjectInvolved(
				projectForm.getCurrSearchField(), projectForm.getCurrSearchValue(),
				projectForm.getCurrPage(), Constant.pageSize,  us.getUserId()));
		
		request.setAttribute("pageTitle", "Project Involved List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(projectForm.getPageCount(),projectForm.getCurrPage()));

		request.setAttribute("pageCount", projectForm.getPageCount());
		request.setAttribute("currPage", projectForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectInvolvedList");
	}
}
