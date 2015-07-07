package search_dialog;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Constant;

import employee.EmployeeBean;
import employee.EmployeeManager;

public class SearchEmpSubordinateHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchEmpAssignedToForm seatForm = (SearchEmpAssignedToForm) form;
		
		
		Integer spvId = seatForm.getSpvId();
		String searchField = seatForm.getSearchField();
		String searchValue = seatForm.getSearchValue();
		
		//List<EmployeeBean> arrEmp = eman.getEmpForAssignTask(spvId, searchField, searchValue);
		
		List<EmployeeBean> arrEmp = new ArrayList<EmployeeBean>();
		generateSubordinateList(arrEmp,spvId,searchField,searchValue);
		
		
		
		//System.out.println(arrEmp.size());
		if (arrEmp.size() == 0) {
			out.println("<tr>");
			out.println("<td colspan=\"3\" align=\"center\">No Data Found</td>");	
			out.println("</tr>");
			
		}
		else {
			for (EmployeeBean employeeBean : arrEmp) {
				if(arrEmp.indexOf(employeeBean) == Constant.PAGE_SIZE) break;
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearchSubordinate\">");
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
	
	public void generateSubordinateList(List<EmployeeBean> arrEmp, Integer spvId, String searchField, String searchValue){
		if(spvId == null) return;
		
		EmployeeManager eman = new EmployeeManager();
		List<EmployeeBean> arrTemp = eman.getEmpForAssignTask(spvId, "", "");
		for (EmployeeBean employeeBean : arrTemp) {
			if(searchField.equals("employeeCode")){
				if(employeeBean.getEmployeeCode().toLowerCase().contains(searchValue.toLowerCase())){
					arrEmp.add(employeeBean);
				}
			}
			else if(searchField.equals("employeeName")){
				if(employeeBean.getEmployeeName().toLowerCase().contains(searchValue.toLowerCase())){
					arrEmp.add(employeeBean);
				}
			}
			else
				arrEmp.add(employeeBean);
			
			generateSubordinateList(arrEmp,employeeBean.getEmployeeId(),searchField,searchValue);
		}
	}
}
