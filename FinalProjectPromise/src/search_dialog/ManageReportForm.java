package search_dialog;

import org.apache.struts.action.ActionForm;

public class ManageReportForm extends ActionForm{
	private Integer selectedId;
	private String	task;
	private String listReportId;

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
