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
		GeneralHolidayForm genForm = (GeneralHolidayForm) form;
		GeneralHolidayManager genManager = new GeneralHolidayManager();
		
		CommonFunction.createAllowedMenu(null, request);
		
		if("add".equals(genForm.getTask())){
			/*empForm.getEmpBean().setEmpId(empManager.generateIdEmp());
			empForm.setListOfJobs(empManager.getJobId());
			empForm.setListOfEmp(empManager.getEmployees());
			empForm.setListOfDept(empManager.getDeptId());
			
			request.setAttribute("ljob", empManager.getJobId());*/
			
			genForm.setIsAdd(true);
			request.setAttribute("pageTitle", "General Holiday Entry");
			
			request.setAttribute("pageNavigator", CommonFunction
					.createPagingNavigatorList(1,1));
			
			request.setAttribute("pageCount", 1);
			request.setAttribute("currPage", 1);
			request.setAttribute("rowCount", 1);
			
			return mapping.findForward("genEntry");
		}
		else if ("save".equals(genForm.getTask())){
			Boolean isAdd = genForm.getIsAdd();
			
			//if (genForm.getGenHolidayBean().getGenHolidayId() == 0)
				genForm.getGenHolidayBean().setGenHolidayId(1);
				
			if (isAdd) {
				genForm.getGenHolidayBean().setCreatedBy(1);
				genManager.insertGeneralHoliday(genForm.getGenHolidayBean());
			} 
			else {
				genForm.getGenHolidayBean().setUpdatedBy(1);
				genManager.editGeneralHoliday(genForm.getGenHolidayBean());
			}

			response.sendRedirect("generalHoliday.do");
			return null;
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
