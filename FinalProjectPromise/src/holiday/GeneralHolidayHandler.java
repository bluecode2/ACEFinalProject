package holiday;

import general.GeneralCodeManager;

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

public class GeneralHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		GeneralHolidayForm genForm = (GeneralHolidayForm) form;
		GeneralHolidayManager genManager = new GeneralHolidayManager();
		GeneralCodeManager genCodeManager = new GeneralCodeManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		if("add".equals(genForm.getTask())){
			genForm.setIsAdd(true);
			request.setAttribute("pageTitle", "General Holiday");
			
			CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_HOLIDAY_ENTRY,
					us, request);
			genForm.setListOfGenCode(genCodeManager.getGeneralCodeByParentId(Constant.GeneralCode.GENERAL_HOLIDAY_TYPE));
			
			return mapping.findForward("genEntry");
		}
		else if ("save".equals(genForm.getTask())){
			Boolean isAdd = genForm.getIsAdd();
			if (isAdd) {
				genForm.getGenHolidayBean().setCreatedBy(us.getUserId());
				genManager.insertGeneralHoliday(genForm.getGenHolidayBean());
			} 
			else {
				genForm.getGenHolidayBean().setUpdatedBy(us.getUserId());
				genManager.editGeneralHoliday(genForm.getGenHolidayBean());
			}

			response.sendRedirect("generalHoliday.do");
			return null;
		}
		else if ("generate".equals(genForm.getTask())){
			genManager.generateWeekend(genForm.getStartDateInString(), genForm.getEndDateInString(), genForm.getCheckDays(), us.getUserId());
			
			response.sendRedirect("generalHoliday.do");
			return null;
		}
		else if ("edit".equals(genForm.getTask())) {
			request.setAttribute("pageTitle", "General Holiday Edit");
			genForm.setGenHolidayBean(genManager.getGeneralHolidayByHolId(genForm.getSelectedId()));

			CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_HOLIDAY_ENTRY,
					us, request);
			genForm.setListOfGenCode(genCodeManager.getGeneralCodeByParentId(Constant.GeneralCode.GENERAL_HOLIDAY_TYPE));
			
			return mapping.findForward("genEntry");
		}
		else if ("delete".equals(genForm.getTask())) {
			genForm.getGenHolidayBean().setUpdatedBy(us.getUserId());
			genForm.getGenHolidayBean().setGenHolidayId(genForm.getSelectedId());
			genManager.deleteGeneralHoliday(genForm.getGenHolidayBean());
		}
		else if ("search".equals(genForm.getTask())) {
			if (genForm.getCurrSearchValue()!= "" && genForm.getCurrSearchValue2() != "") {	
				int rowCount;
				genForm.setArrList(genManager.getGeneralHoliday(
						genForm.getCurrSearchField(), genForm.getCurrSearchValue(), genForm.getCurrSearchValue2(),
						genForm.getCurrPage(), Constant.PAGE_SIZE));
				
				rowCount = genManager.getCountGeneralHoliday(genForm.getCurrSearchField(),
						genForm.getCurrSearchValue(), genForm.getCurrSearchValue2());

				genForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.PAGE_SIZE));
				request.setAttribute("pageTitle", "General Holiday");
				
				request.setAttribute("pageNavigator", CommonFunction
						.createPagingNavigatorList(genForm.getPageCount(),genForm.getCurrPage()));
				
				request.setAttribute("pageCount", genForm.getPageCount());
				request.setAttribute("currPage", genForm.getCurrPage());
				request.setAttribute("rowCount", rowCount);
				
				CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_HOLIDAY, us,
						request);
				
				return mapping.findForward("genList");				
			}
			else {
				genForm.setCurrSearchField("");
			}
		}
		else if("generateWeekend".equals(genForm.getTask())){
			request.setAttribute("pageTitle", "Generate Holiday Entry");
			
			CommonFunction.initializeHeader(Constant.MenuCode.GENERATE_HOLIDAY_ENTRY,
					us, request);
			return mapping.findForward("generateEntry");
		}
		
		genForm.setTask("");
		
		genForm.setSearchField(genForm.getCurrSearchField());
		
		int rowCount;
		genForm.setArrList(genManager.getGeneralHoliday(
				genForm.getCurrSearchField(), genForm.getCurrSearchValue(), genForm.getCurrSearchValue2(),
				genForm.getCurrPage(), Constant.PAGE_SIZE));
		
		rowCount = genManager.getCountGeneralHoliday(genForm.getCurrSearchField(),
				genForm.getCurrSearchValue(), genForm.getCurrSearchValue2());

		genForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.PAGE_SIZE));		
		
		CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_HOLIDAY, us,
				request);
		
		request.setAttribute("pageTitle", "General Holiday");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(genForm.getPageCount(),genForm.getCurrPage()));
		
		request.setAttribute("pageCount", genForm.getPageCount());
		request.setAttribute("currPage", genForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("genList");
	}
}
