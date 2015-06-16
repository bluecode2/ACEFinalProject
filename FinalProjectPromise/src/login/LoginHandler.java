package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(true);

		
		if ("validasi".equals(lForm.getTask())) {
			lForm.setUserBean(null);
			
			if (lMan.getLoginValidasi(lForm.getUsername(), lForm.getPassword()) != null) {
				session.setAttribute("user", lForm.getUserBean());
				response.sendRedirect("generalCode.do");
			}
			else {
				return mapping.findForward("default");
			}
		}
		
		return mapping.findForward("default");
	}
}
