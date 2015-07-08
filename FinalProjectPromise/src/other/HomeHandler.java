package other;

import holiday.GeneralHolidayBean;
import holiday.GeneralHolidayManager;
import holiday.PersonalHolidayBean;
import holiday.PersonalHolidayManager;
import independent_task.IndependentTaskManager;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;

public class HomeHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		HomeForm hmForm = (HomeForm) form;
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		hmForm.setUsername(us.getEmployeeName());
		
		GeneralHolidayManager ghManager = new GeneralHolidayManager();
		PersonalHolidayManager phManager = new PersonalHolidayManager();

//		if(hmForm.getCurrentMonth() == null && hmForm.getCurrentYear() == null){
//			Calendar now = Calendar.getInstance();
//			hmForm.setCurrentMonth(now.get(Calendar.MONTH)+1);
//			hmForm.setCurrentYear(now.get(Calendar.YEAR));
//		}
		
		
		
		if(hmForm.getTask().equals("fetchHoliday")){
			StringBuilder strBuilderGeneral = new StringBuilder();
			StringBuilder strBuilderPersonal = new StringBuilder();
			StringBuilder strBuilderLegend = new StringBuilder();
			
			List<GeneralHolidayBean> lstGeneralHoliday = ghManager.getGeneralHolidayForCalendar(hmForm.getCurrentMonth(), hmForm.getCurrentYear());
			List<PersonalHolidayBean> lstPersonalHoliday = phManager.getPersonalHolidayForCalendar(hmForm.getCurrentMonth(), hmForm.getCurrentYear(),us.getEmployeeId());
			List<String> lstLegend = new ArrayList<String>();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
			SimpleDateFormat df2 = new SimpleDateFormat("dd MMMM");
			
			for (GeneralHolidayBean genBean : lstGeneralHoliday) {
				if(!strBuilderGeneral.toString().equals(""))strBuilderGeneral.append("#");
				
				strBuilderGeneral.append(df.format(genBean.getGenHolidayDate()));
				if(genBean.getIsGenerated().intValue() == 0)
					lstLegend.add("<li title='" + df2.format(genBean.getGenHolidayDate()) + "' style='color:red'>" + df2.format(genBean.getGenHolidayDate()) + " : " + genBean.getGenHolidayName() + "</li>");
			}
			
			for (PersonalHolidayBean perBean : lstPersonalHoliday) {
				if(!strBuilderPersonal.toString().equals(""))strBuilderPersonal.append("#");
				
				strBuilderPersonal.append(df.format(perBean.getHolidayDate()));
				lstLegend.add("<li title='" + df2.format(perBean.getHolidayDate()) + "' style='color:#01DF01'>" + df2.format(perBean.getHolidayDate()) + " : " + perBean.getHolidayDesc() + "</li>");
			}
			
			Collections.sort(lstLegend);
			
			for (String string : lstLegend) {
				strBuilderLegend.append(string);
			}
			
			PrintWriter out = response.getWriter();
			out.println(strBuilderGeneral.toString() + "|" + strBuilderPersonal.toString() + "|" + strBuilderLegend.toString());
			
			hmForm.setTask("");
			
			return null;
		}
		
		
		ProjectManager pMan = new ProjectManager();		
		hmForm.setListProjBean(pMan.getProjForHome(us.getEmployeeId(), 1, 5));
		hmForm.setAvgProjProg(pMan.avgProjProg(us.getEmployeeId()));
		
		IndependentTaskManager iTaskMan = new IndependentTaskManager();
		hmForm.setListTaskBean(iTaskMan.getListForIndividualTask(1, 5, us.getEmployeeId()));
		hmForm.setAvgTaskProg(iTaskMan.getAvgTaskProg(us.getEmployeeId()));
		
		CommonFunction.createAllowedMenu(us, request);
		
		
		
		return mapping.findForward("index");
	}
}
