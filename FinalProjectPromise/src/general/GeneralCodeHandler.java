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

	
		/*if ("add".equals(gcForm.getTask())) {
			request.setAttribute("pageTitle", "Add General Code");
			//gcForm.setGenCodeBean(gcMan.getGeneralCodeByGenId(gcForm.getSelectedId()));
			return mapping.findForward("entry");
		} */

		if ("edit".equals(gcForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_CODE_ENTRY,us, request);
			/*request.setAttribute("lstDeptHead",
					eMan.getAllEmployeeForDeptHead(gcForm.getSelectedId()));*/
			request.setAttribute("pageTitle", "General Code Edit");

			gcForm.setGenCodeBean(gcMan.getGeneralCodeByGenId(gcForm.getSelectedId()));
/*			if(gcForm.getGenCodeBean().getDeptHeadCode() != null)
				gcForm.setDeptHeadDisplay(gcForm.getSelectedDept().getDeptHeadCode() + " - "
						+ gcForm.getSelectedDept().getDeptHeadName());
			else
				gcForm.setDeptHeadDisplay("");*/
			return mapping.findForward("entry");
		}
		else if ("save".equals(gcForm.getTask())) {
			System.out.println("save");
			if (gcForm.getGenCodeBean().getGenCodeId() == "")
				gcForm.getGenCodeBean().setGenCodeId(null);
			
			gcForm.getGenCodeBean().setUpdatedBy(us.getUserId()); 
			gcMan.updateGeneralCode(gcForm.getGenCodeBean());
				
			response.sendRedirect("generalCode.do");
			return null;
		}
		else if ("delete".equals(gcForm.getTask())) {
			gcForm.getGenCodeBean().setUpdatedBy(us.getUserId());
			gcMan.deleteGeneralCodeByCodeId(gcForm.getSelectedId());
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_CODE,
				us, request);
		CommonFunction.createAllowedMenu(us, request);
		
		gcForm.setTask("");
		gcForm.setSearchField(gcForm.getCurrSearchField());
		gcForm.setSearchValue(gcForm.getCurrSearchValue());
		
		gcForm.setListCount(gcMan.getCountGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue()));
		gcForm.setPageCount((int) Math.ceil((double) gcForm.getListCount() / (double) Constant.pageSize));
		
		gcForm.setArrList(gcMan.getAllGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue(), gcForm.getCurrPage(), Constant.pageSize));

		request.setAttribute("pageTitle", "General Code");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(gcForm.getPageCount(), gcForm.getCurrPage()));

		request.setAttribute("pageCount", gcForm.getPageCount());
		request.setAttribute("currPage", gcForm.getCurrPage());
		request.setAttribute("rowCount", gcForm.getListCount());

		return mapping.findForward("list");
	}
}
