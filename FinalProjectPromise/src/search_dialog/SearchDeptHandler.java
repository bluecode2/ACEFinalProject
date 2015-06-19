package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import department.DepartmentBean;
import department.DepartmentManager;
import rank_employee.RankEmpBean;
import rank_employee.RankEmpManager;

public class SearchDeptHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
	
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		SearchDeptForm sdForm = (SearchDeptForm) form;
		DepartmentManager deptMan = new DepartmentManager();
		
		String searchField = sdForm.getSearchField();
		String searchValue = sdForm.getSearchValue();
		System.out.println(searchField  + " "+ searchValue);
		List<DepartmentBean> arrTmp = deptMan.getListDepartmentForSearchDialog(searchField, searchValue);

		for (DepartmentBean deptBean : arrTmp) {
			out.println("<tr data-dismiss=\"modal\" class=\"rowSearchDept\">");
			out.println("<td style=\"display: none\">" + deptBean.getDeptId() + "</td>");
			out.println("<td>" + deptBean.getDeptCode() + "</td>");
			out.println("<td>" + deptBean.getDeptName()+ "</td>");
			out.println("<td>" + deptBean.getDeptHeadName() + "</td>");
			out.println("</tr>");
		}
	//	
		out.flush();

		return null;
	}
}
