package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		CommonFunction.createAllowedMenu(null, request);
		
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
		
		
		request.setAttribute("pageTitle", "User Role List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(userRoleForm.getPageCount(),userRoleForm.getCurrPage()));
		
		request.setAttribute("pageCount", userRoleForm.getPageCount());
		request.setAttribute("currPage", userRoleForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("userRoleList");
	}
}
