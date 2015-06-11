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
			System.out.println("masuk ke form add");
			request.setAttribute("pageTitle", "Add General Code");
			request.setAttribute("pageNavigator",
					CommonFunction.createPagingNavigatorList(1, 1));

			request.setAttribute("pageCount", 1);
			request.setAttribute("currPage", 1);
			request.setAttribute("rowCount", 1);

			return mapping.findForward("insert");
		} else if ("search".equals(gcf.getTask())) {


			request.setAttribute("pageNavigator",
					CommonFunction.createPagingNavigatorList(1, 1));
		}

		else {

			request.setAttribute("pageTitle", "General Code List");

			request.setAttribute("pageNavigator",
					CommonFunction.createPagingNavigatorList(1, 1));

			request.setAttribute("pageCount", 1);
			request.setAttribute("currPage", 1);
			request.setAttribute("rowCount", 1);

		}

		return mapping.findForward("list");
	}
}
