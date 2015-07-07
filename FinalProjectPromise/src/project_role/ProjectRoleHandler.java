package project_role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;
import common.CommonFunction;
import common.Constant;

public class ProjectRoleHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectRoleManager prm = new ProjectRoleManager();
		ProjectRoleForm prf = (ProjectRoleForm) form;
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ROLE, us, request);

		if("add".equals(prf.getTask())){		
			prf.setIsAdd(true);
			request.setAttribute("pageTitle", "Project Role Entry");
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ROLE_ENTRY, us, request);
			
			return mapping.findForward("projectRoleEntry");
		}
		else if ("save".equals(prf.getTask())){
			
			Boolean isAdd = prf.getIsAdd();
			if (isAdd) {
				prf.getProjectRoleBean().setProjectRoleId(prm.getNewProjectRoleId());
				prf.getProjectRoleBean().setCreatedBy(us.getUserId());
				prm.insertProjectRole(prf.getProjectRoleBean());
			} 
			else {
				prf.getProjectRoleBean().setProjectRoleId(prf.getSelectedId());
				prf.getProjectRoleBean().setUpdatedBy(us.getUserId());
				prm.updateProjectRole(prf.getProjectRoleBean());
			}
			
			response.sendRedirect("projectRole.do");
			return null;
		}
		else if ("edit".equals(prf.getTask())) {
			request.setAttribute("pageTitle", "Project Role Edit");
			prf.setProjectRoleBean(prm.getProjectRoleById(prf.getSelectedId()));
			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_ROLE_ENTRY, us, request);

			return mapping.findForward("projectRoleEntry");
		}
		else if ("delete".equals(prf.getTask())) {
			prf.getProjectRoleBean().setProjectRoleId(prf.getSelectedId());
			prf.getProjectRoleBean().setUpdatedBy(us.getUserId());
			prm.deleteProjectRole(prf.getProjectRoleBean());
		}
		
		prf.setTask("");
		prf.setSearchField(prf.getCurrSearchField());
		prf.setSearchValue(prf.getCurrSearchValue());
		
		int rowCount;
		prf.setArrList(prm.getAllProjectRole(
				prf.getCurrSearchField(), prf.getCurrSearchValue(),
				prf.getCurrPage(), Constant.PAGE_SIZE));
		
		
		rowCount = prm.getCountGeneralHoliday(prf.getCurrSearchField(),
				prf.getCurrSearchValue());
		
		prf.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.PAGE_SIZE));
		
		request.setAttribute("pageTitle", "Project Role List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList( prf.getPageCount(),prf.getCurrPage()));
		
		request.setAttribute("pageCount", prf.getPageCount());
		request.setAttribute("currPage", prf.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectRoleList");
	}
}
