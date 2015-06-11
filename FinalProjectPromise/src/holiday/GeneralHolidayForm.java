package holiday;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralHolidayForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GeneralHolidayBean> arrList = new ArrayList<GeneralHolidayBean>();
	private String task="";

	public ArrayList<GeneralHolidayBean> getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList<GeneralHolidayBean> arrList) {
		this.arrList = arrList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}
