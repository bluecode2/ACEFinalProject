package reports;

import general.GeneralCodeManager;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import project.ProjectManager;
import user.UserBean;
import common.CommonFunction;
import common.Constant;
import department.DepartmentManager;
import employee.EmployeeManager;

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
		DepartmentManager dMan = new DepartmentManager();
		GeneralCodeManager genCodeMan = new GeneralCodeManager();
		EmployeeManager eMan = new EmployeeManager();
		ProjectManager pMan = new ProjectManager();
		
		rForm.setListOfDept(dMan.getListDepartmentForSearchDialog("", ""));
		rForm.setListOfGenCode(genCodeMan.getGeneralCodeByParentId("PR_STAT"));
		request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPopUp());
		request.setAttribute("lstProject", pMan.getAllProjectForPopUp());
		
		if("selectReport".equals(rForm.getTask())){
			ReportBean bean  = rMan.getReportById(rForm.getSelectedId());
			PrintWriter out = response.getWriter();
			out.println(bean.getFilterPanel().trim());
			
			return null;
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
