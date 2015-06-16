package holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

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
			
			genForm.getGenHolidayBean().setGenHolidayId(genManager.getNewGenHolidayId());
				
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
		
		genForm.setTask("");
		genForm.setSearchField(genForm.getCurrSearchField());
		genForm.setSearchValue(genForm.getCurrSearchValue());

		int rowCount;
		genForm.setArrList(genManager.getGeneralHoliday(
				genForm.getCurrSearchField(), genForm.getCurrSearchValue(),
				genForm.getCurrPage(), Constant.pageSize));
		
		rowCount = genManager.getCountGeneralHoliday(genForm.getCurrSearchField(),
				genForm.getCurrSearchValue());
		//
		genForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
		
		
		request.setAttribute("pageTitle", "General Holiday List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(genForm.getPageCount(),genForm.getCurrPage()));
		
		request.setAttribute("pageCount", genForm.getPageCount());
		request.setAttribute("currPage", genForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("genList");
	}
}
