package search_dialog;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import reports.ReportBean;
import reports.ReportManager;
import reports.ReportRoleBean;
import reports.ReportRoleManager;
import user_access.UserRoleMenuBean;
import user_access.UserRoleMenuManager;

public class ManageReportHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ManageReportForm mrForm = (ManageReportForm) form;
		ReportManager rpMan = new ReportManager();
		
		if ("openReportAccess".equals(mrForm.getTask())) {
			String listReportId = "";
			Integer userRoleID = mrForm.getSelectedId();
			

			ReportRoleManager rrMan = new ReportRoleManager();
			
			List<ReportRoleBean> arrList = rrMan.getReportRoleByRoleId(userRoleID);
			
			for (ReportRoleBean rrBean : arrList) {
				if (!listReportId.equals("")) {
					listReportId += "#";
				}
				listReportId += rrBean.getReportId().toString();
			}
			PrintWriter out = response.getWriter();
			String resp = listReportId;
			out.println(resp);
			
	
		}
		else if ("saveReportAccess".equals(mrForm.getTask())) {
			String[] listReportId =  mrForm.getListReportId().split("#");
			
			ReportRoleManager rrMan = new ReportRoleManager();
			Integer userRoleId = mrForm.getSelectedId();
			List<ReportRoleBean> arrReportMenu = rrMan.getReportRoleByRoleId(userRoleId);
			List<ReportRoleBean> arrDeletedRoleMenu = new ArrayList<ReportRoleBean>();
			
			for (ReportRoleBean rrBean : arrReportMenu) {
				if (!Arrays.asList(listReportId).contains(rrBean.getReportId().toString())) {
					arrDeletedRoleMenu.add(rrBean);
				}
			}
	
			for (int i = 0; i < listReportId.length; i++) {
				Integer reportId = Integer.valueOf(listReportId[i]);
				ReportRoleBean bean = rrMan.getReportRoleBean(userRoleId, reportId);
				
				if (bean == null) {
					bean = new ReportRoleBean();
					bean.setReportId(reportId);
					bean.setUserRoleId(userRoleId);
					rrMan.insertUserRoleReport(bean);
				}
			}
			for (ReportRoleBean reportRoleBean : arrDeletedRoleMenu) {
				rrMan.deleteUserRoleReport(reportRoleBean);
			}
		}
		request.setAttribute("lstReport", rpMan.getListReports() );
		return null;
	}
}
