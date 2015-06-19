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

public class GeneralHolidayHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		GeneralHolidayForm genForm = (GeneralHolidayForm) form;
		GeneralHolidayManager genManager = new GeneralHolidayManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		CommonFunction.createAllowedMenu(us, request);
		
		if("add".equals(genForm.getTask())){
			genForm.setIsAdd(true);
			request.setAttribute("pageTitle", "General Holiday Entry");
			
			return mapping.findForward("genEntry");
		}
		else if ("save".equals(genForm.getTask())){
			Boolean isAdd = genForm.getIsAdd();
			if (isAdd) {
				genForm.getGenHolidayBean().setGenHolidayId(genManager.getNewGenHolidayId());
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
		else if ("edit".equals(genForm.getTask())) {
			request.setAttribute("pageTitle", "General Holiday Edit");
			genForm.setGenHolidayBean(genManager.getGeneralHolidayByHolId(genForm.getSelectedId()));

			return mapping.findForward("genEntry");
		}
		else if ("delete".equals(genForm.getTask())) {
			genForm.getGenHolidayBean().setUpdatedBy(us.getUserId());
			genManager.deleteGeneralHoliday(genForm.getSelectedId());
		}
		else if ("search".equals(genForm.getTask())) {
			System.out.println(genForm.getCurrSearchField() + " "+ genForm.getCurrSearchValue()+" " +genForm.getCurrSearchValue2());
			
			if (genForm.getCurrSearchValue()!= "" && genForm.getCurrSearchValue2() != "") {	
				int rowCount;
				genForm.setArrList(genManager.getGeneralHoliday(
						genForm.getCurrSearchField(), genForm.getCurrSearchValue(), genForm.getCurrSearchValue2(),
						genForm.getCurrPage(), Constant.pageSize));
				
				rowCount = genManager.getCountGeneralHoliday(genForm.getCurrSearchField(),
						genForm.getCurrSearchValue(), genForm.getCurrSearchValue2());

				genForm.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
				request.setAttribute("pageTitle", "General Holiday List");
				
				request.setAttribute("pageNavigator", CommonFunction
						.createPagingNavigatorList(genForm.getPageCount(),genForm.getCurrPage()));
				
				request.setAttribute("pageCount", genForm.getPageCount());
				request.setAttribute("currPage", genForm.getCurrPage());
				request.setAttribute("rowCount", rowCount);
				
				return mapping.findForward("genList");				
			}
			else {
				genForm.setCurrSearchField("");
				System.out.println("salah");
			}

		}

		genForm.setTask("");
		
		genForm.setSearchField(genForm.getCurrSearchField());
//		genForm.setSearchValue(genForm.getCurrSearchValue());
//		genForm.setSearchValue2(genForm.getCurrSearchValue2());
		
		int rowCount;
		genForm.setArrList(genManager.getGeneralHoliday(
				genForm.getCurrSearchField(), genForm.getCurrSearchValue(), genForm.getCurrSearchValue2(),
				genForm.getCurrPage(), Constant.pageSize));
		
		rowCount = genManager.getCountGeneralHoliday(genForm.getCurrSearchField(),
				genForm.getCurrSearchValue(), genForm.getCurrSearchValue2());

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
