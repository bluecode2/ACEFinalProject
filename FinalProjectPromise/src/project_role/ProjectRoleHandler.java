package project_role;

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

public class ProjectRoleHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectRoleManager prm = new ProjectRoleManager();
		ProjectRoleForm prf = (ProjectRoleForm) form;
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		CommonFunction.createAllowedMenu(us, request);

		if("add".equals(prf.getTask())){
			/*empForm.getEmpBean().setEmpId(empManager.generateIdEmp());
			empForm.setListOfJobs(empManager.getJobId());
			empForm.setListOfEmp(empManager.getEmployees());
			empForm.setListOfDept(empManager.getDeptId());
			
			request.setAttribute("ljob", empManager.getJobId());*/
			
			prf.setIsAdd(true);
			request.setAttribute("pageTitle", "Project Role Entry");
			
			return mapping.findForward("entry");
		}
		else if ("save".equals(prf.getTask())){
			
			prf.getProjectRoleBean().setUpdatedBy(us.getUserId());
			prm.updateProjectRole(prf.getProjectRoleBean());
			
			response.sendRedirect("generalParam.do");
			return null;
		}
		else if ("edit".equals(prf.getTask())) {
			request.setAttribute("pageTitle", "General Parameter Edit");
			prf.setProjectRoleBean(prm.getProjectRoleById(prf.getSelectedId()));
			
			System.out.println(prf.getProjectRoleBean().getProjectRoleId());

			return mapping.findForward("entry");
		}
		else if ("delete".equals(prf.getTask())) {
			System.out.println(prf.getSelectedId());
			prf.getProjectRoleBean().setUpdatedBy(us.getUserId());
			prm.deleteProjectRole(prf.getProjectRoleBean());
			System.out.println("selesai");
		}
		
		prf.setTask("");
		prf.setSearchField(prf.getCurrSearchField());
		prf.setSearchValue(prf.getCurrSearchValue());
		
		int rowCount;
		prf.setArrList(prm.getAllProjectRole(
				prf.getCurrSearchField(), prf.getCurrSearchValue(),
				prf.getCurrPage(), Constant.pageSize));
		
		
		rowCount = prm.getCountGeneralHoliday(prf.getCurrSearchField(),
				prf.getCurrSearchValue());
		
		prf.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
		
		request.setAttribute("pageTitle", "General Param List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList( prf.getPageCount(),prf.getCurrPage()));
		
		request.setAttribute("pageCount", prf.getPageCount());
		request.setAttribute("currPage", prf.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("list");
	}
}
