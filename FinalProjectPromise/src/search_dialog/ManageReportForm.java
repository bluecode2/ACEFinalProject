package search_dialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import reports.ReportBean;

public class ManageReportForm extends ActionForm{
	private Integer selectedId;
	private String	task;
	private String listReportId;
	private List<ReportBean> arrList = new ArrayList<ReportBean>();

	public List<ReportBean> getArrList() {
		return arrList;
	}

	public void setArrList(List<ReportBean> arrList) {
		this.arrList = arrList;
	}

	public String getListReportId() {
		return listReportId;
	}

	public void setListReportId(String listReportId) {
		this.listReportId = listReportId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
	
	
}
