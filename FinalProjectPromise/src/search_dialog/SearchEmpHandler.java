package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserRoleBean;
import user.UserRoleManager;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class SearchEmpHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchEmpForm sEmpForm = (SearchEmpForm) form;
		EmployeeManager eMan = new EmployeeManager();
		
		String searchField = sEmpForm.getSearchField();
		String searchValue = sEmpForm.getSearchValue();
		
		List<EmployeeBean> arrEmp = eMan.getAllEmployee(searchField, searchValue, 1, Constant.pageSize);
		
		for (EmployeeBean empBean : arrEmp) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearchUserRole\">");
//			out.println("<td style=\"display: none\">" + uRoleBean.getUserRoleId() + "</td>");
//			out.println("<td>" + uRoleBean.getUserRoleCode() + "</td>");
//			out.println("<td>" + uRoleBean.getUserRoleName() + "</td>");
			out.println("</tr>");
		}	

		out.flush();
		return null;
	}
}
