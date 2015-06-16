package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class GeneralCodeHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GeneralCodeForm gcForm = (GeneralCodeForm) form;
		GeneralCodeManager gcMan = new GeneralCodeManager();
		CommonFunction.createAllowedMenu(null, request);
		HttpSession session = request.getSession(true);
	
		
		if ("entry".equals(gcForm.getTask())) {
			System.out.println("masuk ke form add");
			request.setAttribute("pageTitle", "Add General Code");
			gcForm.setGenCodeBean(gcMan.getGeneralCodeByGenId(gcForm.getSelectedId()));
			return mapping.findForward("entry");
		} else if ("search".equals(gcForm.getTask())) {

			request.setAttribute("pageNavigator",
					CommonFunction.createPagingNavigatorList(1, 1));
		}

		else if ("save".equals(gcForm.getTask())) {

			if (gcForm.getGenCodeBean().getGenCodeId() == "")
				gcForm.getGenCodeBean().setGenCodeId(null);
			
				gcForm.getGenCodeBean().setUpdatedBy(1); 
				gcMan.updateGeneralCode(gcForm.getGenCodeBean());

			response.sendRedirect("generalCode.do");
			return null;
		}
		
		else if ("delete".equals(gcForm.getTask())) {
			gcMan.deleteGeneralCodeByCodeId(gcForm.getSelectedId());
		}
		
		gcForm.setTask("");
		gcForm.setSearchField(gcForm.getCurrSearchField());
		gcForm.setSearchValue(gcForm.getCurrSearchValue());
		
		gcForm.setListCount(gcMan.getCountGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue()));
		gcForm.setPageCount((int) Math.ceil((double) gcForm.getListCount() / (double) Constant.pageSize));
		
		gcForm.setArrList(gcMan.getAllGeneralCode(gcForm.getCurrSearchField(), gcForm.getCurrSearchValue(), gcForm.getCurrPage(), Constant.pageSize));

		request.setAttribute("pageTitle", "General Code List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(gcForm.getPageCount(), gcForm.getCurrPage()));

		request.setAttribute("pageCount", gcForm.getPageCount());
		request.setAttribute("currPage", gcForm.getCurrPage());
		request.setAttribute("rowCount", gcForm.getListCount());

		return mapping.findForward("list");
	}
}
