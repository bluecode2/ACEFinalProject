package holiday;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import general.GeneralCodeManager;

public class PersonalHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		PersonalHolidayForm persForm = (PersonalHolidayForm) form;
		PersonalHolidayManager persManager = new PersonalHolidayManager();
		EmployeeManager empManager = new EmployeeManager();
		GeneralCodeManager genCodeManager = new GeneralCodeManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		if("add".equals(persForm.getTask())){
			persForm.setIsAdd(true);
			request.setAttribute("listEmployeeSearch", empManager.getListEmployeeForPersonalHoliday());
			
			CommonFunction.initializeHeader(Constant.MenuCode.PERSONAL_HOLIDAY_ENTRY,
					us, request);
			persForm.setListOfGenCode(genCodeManager.getGeneralCodeByParentId(Constant.GeneralCode.PERSONAL_HOLIDAY_TYPE));
			return mapping.findForward("personalHolidayEntry");
		}
		else if ("save".equals(persForm.getTask())){
			Boolean isAdd = persForm.getIsAdd();
			
			if (isAdd) {
				persForm.getPersHolidayBean().setHolidayId(persManager.getNewGenHolidayId());
				persForm.getPersHolidayBean().setCreatedBy(us.getUserId());
				
				
				if(!persManager.insertPersonalHoliday(persForm.getPersHolidayBean())){
					session.setAttribute("validationMessage",
							"Failed To Add New Personal Holiday!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Add New Personal Holiday!");
					session.setAttribute("validationType", "success");
				}
			} 
			else {
				persForm.getPersHolidayBean().setUpdatedBy(us.getUserId());
				
				if(!persManager.editPersonalHoliday(persForm.getPersHolidayBean())){
					session.setAttribute("validationMessage",
							"Failed To Edit Personal Holiday!");
					session.setAttribute("validationType", "danger");
				}
				else {
					session.setAttribute("validationMessage",
							"Succeed To Edit Personal Holiday!");
					session.setAttribute("validationType", "success");
				}
			}

			response.sendRedirect("personalHoliday.do");
			return null;
		}
		else if ("edit".equals(persForm.getTask())) {
			persForm.setPersHolidayBean(persManager.getPersonalHolidayEdit(persForm.getSelectedId()));
			
			request.setAttribute("listEmployeeSearch", empManager.getListEmployeeForPersonalHoliday());

			CommonFunction.initializeHeader(Constant.MenuCode.PERSONAL_HOLIDAY_ENTRY,
					us, request);
			persForm.setListOfGenCode(genCodeManager.getGeneralCodeByParentId(Constant.GeneralCode.PERSONAL_HOLIDAY_TYPE));
			
			return mapping.findForward("personalHolidayEntry");
		}
		else if ("delete".equals(persForm.getTask())) {
			persForm.getPersHolidayBean().setUpdatedBy(us.getUserId());
			persForm.getPersHolidayBean().setHolidayId(persForm.getSelectedId());
			
			
			if(!persManager.deletePersonalHoliday(persForm.getPersHolidayBean())){
				session.setAttribute("validationMessage",
						"Failed To Delete Personal Holiday!");
				session.setAttribute("validationType", "danger");
			}
			else {
				session.setAttribute("validationMessage",
						"Succeed To Delete Personal Holiday!");
				session.setAttribute("validationType", "success");
			}
		}
		
		persForm.setTask("");
		if(persForm.getCurrSearchField().equals("")){
			SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);
			persForm.setCurrSearchField("byDate");
			persForm.setCurrSearchValue(df.format(new Date()));
		}
		
		persForm.setSearchField(persForm.getCurrSearchField());
		persForm.setSearchValue(persForm.getCurrSearchValue());
		persForm.setSearchValue2(persForm.getCurrSearchValue2());
		
		if(session.getAttribute("validationMessage") != null){
			request.setAttribute("validationMessage", session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType", session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
		}
		int rowCount;
		persForm.setArrList(persManager.getPersonalHoliday(
				persForm.getCurrSearchField(), persForm.getCurrSearchValue(),persForm.getCurrSearchValue2(),
				persForm.getCurrPage(), Constant.PAGE_SIZE));
		
		rowCount = persManager.getCountPersonalHoliday(persForm.getCurrSearchField(), persForm.getCurrSearchValue(),persForm.getCurrSearchValue2());
		persForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.PAGE_SIZE));
		
		CommonFunction.initializeHeader(Constant.MenuCode.PERSONAL_HOLIDAY, us,
				request);
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(persForm.getPageCount(),persForm.getCurrPage()));
		request.setAttribute("pageCount", persForm.getPageCount());
		request.setAttribute("currPage", persForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("personalHolidayList");
	}
}
