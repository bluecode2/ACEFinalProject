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

public class GeneralCodeHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GeneralCodeForm gcForm = (GeneralCodeForm) form;
		GeneralCodeManager gcMan = new GeneralCodeManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		if ("edit".equals(gcForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_CODE_ENTRY,us, request);

			gcForm.setGenCodeBean(gcMan.getGeneralCodeByGenId(gcForm.getSelectedId()));
			return mapping.findForward("entry");
		}
		else if ("save".equals(gcForm.getTask())) {
			if (gcForm.getGenCodeBean().getGenCodeId() == "")
				gcForm.getGenCodeBean().setGenCodeId(null);
			
			gcForm.getGenCodeBean().setUpdatedBy(us.getUserId()); 
			if(gcMan.updateGeneralCode(gcForm.getGenCodeBean())){
				session.setAttribute("validationMessage",
						"Succeed to Edit General Code " + gcForm.getGenCodeBean().getGenCodeCaption() + "!");
				session.setAttribute("validationType", "success");
			}
			else{
				session.setAttribute("validationMessage", "Failed to Edit General Code " + gcForm.getGenCodeBean().getGenCodeCaption() + "!");
				session.setAttribute("validationType", "danger");
			}
				
			response.sendRedirect("generalCode.do");
			return null;
		}
		else if ("delete".equals(gcForm.getTask())) {
			gcForm.getGenCodeBean().setUpdatedBy(us.getUserId());
			if(gcMan.deleteGeneralCodeByCodeId(gcForm.getSelectedId())){
				session.setAttribute("validationMessage",
						"Succeed to Delete General Code!");
				session.setAttribute("validationType", "success");
			}
			else{
				session.setAttribute("validationMessage", "Failed to Delete General Code " + gcForm.getGenCodeBean().getGenCodeCaption() + "!");
				session.setAttribute("validationType", "danger");
			}
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_CODE,
				us, request);
		CommonFunction.createAllowedMenu(us, request);
		
		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
		}
		
		gcForm.setTask("");
		gcForm.setSearchField(gcForm.getCurrSearchField());
		gcForm.setSearchValue(gcForm.getCurrSearchValue());
		
		gcForm.setListCount(gcMan.getCountGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue()));
		gcForm.setPageCount((int) Math.ceil((double) gcForm.getListCount() / (double) Constant.PAGE_SIZE));
		
		gcForm.setArrList(gcMan.getAllGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue(), gcForm.getCurrPage(), Constant.PAGE_SIZE));

		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(gcForm.getPageCount(), gcForm.getCurrPage()));

		request.setAttribute("pageCount", gcForm.getPageCount());
		request.setAttribute("currPage", gcForm.getCurrPage());
		request.setAttribute("rowCount", gcForm.getListCount());

		return mapping.findForward("list");
	}
}
