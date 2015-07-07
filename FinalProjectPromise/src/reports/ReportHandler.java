package reports;

import employee.EmployeeBean;
import employee.EmployeeManager;
import general.GeneralCodeManager;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import search_dialog.SearchEmpSubordinateHandler;
import user.UserBean;
import common.CommonFunction;
import common.Constant;

public class ReportHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		ReportForm rForm = (ReportForm) form;
		ReportManager rMan = new ReportManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		//manager
		GeneralCodeManager genCodeMan = new GeneralCodeManager();
		EmployeeManager eMan = new EmployeeManager();
		ProjectManager pMan = new ProjectManager();
		
		//for subordinate employee
		List<EmployeeBean> arrEmp = new ArrayList<EmployeeBean>();
		SearchEmpSubordinateHandler subordinateHandler = new SearchEmpSubordinateHandler();
		subordinateHandler.generateSubordinateList(arrEmp, us.getEmployeeId(), "", "");
		int maxIdx = arrEmp.size() < Constant.PAGE_SIZE ? arrEmp.size() : Constant.PAGE_SIZE;
		
		rForm.setListOfDept(rMan.getListDeptFromUserRole(us.getUserRoleId()));
		rForm.setListOfGenCode(genCodeMan.getGeneralCodeByParentId("PR_STAT"));
		request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPopUp("", ""));
		request.setAttribute("lstSubEmployeeId", arrEmp.subList(0,maxIdx));
		request.setAttribute("lstProject", pMan.getAllProjectForPopUp());

		if("selectReport".equals(rForm.getTask())){
			ReportBean bean  = rMan.getReportById(rForm.getSelectedId());
			PrintWriter out = response.getWriter();
			out.println(bean.getFilterPanel().trim());
			
			return null;
		}
		else if("printReport".equals(rForm.getTask())){
			ReportBean rptBean = rMan.getReportById(rForm.getSelectedId());
			
			session.setAttribute("reportBean", rptBean);
			session.setAttribute("filterValue", rForm.getFilterValue());
			return mapping.findForward("print");
		}

		CommonFunction.initializeHeader(Constant.MenuCode.REPORTS, us, request);
		
		List<ReportBean> listParentReport = rMan.getListParentReportsByUserRole(us.getUserRoleId());
		List<ReportBean> listChildReport = rMan.getListParentReportsByUserRole(us.getUserRoleId());
		
		for (ReportBean rpt : listParentReport) {
			List<ReportBean> tempList = rMan.getListChildReportsByUserRole(us.getUserRoleId(), rpt.getReportId());
			for (ReportBean reportBean : tempList) {
				listChildReport.add(reportBean);
			}
		}
		
		request.setAttribute("parentReport", listParentReport);
		request.setAttribute("childReport", listChildReport);
		
		request.setAttribute("currentEmpId", us.getEmployeeId());
		request.setAttribute("currentEmpName", us.getEmployeeName());
		
		return mapping.findForward("list");
	}
}
