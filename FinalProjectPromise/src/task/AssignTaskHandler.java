package task;

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

public class AssignTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		AssignTaskForm tsForm = (AssignTaskForm) form;
		AssignTaskManager tsMan = new AssignTaskManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		EmployeeManager empMan = new EmployeeManager();
		tsForm.getTkBean().setAssignedBy(us.getEmployeeId());

		if ("add".equals(tsForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_ENTRY,us, request);
			tsForm.setIsAdd(true);
			request.setAttribute("pageTitle", "Assign Task Entry");
			request.setAttribute("listAssignTo", empMan.getEmpForAssignTask(us.getEmployeeId(),"",""));
			return mapping.findForward("assignTaskEntry");
		}
		else if ("save".equals(tsForm.getTask()	)) {
			Boolean isAdd = tsForm.getIsAdd();

			if (tsForm.getTkBean().getAssignedTo() == 0)
				tsForm.getTkBean().setAssignedTo(null);

			if (isAdd) {
				tsForm.getTkBean().setCreatedBy(us.getUserId());
				tsMan.createNewAssignTask(tsForm.getTkBean());
			} else {
				
				tsForm.getTkBean().setUpdatedBy(us.getUserId());
				tsMan.editAssignTask(tsForm.getTkBean().getTaskId(), tsForm.getTkBean().getTaskName(), tsForm.getTkBean().getTaskDesc(), tsForm.getTkBean().getUpdatedBy());			
			}
			
			response.sendRedirect("assignTaskEntry");
			CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_LIST,
					us, request);
			return null;
		}
		else if ("firstEdit".equals(tsForm.getTask())) {
			tsForm.setIsAdd(false);
			
			if (tsForm.getSelectedEdit() == 0) {
				request.setAttribute("pageTitle", "Assign Entry");
				CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_ENTRY,us, request);
				return mapping.findForward("assignTaskEntry");
			}
			else if (tsForm.getSelectedEdit() == 2) {
				tsForm.setStatusTask("TA_STAT_07");
				tsMan.editStatusAssignTask(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),"");
			}	
		}
		else if ("secondEdit".equals(tsForm.getTask())) {
			if (tsForm.getSelectedEdit() == 0) {
				tsForm.setStatusTask("TA_STAT_99");
				tsMan.editStatusAssignTask(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),tsForm.getRemarksRecord());
				
			}
			else if (tsForm.getSelectedEdit() == 1) {
				tsForm.setStatusTask("TA_STAT_98");
				tsMan.editStatusAssignTask(tsForm.getSelectedId(), us.getUserId(), tsForm.getStatusTask(),tsForm.getRemarksRecord());
			}

		}
		
		

		CommonFunction.initializeHeader(Constant.MenuCode.ASSIGN_TASK_LIST,
				us, request);
		
		tsForm.setTask("");
		tsForm.setSearchField(tsForm.getCurrSearchField());
		tsForm.setSearchValue(tsForm.getCurrSearchValue());
		
		tsForm.setListCount(tsMan.getCountAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue()));
		tsForm.setPageCount((int) Math.ceil((double) tsForm.getListCount() / (double) Constant.pageSize));
		
		tsForm.setArrList(tsMan.getListAssignTask(tsForm.getCurrSearchField(), tsForm.getCurrSearchValue(), tsForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));

		request.setAttribute("pageTitle", "Assign Task List");
		request.setAttribute("pageNavigator", CommonFunction.createPagingNavigatorList(tsForm.getPageCount(), tsForm.getCurrPage()));

		request.setAttribute("pageCount", tsForm.getPageCount());
		request.setAttribute("currPage", tsForm.getCurrPage());
		request.setAttribute("rowCount", tsForm.getListCount());

		return mapping.findForward("assignTaskList");
/*		return mapping.findForward("assignTask");*/
		
		
	}
}
