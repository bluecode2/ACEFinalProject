package reports;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class ReportForm extends ActionForm {
	private String task;
	private Integer selectedId;

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	

}
