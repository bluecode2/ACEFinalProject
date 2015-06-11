package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralCodeForm extends ActionForm {

	private ArrayList<GeneralCodeBean> arrCodeBean = new ArrayList<GeneralCodeBean>();
	private String task;
	private String column;
	private String input;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ArrayList<GeneralCodeBean> getArrCodeBean() {
		return arrCodeBean;
	}

	public void setArrCodeBean(ArrayList<GeneralCodeBean> arrCodeBean) {
		this.arrCodeBean = arrCodeBean;
	}

}
