package project_member;

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

public class ProjectMemberHandler extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectMemberForm pMemberForm = (ProjectMemberForm) form;
		ProjectMemberManager pMemberMan = new ProjectMemberManager();
		
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT, us,
				request);
		
		pMemberForm.setTask("");
		pMemberForm.setSearchField(pMemberForm.getCurrSearchField());
		pMemberForm.setSearchValue(pMemberForm.getCurrSearchValue());

		int rowCount;
		rowCount = pMemberMan.getCountProjectMember(pMemberForm.getCurrSearchField(),
				pMemberForm.getCurrSearchValue());
		
		pMemberForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		System.out.println("pageCount end");
		
		pMemberForm.setListOfProjMember(pMemberMan.getAllProjectMember(
				pMemberForm.getCurrSearchField(), pMemberForm.getCurrSearchValue(),
				pMemberForm.getCurrPage(), Constant.pageSize));
		System.out.println("isi list selesai");
		
		
		request.setAttribute("pageTitle", "Project Member Entry");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pMemberForm.getPageCount(),pMemberForm.getCurrPage()));

		request.setAttribute("pageCount", pMemberForm.getPageCount());
		request.setAttribute("currPage", pMemberForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("projectMemberListEntry");
	}
}
