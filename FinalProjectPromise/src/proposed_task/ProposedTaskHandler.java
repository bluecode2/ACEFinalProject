package proposed_task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notification.NotificationManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.crystaldecisions.a.d;

import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;
import general.NotificationBean;
import proposed_task.ProposedTaskBean;
import proposed_task.ProposedTaskManager;
import user.UserBean;

public class ProposedTaskHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProposedTaskForm dForm = (ProposedTaskForm) form;
		ProposedTaskManager dMan = new ProposedTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		NotificationManager noMan = new NotificationManager();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dForm.setDateInString(sdf.format(now));

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);
			request.setAttribute("pageTitle", "Proposed Task Entry");
			CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK_ENTRY, us, request);

			return mapping.findForward("proposedTaskEntry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK_ENTRY, us, request);
			request.setAttribute("pageTitle", "Proposed Task Edit");
			dForm.setBean(dMan.getPropTaskByPropTaskId(dForm.getSelectedId()));
			
			return mapping.findForward("proposedTaskEntry");
		}

		else if (dForm.getTask().equals("delete")) {
			dForm.getBean().setUpdatedBy(us.getUserId());
			dForm.setBean(dMan.getPropTaskByPropTaskId(dForm.getSelectedId()));
			noMan.createNotificationHapusProposeTask(us.getEmployeeId(), dForm.getBean().getPropTo(), dForm.getBean().getPropTaskId());
			dMan.deleteProposedTask(dForm.getSelectedId(),us.getUserId());
		}
		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (isAdd) {
				
				dForm.getBean().setCreatedBy(us.getUserId());
				dForm.getBean().setPropBy(us.getEmployeeId());
				int newId = dMan.newPropTaskId();
				dForm.getBean().setPropTaskId(newId);
				dMan.insertProposedTask(dForm.getBean());
				
				dForm.setBean(dMan.getPropTaskByPropTaskId(newId));
				noMan.createNotificationProposeIndependentTask(us.getEmployeeId(), dForm.getBean().getPropTo(), dForm.getBean().getPropTaskId());
			} else {
				dForm.getBean().setUpdatedBy(us.getUserId());
				dMan.updateProposedTask(dForm.getBean());
			}

			response.sendRedirect("proposedTask.do");
			return null;
		}

		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());
		request.setAttribute("pageTitle", "Proposed Task");
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean empBean = empMan.getEmployeeByEmpId(us.getEmployeeId());
		dForm.setAllowAdd(empBean.getSupervisorId() != null);

		int rowCount;

		dForm.setArrList(dMan.getAllPropTaskFiltered(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.PAGE_SIZE, us.getEmployeeId()));
		rowCount = dMan.getCountProposedTask(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue(), us.getEmployeeId());
		
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK, us, request);

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("proposedTaskList");
	}
}