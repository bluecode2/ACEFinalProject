package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class UserHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
//		return super.execute(mapping, form, request, response);
		System.out.println("masuk ke index");
		UserForm uForm = (UserForm) form;
		UserManager uMan = new UserManager();
		CommonFunction.createAllowedMenu(null, request);
		
		request.setAttribute("pageTitle", "User");
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(15,5));
		
		request.setAttribute("pageCount", 5);
		request.setAttribute("currPage", 5);
		request.setAttribute("rowCount", 50);
		System.out.println("berhasilkasi smua attribut");
		return mapping.findForward("userAdd");
	}
}
