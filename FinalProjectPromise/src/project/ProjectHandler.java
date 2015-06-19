package project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class ProjectHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ProjectForm pForm = (ProjectForm) form;
		ProjectManager pMan = new ProjectManager();
		CommonFunction.createAllowedMenu(null, request);
		
		if ("add".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsAdd(true);
			pForm.setSelectedId(0);
			request.setAttribute("pageTitle", "Project Entry");
			
			return mapping.findForward("projectEntry");
		}
		else if ("edit".equalsIgnoreCase(pForm.getTask())){
			pForm.setIsAdd(false);
			request.setAttribute("pageTitle", "Project Edit");
			pForm.setpBean(pMan.getUserByUserID(pForm.getSelectedId()));
			
			return mapping.findForward("projectEntry");
		}
		else if ("save".equalsIgnoreCase(pForm.getTask())){
			Boolean isAdd = pForm.getIsAdd();
			if (isAdd) {
				
			} else {

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
