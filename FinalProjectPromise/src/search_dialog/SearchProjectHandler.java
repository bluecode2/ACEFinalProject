package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Constant;
import employee.EmployeeBean;
import project.ProjectBean;
import project.ProjectManager;

public class SearchProjectHandler extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchProjectForm sProjForm = (SearchProjectForm) form;
		ProjectManager pMan = new ProjectManager();
		
		String searchField = sProjForm.getSearchField();
		String searchValue = sProjForm.getSearchValue();
		
		List<ProjectBean> list = pMan.getAllProject(searchField, searchValue, 1, Constant.pageSize);
		
		for (ProjectBean projBean : list) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearchProj\">");
			out.println("<td style=\"display: none\">" + projBean.getProjectId() + "</td>");
			out.println("<td>" + projBean.getProjectCode() + "</td>");
			out.println("<td>" + projBean.getProjectName() + "</td>");
			out.println("<td>" + projBean.getEmployeeName() + "</td>");
			out.println("</tr>");
		}	
		
		out.flush();
		return null;
	}
}