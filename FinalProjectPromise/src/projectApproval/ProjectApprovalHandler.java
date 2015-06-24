package projectApproval;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import activity.ActivityBean;
import activity.ActivityManager;
import project.ProjectManager;
import project_member.ProjectMemberBean;
import project_member.ProjectMemberManager;
import project_task.ProjectTaskBean;
import project_task.ProjectTaskManager;
import search_dialog.SearchDeptHeadForm;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class ProjectApprovalHandler extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectApprovalForm paForm = (ProjectApprovalForm) form;
		ProjectManager paMan = new ProjectManager();
		ProjectMemberManager pmMan = new ProjectMemberManager();
		ProjectTaskManager tsMan = new ProjectTaskManager();
		ActivityManager actMan = new ActivityManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		int rowCount;		
		if ("listMembers".equals(paForm.getTask())) {
			Integer selId = paForm.getSelectedId();
			paForm.setArrMember(pmMan.getProjectMemberToEvaluate(selId));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ProjectMemberBean> arrMember = paForm.getArrMember();
			System.out.println(arrMember.size());
			for (ProjectMemberBean pmBean : arrMember) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + pmBean.getEmpName() + "</td>");
				out.println("<td>" + pmBean.getProjRoleName() + "</td>");
				out.println("</tr>");
			}	

			out.flush();
			return null;
		}
		else if ("listActivity".equals(paForm.getTask())) {
			int selId = paForm.getSelectedId();
			paForm.setArrActivity(actMan.getActivityWithTaskId(selId));
			response.setContentType("text/text;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			
			List<ActivityBean> arrActivity = paForm.getArrActivity();

			System.out.println(arrActivity.size());
			for (ActivityBean actBean : arrActivity) {
				out.println("<tr data-dismiss=\"modal\" class=\"rowSearch\">");
				out.println("<td>" + actBean.getActivityDesc() + "</td>");
				if (actBean.getIsCompleted() == 1) {
					out.println("<td> <input type=\"checkbox\" checked disabled> </td>");					
				}
				else {
					out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");		
				}

				out.println("</tr>");
			}	

			out.flush();
			return null;
		}
		
		else if ("approve".equals(paForm.getTask())) {
			System.out.println("proj id "+paForm.getSelectedId());
			paMan.setApproveProject(paForm.getSelectedId(), us.getUserId());
		}
		
		else if ("decline".equals(paForm.getTask())) {	
			System.out.println(paForm.getSelectedId());
			paMan.setDeclineProject(paForm.getSelectedId(), us.getUserId(), paForm.getRemarksRecord());
		}
		
		else if ("evaluate".equals(paForm.getTask())) {
			System.out.println(paForm.getSelectedId());
			paForm.setpBean(paMan.getProjectByID(paForm.getSelectedId()));
			paForm.setSearchField(paForm.getCurrSearchField());
			paForm.setSearchValue(paForm.getCurrSearchValue());
			
			rowCount = paMan.getCountProjectToEvaluate(paForm.getCurrSearchField(),
					paForm.getCurrSearchValue(),us.getDeptId());
			
			paForm.setPageCount((int) Math.ceil((double) rowCount
					/ (double) Constant.pageSize));
			
			paForm.setArrTask(tsMan.getListProjectTaskByProjectId(paForm.getCurrSearchField(),
					paForm.getCurrSearchValue(), paForm.getCurrPage(),
					Constant.pageSize, paForm.getpBean().getProjectId()));
			List<ProjectTaskBean> arr = paForm.getArrTask();


			CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_APPROVAL_EVALUATE,
					us, request);

			request.setAttribute("pageNavigator", CommonFunction
					.createPagingNavigatorList(paForm.getPageCount(),
							paForm.getCurrPage()));

			request.setAttribute("pageCount", paForm.getPageCount());
			request.setAttribute("currPage", paForm.getCurrPage());
			request.setAttribute("rowCount", rowCount);
			return mapping.findForward("evaluate");
		}
		
		paForm.setTask("");
		paForm.setSearchField(paForm.getCurrSearchField());
		paForm.setSearchValue(paForm.getCurrSearchValue());
		
		System.out.println(us.getDeptId());
		paForm.setArrList(paMan.getListProjectToEvaluate(
				paForm.getCurrSearchField(), paForm.getCurrSearchValue(),
				paForm.getCurrPage(), Constant.pageSize,us.getDeptId()));
		rowCount = paMan.getCountProjectToEvaluate(paForm.getCurrSearchField(),
				paForm.getCurrSearchValue(),us.getDeptId());
		
		paForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		CommonFunction.initializeHeader(Constant.MenuCode.PROJECT_APPROVAL,
				us, request);
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(paForm.getPageCount(),
						paForm.getCurrPage()));

		request.setAttribute("pageCount", paForm.getPageCount());
		request.setAttribute("currPage", paForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}

}
