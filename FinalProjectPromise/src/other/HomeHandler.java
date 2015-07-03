package other;

import independent_task.IndependentTaskManager;

import java.util.ArrayList;

import general.MenuBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import common.CommonFunction;
import user.UserBean;

public class HomeHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		//MenuManager menuMan = new MenuManager();
		HomeForm hmForm = (HomeForm) form;
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		hmForm.setUsername(us.getEmployeeName());

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
