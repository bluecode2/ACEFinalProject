package holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class GeneralHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommonFunction.createAllowedMenu(null, request);
		
		GeneralHolidayForm genForm = (GeneralHolidayForm)form;
		
		if("add".equals(genForm.getTask())){
			/*empForm.getEmpBean().setEmpId(empManager.generateIdEmp());
			empForm.setListOfJobs(empManager.getJobId());
			empForm.setListOfEmp(empManager.getEmployees());
			empForm.setListOfDept(empManager.getDeptId());
			
			request.setAttribute("ljob", empManager.getJobId());*/
			request.setAttribute("pageTitle", "General Holiday Entry");
			
			request.setAttribute("pageNavigator", CommonFunction
					.createPagingNavigatorList(1,1));
			
			request.setAttribute("pageCount", 1);
			request.setAttribute("currPage", 1);
			request.setAttribute("rowCount", 1);
			
			return mapping.findForward("genEntry");
		}
		
		request.setAttribute("pageTitle", "General Holiday List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(1,1));
		
		request.setAttribute("pageCount", 1);
		request.setAttribute("currPage", 1);
		request.setAttribute("rowCount", 1);
		
		return mapping.findForward("genList");
	}
}
