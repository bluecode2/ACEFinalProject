package projectApproval;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import project_member.ProjectMemberBean;
import project_member.ProjectMemberManager;
import search_dialog.SearchDeptHeadForm;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class ProjectApprovalHandler extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		

		
		ProjectApprovalForm paForm = (ProjectApprovalForm) form;
		ProjectManager paMan = new ProjectManager();
		ProjectMemberManager pmMan = new ProjectMemberManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		if ("listMembers".equals(paForm.getTask())) {
			Integer selId = paForm.getSelectedId();
			paForm.setArrMember(pmMan.getProjectMemberToEvaluate(selId));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ProjectMemberBean> arrMember = paForm.getArrMember();

			for (ProjectMemberBean pmBean : arrMember) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + pmBean.getEmpName() + "</td>");
				out.println("<td>" + pmBean.getProjRoleName() + "</td>");
				out.println("</tr>");
			}	

			out.flush();
			return null;
		}
		
		paForm.setTask("");
		paForm.setSearchField(paForm.getCurrSearchField());
		paForm.setSearchValue(paForm.getCurrSearchValue());

		int rowCount;
		System.out.println(paForm.getSelectedId());
		
		paForm.setArrList(paMan.getListProjectToEvaluate(
				paForm.getCurrSearchField(), paForm.getCurrSearchValue(),
				paForm.getCurrPage(), Constant.pageSize,us.getDeptId()));
		rowCount = paMan.getCountProjectToEvaluate(paForm.getCurrSearchField(),
				paForm.getCurrSearchValue(),us.getDeptId());
		
		paForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_APPROVAL,
				us, request);
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(paForm.getPageCount(),
						paForm.getCurrPage()));

		request.setAttribute("pageCount", paForm.getPageCount());
		request.setAttribute("currPage", paForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}

}
