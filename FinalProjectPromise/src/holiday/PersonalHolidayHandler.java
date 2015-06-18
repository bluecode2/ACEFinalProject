package holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class PersonalHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		PersonalHolidayForm persForm = (PersonalHolidayForm) form;
		PersonalHolidayManager persManager = new PersonalHolidayManager();
		
		CommonFunction.createAllowedMenu(null, request);
		
		/*if("add".equals(persForm.getTask())){
			persForm.setIsAdd(true);
			request.setAttribute("pageTitle", "General Holiday Entry");
			
			return mapping.findForward("genEntry");
		}
		else if ("save".equals(persForm.getTask())){
			Boolean isAdd = persForm.getIsAdd();
			
			if (isAdd) {
				persForm.getGenHolidayBean().setGenHolidayId(persManager.getNewGenHolidayId());
				persForm.getGenHolidayBean().setCreatedBy(1);
				persManager.insertGeneralHoliday(persForm.getGenHolidayBean());
			} 
			else {
				persForm.getGenHolidayBean().setUpdatedBy(1);
				persManager.editGeneralHoliday(persForm.getGenHolidayBean());
			}

			response.sendRedirect("generalHoliday.do");
			return null;
		}
		else if ("edit".equals(persForm.getTask())) {
			request.setAttribute("pageTitle", "General Holiday Edit");
			persForm.setGenHolidayBean(persManager.getGeneralHolidayByHolId(persForm.getSelectedId()));

			return mapping.findForward("genEntry");
		}
		else if ("delete".equals(persForm.getTask())) {
			persManager.deleteGeneralHoliday(persForm.getSelectedId());
		}*/
		
		persForm.setTask("");
		persForm.setSearchField(persForm.getCurrSearchField());
		persForm.setSearchValue(persForm.getCurrSearchValue());
		int rowCount;
		persForm.setArrList(persManager.getPersonalHoliday(
				persForm.getCurrSearchField(), persForm.getCurrSearchValue(),
				persForm.getCurrPage(), Constant.pageSize));
		
		rowCount = persManager.getCountPersonalHoliday(persForm.getCurrSearchField(), persForm.getCurrSearchValue());
		
		persForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
		
		request.setAttribute("pageTitle", "Personal Holiday List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(persForm.getPageCount(),persForm.getCurrPage()));
		
		request.setAttribute("pageCount", persForm.getPageCount());
		request.setAttribute("currPage", persForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("personalHolidayList");
	}
}
