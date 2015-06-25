package propose_project_task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectBean;
import project.ProjectManager;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;
import user.UserBean;

public class ProposeProjectTaskHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		ProposeProjectTaskForm pProjTaskForm = (ProposeProjectTaskForm) form;
		ProposeProjectTaskManager pProjtaskMan = new ProposeProjectTaskManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		Integer projId = 2;

		ProjectManager pMan = new ProjectManager();
		ProjectBean pBean = new ProjectBean();
		pBean = pMan.getProjectByID(projId);
		
		request.setAttribute("getProject", pBean);
		
		if ("add".equalsIgnoreCase(pProjTaskForm.getTask())){
			pProjTaskForm.setIsAdd(true);
			pProjTaskForm.setSelectedId(0);
			request.setAttribute("pageTitle", "Proposed Project Task Entry");
			CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK_ENTRY, us, request);

			return mapping.findForward("propProjTaskEntry");
		}
		else if ("edit".equalsIgnoreCase(pProjTaskForm.getTask())){
			pProjTaskForm.setIsAdd(false);
			CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK_ENTRY, us, request);
			request.setAttribute("pageTitle", "Proposed Project Task Edit");
			pProjTaskForm.setBean(pProjtaskMan.getPropProjTaskByTaskId(pProjTaskForm.getSelectedId()));
			
			return mapping.findForward("propProjTaskEntry");
		}
		else if ("delete".equalsIgnoreCase(pProjTaskForm.getTask())){
			
		}

		pProjTaskForm.setTask("");
		pProjTaskForm.setSearchField(pProjTaskForm.getCurrSearchField());
		pProjTaskForm.setSearchValue(pProjTaskForm.getCurrSearchValue());
		request.setAttribute("pageTitle", "Proposed Project Task");
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean empBean = empMan.getEmployeeByEmpId(us.getEmployeeId());
		pProjTaskForm.setAllowAdd(empBean.getSupervisorId() != null);
		
		pProjTaskForm.setArrList(pProjtaskMan.getAllPropProjTask(
				pProjTaskForm.getCurrSearchField(), pProjTaskForm.getCurrSearchValue(),
				pProjTaskForm.getCurrPage(), Constant.pageSize,projId));
		
		int rowCount;
		
		rowCount = pProjtaskMan.getCountPropProjTask(pProjTaskForm.getCurrSearchField(),
				pProjTaskForm.getCurrSearchValue(), us.getEmployeeId());
		
		pProjTaskForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(pProjTaskForm.getPageCount(),
						pProjTaskForm.getCurrPage()));
		
		CommonFunction.initializeHeader(Constant.MenuCode.PROPOSE_INDEPENDENT_TASK, us, request);

		request.setAttribute("pageCount", pProjTaskForm.getPageCount());
		request.setAttribute("currPage", pProjTaskForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("propProjTaskList");
	}
}
