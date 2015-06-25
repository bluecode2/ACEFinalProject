package propose_project_task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		
		pProjTaskForm.setTask("");
		pProjTaskForm.setSearchField(pProjTaskForm.getCurrSearchField());
		pProjTaskForm.setSearchValue(pProjTaskForm.getCurrSearchValue());
		request.setAttribute("pageTitle", "Proposed Task");
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean empBean = empMan.getEmployeeByEmpId(us.getEmployeeId());
		pProjTaskForm.setAllowAdd(empBean.getSupervisorId() != null);
		
		int rowCount;

		pProjTaskForm.setArrList(pProjtaskMan.getAllPropProjTask(
				pProjTaskForm.getCurrSearchField(), pProjTaskForm.getCurrSearchValue(),
				pProjTaskForm.getCurrPage(), Constant.pageSize, us.getEmployeeId()));
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
