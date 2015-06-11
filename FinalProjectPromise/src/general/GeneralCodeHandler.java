package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class GeneralCodeHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GeneralCodeForm gcf = (GeneralCodeForm) form;

		if ("add".equals(gcf.getTask())) {

		} else if ("search".equals(gcf.getTask())) {

		}
		

		request.setAttribute("pageTitle", "General Code List");

		request.setAttribute("pageNavigator",
				CommonFunction.createPagingNavigatorList(1, 1));

		request.setAttribute("pageCount", 1);
		request.setAttribute("currPage", 1);
		request.setAttribute("rowCount", 1);

		return mapping.findForward("list");
	}
}
