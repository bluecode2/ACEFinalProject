package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import employee.EmployeeBean;
import employee.EmployeeManager;

public class SearchSpvHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchSpvForm ssForm = (SearchSpvForm) form;
		EmployeeManager eman = new EmployeeManager();
		
		Integer rankId = ssForm.getRankId();
		Integer deptId = ssForm.getDeptId();
		String searchField = ssForm.getSearchField();
		String searchValue = ssForm.getSearchValue();
		System.out.println(rankId + "-" + deptId + "-" +searchField + "-" + searchValue);
		List<EmployeeBean> arrEmp = eman.getListEmployeeForSupervisor(deptId, rankId, searchField, searchValue);
		
		for (EmployeeBean employeeBean : arrEmp) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearchSpv\">");
			out.println("<td style=\"display: none\">" + employeeBean.getEmployeeId() + "</td>");
			out.println("<td>" + employeeBean.getEmployeeCode() + "</td>");
			out.println("<td>" + employeeBean.getEmployeeName() + "</td>");
			out.println("<td>" + employeeBean.getEmail() + "</td>");
			out.println("</tr>");
		}	

		out.flush();
		return null;
	}
}
