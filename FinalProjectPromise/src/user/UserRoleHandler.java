package user;

import general.MenuManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class UserRoleHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		UserRoleForm userRoleForm = (UserRoleForm) form;
		UserRoleManager userRoleManager = new UserRoleManager();
		MenuManager menuMan = new MenuManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		CommonFunction.createAllowedMenu(us, request);



		
		if("add".equals(userRoleForm.getTask())){
			userRoleForm.setIsAdd(true);
			request.setAttribute("pageTitle", "User Role Entry");
			
			return mapping.findForward("userRoleEntry");
		}
		else if ("save".equals(userRoleForm.getTask())){
			Boolean isAdd = userRoleForm.getIsAdd();
			
			if (isAdd) {
				userRoleForm.getUserRoleBean().setUserRoleId(userRoleManager.getUserRoleId());
				userRoleForm.getUserRoleBean().setCreatedBy(1);
				userRoleManager.insertUserRole(userRoleForm.getUserRoleBean());
			} 
			else {
				userRoleForm.getUserRoleBean().setUpdatedBy(1);
				userRoleManager.editUserRole(userRoleForm.getUserRoleBean());
			}

			response.sendRedirect("userRole.do");
			return null;
		}
		else if ("edit".equals(userRoleForm.getTask())) {
			request.setAttribute("pageTitle", "User Role Edit");
			userRoleForm.setUserRoleBean(userRoleManager.getUserRoleEdit(userRoleForm.getSelectedId()));

			return mapping.findForward("userRoleEntry");
		}
		else if ("delete".equals(userRoleForm.getTask())) {
			userRoleManager.deleteUserRole(userRoleForm.getSelectedId());
		}
		
		userRoleForm.setTask("");
		userRoleForm.setSearchField(userRoleForm.getCurrSearchField());
		userRoleForm.setSearchValue(userRoleForm.getCurrSearchValue());

		int rowCount;
		userRoleForm.setArrList(userRoleManager.getUserRole(
				userRoleForm.getCurrSearchField(), userRoleForm.getCurrSearchValue(),
				userRoleForm.getCurrPage(), Constant.pageSize));
		
		rowCount = userRoleManager.getCountUserRole(userRoleForm.getCurrSearchField(),
				userRoleForm.getCurrSearchValue());
		
		userRoleForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
		
		request.setAttribute("lstMenu", menuMan.getAllMenuHead());
		
		
		request.setAttribute("pageTitle", "User Role List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(userRoleForm.getPageCount(),userRoleForm.getCurrPage()));
		
		request.setAttribute("pageCount", userRoleForm.getPageCount());
		request.setAttribute("currPage", userRoleForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("userRoleList");
	}
}
