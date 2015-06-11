package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class GeneralParamHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		GeneralParamManager gpm = new GeneralParamManager();
		GeneralParamForm gpf = new GeneralParamForm();
		
		request.setAttribute("pageTitle", "Department List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(1,1));
		
		request.setAttribute("pageCount", gpm.getCountGeneralParam(gpf.getSearchCategory(),gpf.getSearchItem()));
		request.setAttribute("currPage", 1);
		request.setAttribute("rowCount", 1);
		
		return mapping.findForward("list");
	}
	
}
