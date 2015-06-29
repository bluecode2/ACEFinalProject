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

public class SearchEmpAssignedToHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchEmpAssignedToForm seatForm = (SearchEmpAssignedToForm) form;
		EmployeeManager eman = new EmployeeManager();
		
		Integer spvId = seatForm.getSpvId();
		String searchField = seatForm.getSearchField();
		String searchValue = seatForm.getSearchValue();
		
		List<EmployeeBean> arrEmp = eman.getEmpForAssignTask(spvId, searchField, searchValue);
		System.out.println(arrEmp.size());
		if (arrEmp.size() == 0) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
			out.println("<td colspan=\"3\" align=\"center\">No Data Found</td>");	
			out.println("</tr>");
			
		}
		else {
			for (EmployeeBean employeeBean : arrEmp) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td style=\"display: none\">" + employeeBean.getEmployeeId() + "</td>");
				out.println("<td>" + employeeBean.getEmployeeCode() + "</td>");
				out.println("<td>" + employeeBean.getEmployeeName() + "</td>");
				out.println("<td>" + employeeBean.getEmail() + "</td>");
				out.println("</tr>");
			}				
		}
		


		out.flush();
		return null;
	}

}
