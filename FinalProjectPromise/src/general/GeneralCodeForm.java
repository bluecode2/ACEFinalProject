package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralCodeForm extends ActionForm {

	private ArrayList<GeneralCodeBean> arrCodeBean = new ArrayList<GeneralCodeBean>();
	private String 			task;
	private String 			column;
	private String 			input;
	private Integer 		hal = 1;
	private Integer 		pageCount;
	private GeneralCodeBean genCodeBean = new GeneralCodeBean();
	private Integer 		listCount;
	private boolean			isadd;
	
	public boolean isIsadd() {
		return isadd;
	}

	public void setIsadd(boolean isadd) {
		this.isadd = isadd;
	}

	public Integer getListCount() {
		return listCount;
	}

	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}

	public GeneralCodeBean getGenCodeBean() {
		return genCodeBean;
	}

	public void setGenCodeBean(GeneralCodeBean genCodeBean) {
		this.genCodeBean = genCodeBean;
	}

	public Integer getHal() {
		return hal;
	}

	public void setHal(Integer hal) {
		this.hal = hal;
	}

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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
