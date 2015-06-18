package other;

import java.util.ArrayList;

import general.MenuBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

import user.UserBean;

public class HomeHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		//MenuManager menuMan = new MenuManager();
		HomeForm hmForm = (HomeForm) form;
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		request.setAttribute("username", us.getUsername());
		CommonFunction.createAllowedMenu(us, request);
		
		return mapping.findForward("index");
	}
}
