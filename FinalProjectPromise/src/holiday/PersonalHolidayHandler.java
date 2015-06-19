package holiday;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeManager;

public class PersonalHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		PersonalHolidayForm persForm = (PersonalHolidayForm) form;
		PersonalHolidayManager persManager = new PersonalHolidayManager();
		EmployeeManager empManager = new EmployeeManager();
		
		CommonFunction.createAllowedMenu(null, request);
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		request.setAttribute("username", us.getUsername());
		
		if("add".equals(persForm.getTask())){
			persForm.setIsAdd(true);
			request.setAttribute("pageTitle", "Personal Holiday Entry");
			
			request.setAttribute("listEmployeeSearch", empManager.getListEmployeeForPersonalHoliday());
			
			return mapping.findForward("personalHolidayEntry");
		}
		else if ("save".equals(persForm.getTask())){
			Boolean isAdd = persForm.getIsAdd();
			
			if (isAdd) {
				persForm.getPersHolidayBean().setHolidayId(persManager.getNewGenHolidayId());
				persForm.getPersHolidayBean().setCreatedBy(1);
				persManager.insertPersonalHoliday(persForm.getPersHolidayBean());
			} 
			else {
				persForm.getPersHolidayBean().setUpdatedBy(1);
				persManager.editPersonalHoliday(persForm.getPersHolidayBean());
			}

			response.sendRedirect("personalHoliday.do");
			return null;
		}
		else if ("edit".equals(persForm.getTask())) {
			request.setAttribute("pageTitle", "Personal Holiday Edit");
			persForm.setPersHolidayBean(persManager.getPersonalHolidayEdit(persForm.getSelectedId()));
			
			request.setAttribute("listEmployeeSearch", empManager.getListEmployeeForPersonalHoliday());

			return mapping.findForward("personalHolidayEntry");
		}
		else if ("delete".equals(persForm.getTask())) {
			persManager.deletePersonalHoliday(persForm.getSelectedId());
		}
		
		persForm.setTask("");
		persForm.setSearchField(persForm.getCurrSearchField());
		persForm.setSearchValue(persForm.getCurrSearchValue());
		
		System.out.println(persForm.getCurrSearchField());
		System.out.println(persForm.getCurrSearchValue());
		
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
