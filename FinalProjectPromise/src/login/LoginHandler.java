package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserManager;

public class LoginHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		LoginForm lForm = (LoginForm) form;
		UserManager lMan = new UserManager();
		
/*		if ("validasi".) {
			
			response.sendRedirect("generalCode.do");
		}
		*/
		return mapping.findForward("default");
	}
}
