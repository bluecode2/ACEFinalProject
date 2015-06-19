package user;

import general.MenuBean;
import general.MenuManager;

import java.util.ArrayList;

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
		
		//CommonFunction.createAllowedMenu(us, request);
		
		
		if("add".equals(userRoleForm.getTask())){
			userRoleForm.setIsAdd(true);
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE_ENTRY, us, request);
			
			return mapping.findForward("userRoleEntry");
		}
		else if ("save".equals(userRoleForm.getTask())){
			Boolean isAdd = userRoleForm.getIsAdd();
			
			if (isAdd) {
				userRoleForm.getUserRoleBean().setUserRoleId(userRoleManager.getUserRoleId());
				userRoleForm.getUserRoleBean().setCreatedBy(us.getUserId());
				userRoleManager.insertUserRole(userRoleForm.getUserRoleBean());
			} 
			else {
				userRoleForm.getUserRoleBean().setUpdatedBy(us.getUserId());
				userRoleManager.editUserRole(userRoleForm.getUserRoleBean());
			}

			response.sendRedirect("userRole.do");
			return null;
		}
		else if ("edit".equals(userRoleForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE_ENTRY, us, request);
			userRoleForm.setUserRoleBean(userRoleManager.getUserRoleEdit(userRoleForm.getSelectedId()));

			return mapping.findForward("userRoleEntry");
		}
		else if ("delete".equals(userRoleForm.getTask())) {
			userRoleForm.getUserRoleBean().setUpdatedBy(us.getUserId());
			userRoleManager.deleteUserRole(userRoleForm.getSelectedId());
		}
		else if ("saveMenuAccess".equals(userRoleForm.getTask())) {
			String listMenuId = userRoleForm.getListMenuId();
			String listAllowAdd = userRoleForm.getListAllowAdd();
			String listAllowBack = userRoleForm.getListAllowBack();
			String listAllowSave = userRoleForm.getListAllowSave();
			String listAllowApprove = userRoleForm.getListAllowApprove();
			String listAllowDecline = userRoleForm.getListAllowDecline();
			
			System.out.println(listMenuId);
			System.out.println(listAllowAdd);
			System.out.println(listAllowBack);
			System.out.println(listAllowSave);
			System.out.println(listAllowApprove);
			System.out.println(listAllowDecline);
			return null;
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
		
		CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE, us, request);
		
		request.setAttribute("lstMenu", menuMan.getAllMenu());
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(userRoleForm.getPageCount(),userRoleForm.getCurrPage()));
		
		request.setAttribute("pageCount", userRoleForm.getPageCount());
		request.setAttribute("currPage", userRoleForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("userRoleList");
	}
}
