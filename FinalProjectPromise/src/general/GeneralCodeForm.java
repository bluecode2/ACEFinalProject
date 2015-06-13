package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralCodeForm extends ActionForm {

	private ArrayList<GeneralCodeBean> arrCodeBean = new ArrayList<GeneralCodeBean>();
	private String 			task;
	private String 			searchField;
	private String 			searchValue;
	private Integer 		hal = 1;
	private Integer 		pageCount;
	private GeneralCodeBean genCodeBean = new GeneralCodeBean();
	private Integer 		listCount;
	private boolean			isadd;
	private String			currSearchField;
	private String			currSearchValue;
	
	public String getCurrSearchField() {
		return currSearchField;
	}

	public void setCurrSearchField(String currSearchField) {
		this.currSearchField = currSearchField;
	}

	public String getCurrSearchValue() {
		return currSearchValue;
	}

	public void setCurrSearchValue(String currSearchValue) {
		this.currSearchValue = currSearchValue;
	}

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

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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
