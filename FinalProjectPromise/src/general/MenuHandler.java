package general;

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

public class MenuHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		MenuForm mnForm = (MenuForm) form;
		MenuManager mnMan = new MenuManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		CommonFunction.createAllowedMenu(us, request);
		
		System.out.println("masuk ke handler");
		mnForm.setListCount(mnMan.getCountMenu(mnForm.getCurrSearchField(), mnForm.getCurrSearchValue()));
		mnForm.setPageCount((int) Math.ceil((double) mnForm.getListCount() / (double) Constant.pageSize));
		System.out.println(mnForm.getListCount());
		System.out.println(mnForm.getPageCount());
		mnForm.setArrList(mnMan.selectListMenu(mnForm.getCurrSearchField(), mnForm.getCurrSearchValue(), mnForm.getCurrPage(), Constant.pageSize));
		System.out.println(mnForm.getArrList());
		request.setAttribute("pageTitle", "General Code List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(mnForm.getPageCount(), mnForm.getCurrPage()));

		request.setAttribute("pageCount", mnForm.getPageCount());
		request.setAttribute("currPage", mnForm.getCurrPage());
		request.setAttribute("rowCount", mnForm.getListCount());
		return mapping.findForward("list");
	
	
	}
}
