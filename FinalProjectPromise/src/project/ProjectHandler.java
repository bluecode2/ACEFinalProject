package project;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;
import employee.EmployeeForm;
import employee.EmployeeManager;

public class ProjectHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectForm pForm = (ProjectForm) form;
		ProjectManager pMan = new ProjectManager();
		CommonFunction.createAllowedMenu(null, request);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();
		
		if ("add".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("add");
			pForm.setSelectedId(0);
			pMan.insertProject(pForm.getpBean());
			request.setAttribute("lstEmployeeId", eMan.getAllEmployee(
					eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
					eForm.getCurrPage(), Constant.pageSize));
			request.setAttribute("pageTitle", "Project Entry");
			
			return mapping.findForward("projectEntry");
		}
		else if ("edit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("edit");
			request.setAttribute("pageTitle", "Project Edit");
			pForm.setpBean(pMan.getUserByUserID(pForm.getSelectedId()));
			
			return mapping.findForward("projectEntry");
		}
		else if ("start".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("start");
			pForm.setpBean(pMan.getUserByUserID(pForm.getSelectedId()));
			pForm.getpBean().setProjectStatus("PR_STAT_02");
			pForm.getpBean().setActStartDateInString(sdf.format(date));
			
			pMan.updateProject(pForm.getpBean());
			
		}
		else if ("onGoing".equalsIgnoreCase(pForm.getTask())){
			//bisa pause bisa submit
			
		}
		else if ("onHold".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("onHold");
			
		}
		else if ("forceClose".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("forceClose");
			pForm.setpBean(pMan.getUserByUserID(pForm.getSelectedId()));
			request.setAttribute("pageTitle", "Project Force Close");
			
			return mapping.findForward("projectEntry");
		}
		else if ("cancel".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsProc("cancel");
			pForm.setpBean(pMan.getUserByUserID(pForm.getSelectedId()));
			request.setAttribute("pageTitle", "Project Cancel");
			
			return mapping.findForward("projectEntry");
			
		}
		else if ("save".equalsIgnoreCase(pForm.getTask())){
			String isProc = pForm.getIsProc();
			if (isProc.equalsIgnoreCase("add")) {
				pMan.insertProject(pForm.getpBean());
			} 
			else if (isProc.equalsIgnoreCase("edit")){
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("forceClose")){
				pForm.getpBean().setActStartDate(sdf.parse(pForm.getpBean().getActStartDateDateInString()));
				pForm.getpBean().setActEndDate(date);
				Integer actMainDays = pForm.getpBean().getActStartDate().getDate() - pForm.getpBean().getActEndDate().getDate();
				pForm.getpBean().setActMainDays(actMainDays);
				
				pForm.getpBean().setProjectStatus("PR_STAT_06");
				pMan.updateProject(pForm.getpBean());
			}
			else if (isProc.equalsIgnoreCase("cancel")){
				pForm.getpBean().setProjectStatus("PR_STAT_99");
				pMan.updateProject(pForm.getpBean());
			}
			else {
				
			}

			response.sendRedirect("project.do");
			return null;
		}
		
		
		pForm.setTask("");
		pForm.setSearchField(pForm.getCurrSearchField());
		pForm.setSearchValue(pForm.getCurrSearchValue());

		int rowCount;
		rowCount = pMan.getCountProject(pForm.getCurrSearchField(),
				pForm.getCurrSearchValue());
		
		pForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
		System.out.println("pageCount end");
		pForm.setListOfProject(pMan.getAllProject(
				pForm.getCurrSearchField(), pForm.getCurrSearchValue(),
				pForm.getCurrPage(), Constant.pageSize));
		System.out.println("isi list selesai");
		
		
		request.setAttribute("pageTitle", "Project List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(1,1));

		request.setAttribute("pageCount", 1);
		request.setAttribute("currPage", 1);
		request.setAttribute("rowCount", 1);
		
		return mapping.findForward("projectList");
		
	}
}
