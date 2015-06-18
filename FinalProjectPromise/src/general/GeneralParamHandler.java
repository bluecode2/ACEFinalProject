package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class GeneralParamHandler extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		GeneralParamManager gpm = new GeneralParamManager();
		GeneralParamForm gpf = (GeneralParamForm) form;
		
		CommonFunction.createAllowedMenu(null, request);

		System.out.println(gpf.getTask());
		
		if("add".equals(gpf.getTask())){
			/*empForm.getEmpBean().setEmpId(empManager.generateIdEmp());
			empForm.setListOfJobs(empManager.getJobId());
			empForm.setListOfEmp(empManager.getEmployees());
			empForm.setListOfDept(empManager.getDeptId());
			
			request.setAttribute("ljob", empManager.getJobId());*/
			
			gpf.setIsAdd(true);
			request.setAttribute("pageTitle", "General Parameter Entry");
			
			return mapping.findForward("entry");
		}
		else if ("save".equals(gpf.getTask())){
			Boolean isAdd = gpf.getIsAdd();
			
			if (isAdd) {
				gpf.getBean().setGenParamId(gpm.getMaxParamId());
				gpm.insertGeneralParam(gpf.getBean());
			} 
			else {
				gpf.getBean().setUpdatedBy(1);
				gpm.updateGeneralParam(gpf.getBean());
			}

			response.sendRedirect("generalParam.do");
			return null;
		}
		else if ("edit".equals(gpf.getTask())) {
			request.setAttribute("pageTitle", "General Parameter Edit");
			gpf.setBean(gpm.getGenParamByParamId(gpf.getSelectedId().toString()));

			System.out.println("IRENE GILAA");
			
			return mapping.findForward("entry");
		}
		else if ("delete".equals(gpf.getTask())) {
			System.out.println(gpf.getSelectedId());
			gpf.getBean().setUpdatedBy(1);
			gpm.deleteGeneralParam(gpf.getBean());
			System.out.println("selesai");
		}
		
		gpf.setTask("");
		gpf.setSearchField(gpf.getCurrSearchField());
		gpf.setSearchValue(gpf.getCurrSearchValue());
		
		int rowCount;
		gpf.setArrList(gpm.getAllGeneralParam(
				gpf.getCurrSearchField(), gpf.getCurrSearchValue(),
				gpf.getCurrPage(), Constant.pageSize));
		
		
		rowCount = gpm.getCountGeneralParam(gpf.getCurrSearchField(),
				gpf.getCurrSearchValue());
		
		gpf.setPageCount((int) Math.ceil((double) rowCount/(double) Constant.pageSize));
		
		request.setAttribute("pageTitle", "General Param List");
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList( gpf.getPageCount(),gpf.getCurrPage()));
		
		request.setAttribute("pageCount", gpf.getPageCount());
		request.setAttribute("currPage", gpf.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("list");
	}
	
}
