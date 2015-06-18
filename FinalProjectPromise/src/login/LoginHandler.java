package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

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
			try {
			
			if (lMan.getLoginValidasi(lForm.getUsername(), lForm.getPassword()) != null) {
				lForm.setUserBean(lMan.getLoginValidasi(lForm.getUsername(), lForm.getPassword()));
				if (lForm.getUserBean().getIsActive()==1) {
					session.setAttribute("currUser", lForm.getUserBean());
					response.sendRedirect("home.do");
					return null;
				}
				request.setAttribute("errorMessage", "User is not active");
			}

		} catch (Exception e) {
			request.setAttribute("errorMessage", "Invalid username or password");
		}
		}
		
		return mapping.findForward("default");
	}
}
