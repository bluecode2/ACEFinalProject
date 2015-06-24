package project_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectBean;
import project.ProjectForm;
import project.ProjectManager;
import project_role.ProjectRoleBean;
import project_role.ProjectRoleForm;
import project_role.ProjectRoleManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeForm;
import employee.EmployeeManager;

public class ProjectMemberHandler extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectMemberForm pMemberForm = (ProjectMemberForm) form;
		ProjectMemberManager pMemberMan = new ProjectMemberManager();
		
		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();
		
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT, us,
				request);
		
		ProjectManager pMan = new ProjectManager();
		ProjectBean pBean = new ProjectBean();
		
		ProjectRoleManager pRoleMan = new ProjectRoleManager();
		ProjectRoleForm pRoleForm = new ProjectRoleForm();
				
		Integer projId = (Integer) session.getAttribute("projectId");
		
		pBean = pMan.getProjectByID(projId);
		request.setAttribute("getProject", pBean);
		
		request.setAttribute("showAdd", false);
		
		if ("add".equalsIgnoreCase(pMemberForm.getTask())){
			request.setAttribute("showAdd", true);
			request.setAttribute("lstEmployeeId", eMan.getAllEmployee(eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
				eForm.getCurrPage(), Constant.pageSize));
			request.setAttribute("lstProjectRole", pRoleMan.getAllProjectRole(pRoleForm.getCurrSearchField(), pRoleForm.getCurrSearchValue(),
				pRoleForm.getCurrPage(), Constant.pageSize));
		}
		else if ("save".equalsIgnoreCase(pMemberForm.getTask())){
			request.setAttribute("showAdd", false);
			pMemberMan.insertProjectMember(pMemberForm.getpMemberbean().getProjectRoleId(), pMemberForm.getpMemberbean().getEmployeeId(), projId);
		}
		else if ("delProjMem".equalsIgnoreCase(pMemberForm.getTask())){
			pMemberMan.delProjMember(pMemberForm.getSelectedId());
		}
		pMemberForm.setTask("");

		int rowCount;
		rowCount = pMemberMan.getCountProjectMember(projId);
		
		pMemberForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		System.out.println("pageCount end");
		
		pMemberForm.setListOfProjMember(pMemberMan.getAllProjectMember(projId,				
				pMemberForm.getCurrPage(), Constant.pageSize));
		System.out.println("isi list selesai");
		
		
		request.setAttribute("pageTitle", "Project Member Entry");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pMemberForm.getPageCount(),pMemberForm.getCurrPage()));

		request.setAttribute("pageCount", pMemberForm.getPageCount());
		request.setAttribute("currPage", pMemberForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectMemberListEntry");
	}
}
