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
import user.UserRoleBean;
import user.UserRoleManager;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class SearchUserRolePopUpHandler extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchUserRoleForm sUserRoleForm = (SearchUserRoleForm) form;
		UserRoleManager uRoleMan = new UserRoleManager();
		
		Integer deptId = sUserRoleForm.getUserRoleID();
		String searchField = sUserRoleForm.getSearchField();
		String searchValue = sUserRoleForm.getSearchValue();
		
		List<UserRoleBean> arrEmp = uRoleMan.getUserRole(sUserRoleForm.getSearchField(), sUserRoleForm.getSearchValue(), 1, Constant.pageSize);
		
		for (UserRoleBean uRoleBean : arrEmp) {
			out.println("<tr data-dismiss=\"modal\" class=\"lstUserRole\">");
			out.println("<td style=\"display: none\">" + uRoleBean.getUserRoleId() + "</td>");
			out.println("<td>" + uRoleBean.getUserRoleCode() + "</td>");
			out.println("<td>" + uRoleBean.getUserRoleName() + "</td>");
			out.println("</tr>");
		}	

		out.flush();
		return null;
	}
}
