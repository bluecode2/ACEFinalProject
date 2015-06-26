package propose_project_task;

import independent_task.IndependentTaskManager;

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

public class ApprovePropProjHandler extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ApprovePropProjTaskForm aPropPForm = (ApprovePropProjTaskForm) form;
		ApprovePropProjManager aPropPMan = new ApprovePropProjManager();
		
		IndependentTaskManager iTaskMan = new IndependentTaskManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");

		CommonFunction.initializeHeader(Constant.MenuCode.APPROVE_PROPOSED_INDEPENDENT_TASK, us, request);
		
		if ("approve".equalsIgnoreCase(aPropPForm.getTask())){
			aPropPForm.setBean(aPropPMan.getApproveTaskById(aPropPForm.getSelectedId()));
			aPropPForm.getBean().setCreatedBy(us.getUserId());
			aPropPForm.getBean().setPropBy(aPropPForm.getAssignTo());
			aPropPForm.getBean().setTaskId(iTaskMan.getNewTaskId());

			aPropPMan.createNewAssignTaskMap(aPropPForm.getBean());
			aPropPMan.approveTask(aPropPForm.getBean());
			
			response.sendRedirect("appPropProjTask.do");
			return null;
		}
		else if ("decline".equalsIgnoreCase(aPropPForm.getTask())){
			aPropPForm.getBean().setUpdatedBy(us.getUserId());
			aPropPForm.getBean().setPropTaskId(aPropPForm.getSelectedId());
			aPropPMan.declineTask(aPropPForm.getBean());

			response.sendRedirect("approveTask.do");
			return null;
		}
		
		
		aPropPForm.setTask("");
		aPropPForm.setSearchField(aPropPForm.getCurrSearchField());
		aPropPForm.setSearchValue(aPropPForm.getCurrSearchValue());

		int rowCount;
		
		aPropPForm.setArrList(aPropPMan.getAllPropTask(
				aPropPForm.getCurrSearchField(), aPropPForm.getCurrSearchValue(),
				aPropPForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));
		rowCount = aPropPMan.getCountAllPropTask(aPropPForm.getCurrSearchField(),
				aPropPForm.getCurrSearchValue(), us.getEmployeeId());
		
		aPropPForm.seteBean(aPropPMan.getEmployeeBySpvId(us.getEmployeeId()));
		
		aPropPForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(aPropPForm.getPageCount(),
						aPropPForm.getCurrPage()));

		request.setAttribute("pageCount", aPropPForm.getPageCount());
		request.setAttribute("currPage", aPropPForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("AppPropProjTaskList");
	}
}
