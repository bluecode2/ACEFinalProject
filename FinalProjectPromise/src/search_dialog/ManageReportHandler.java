package search_dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import reports.ReportBean;
import reports.ReportManager;

public class ManageReportHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ManageReportForm mrForm = (ManageReportForm) form;
		
		String listReportId = "";
		Integer userRoleID = mrForm.getSelectedId();
		ReportManager rtMan = new ReportManager();
		
		List<ReportBean> arrList =rtMan.getListReports() ;
		
		for (ReportBean rpBean : arrList) {
			
		}
		
		return null;
	}
}
