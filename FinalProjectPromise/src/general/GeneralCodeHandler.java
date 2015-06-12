package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if ("add".equals(gcForm.getTask())) {
			System.out.println("masuk ke form add");
			request.setAttribute("pageTitle", "Add General Code");


			return mapping.findForward("entry");
		} else if ("search".equals(gcForm.getTask())) {

			request.setAttribute("pageNavigator",
					CommonFunction.createPagingNavigatorList(1, 1));
		}

		else if ("save".equals(gcForm.getTask())) {
			//untuk mengesave edit  atau add
		}

		
		gcForm.setListCount(gcMan.getCountGeneralCode(gcForm.getColumn(), gcForm.getInput()));
		gcForm.setPageCount((int) Math.ceil((double) gcForm.getListCount() / (double) Constant.pageSize));
		
	
		request.setAttribute("pageTitle", "General Code List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(gcForm.getPageCount(), gcForm.getHal()));

		request.setAttribute("pageCount", gcForm.getPageCount());
		request.setAttribute("currPage", gcForm.getHal());
		request.setAttribute("rowCount", gcForm.getListCount());

		return mapping.findForward("list");
	}
}
