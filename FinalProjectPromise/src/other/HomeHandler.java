package other;

import java.util.ArrayList;

import general.MenuBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HomeHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		//MenuManager menuMan = new MenuManager();
		request.setAttribute("menuList", "abc");
		
		return mapping.findForward("home");
	}
	
	class MenuForDisplay{
		public MenuBean menu;
		public ArrayList<MenuForDisplay> child;
	}
}
