package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project_member.ProjectMemberBean;
import project_member.ProjectMemberManager;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class SearchProjMemberHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchProjMemberForm sEmpForm = (SearchProjMemberForm) form;
		ProjectMemberManager pMan = new ProjectMemberManager();
		
		String searchField = sEmpForm.getSearchField();
		String searchValue = sEmpForm.getSearchValue();
		
		/*List<ProjectMemberBean> arrEmp = pMan.getAllEmployee(searchField, searchValue, 1, Constant.pageSize);
		
		for (EmployeeBean empBean : arrEmp) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearchEmployee\">");
			out.println("<td style=\"display: none\">" + empBean.getEmployeeId() + "</td>");
			out.println("<td>" + empBean.getEmployeeCode() + "</td>");
			out.println("<td>" + empBean.getEmployeeName() + "</td>");
			out.println("<td>" + empBean.getDeptName() + "</td>");
			out.println("<td style=\"display: none\">" + empBean.getDeptId() + "</td>");
			out.println("</tr>");
		}	*/

		out.flush();
		return null;
	}
}
