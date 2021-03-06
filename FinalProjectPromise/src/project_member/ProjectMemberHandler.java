package project_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notification.NotificationManager;

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
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_MEMBER, us,
				request);
		
		ProjectManager pMan = new ProjectManager();
		ProjectBean pBean = new ProjectBean();
		
		ProjectRoleManager pRoleMan = new ProjectRoleManager();
		ProjectRoleForm pRoleForm = new ProjectRoleForm();
				
		Integer projId = (Integer) session.getAttribute("projectId");
		
		pBean = pMan.getProjectByID(projId);
		request.setAttribute("getProject", pBean);
		
		request.setAttribute("showAdd", false);
		
		if ("save".equalsIgnoreCase(pMemberForm.getTask())){
			request.setAttribute("showAdd", false);
			pMemberForm.getpMemberbean().setProjectId(projId);
			
			pMemberMan.insertProjectMember(pMemberForm.getpMemberbean());
			
			//Create notification
			NotificationManager nMan = new NotificationManager();
			nMan.createNotificationProjectMember(us.getEmployeeId(),pMemberForm.getpMemberbean().getEmployeeId(), pMemberForm.getpMemberbean().getProjectId(),pMemberForm.getpMemberbean().getProjectRoleId());
		}
		else if ("delProjMem".equalsIgnoreCase(pMemberForm.getTask())){
			
			ProjectMemberBean bean = pMemberMan.getProjectMemberById(pMemberForm.getSelectedId());
			
			pMemberMan.delProjMember(pMemberForm.getSelectedId());
			
			//Create Notification
			NotificationManager nMan = new NotificationManager();
			nMan.createNotificationRemoveProjectMember(us.getEmployeeId(),bean.getEmployeeId(), bean.getProjectId(),bean.getProjectRoleId());
		}
		pMemberForm.setTask("");

		int rowCount;
		rowCount = pMemberMan.getCountProjectMember(projId);
		
		pMemberForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));
		
		pMemberForm.setListOfProjMember(pMemberMan.getAllProjectMember(projId,				
				pMemberForm.getCurrPage(), Constant.PAGE_SIZE));
		request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPopUp("",""));
		request.setAttribute("lstProjectRole", pRoleMan.getAllProjectRoleForPopUp());

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pMemberForm.getPageCount(),pMemberForm.getCurrPage()));

		request.setAttribute("pageCount", pMemberForm.getPageCount());
		request.setAttribute("currPage", pMemberForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectMemberListEntry");
	}
}
