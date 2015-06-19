package project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class ProjectHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		request.setAttribute("pageTitle", "Project List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(1,1));

		request.setAttribute("pageCount", 1);
		request.setAttribute("currPage", 1);
		request.setAttribute("rowCount", 1);
		
		return super.execute(mapping, form, request, response);
	}
}
