package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralParamForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GeneralParamBean> arrList = new ArrayList<GeneralParamBean>();
	private String task = "";
	private Integer selectedId = 0;
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	
	private GeneralParamBean bean = new GeneralParamBean();

	public ArrayList<GeneralParamBean> getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList<GeneralParamBean> arrList) {
		this.arrList = arrList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public GeneralParamBean getBean() {
		return bean;
	}

	public void setBean(GeneralParamBean bean) {
		this.bean = bean;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getCurrSearchValue() {
		return currSearchValue;
	}

	public void setCurrSearchValue(String currSearchValue) {
		this.currSearchValue = currSearchValue;
	}

	public String getCurrSearchField() {
		return currSearchField;
	}

	public void setCurrSearchField(String currSearchField) {
		this.currSearchField = currSearchField;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
