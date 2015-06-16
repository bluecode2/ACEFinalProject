package general;


import java.util.List;

import org.apache.struts.action.ActionForm;

public class GeneralCodeForm extends ActionForm {

	private List<GeneralCodeBean> arrList;
	private String 			task;
	private String 			searchField;
	private String 			searchValue;
	private Integer 		currPage = 1;
	private Integer 		pageCount;
	private GeneralCodeBean genCodeBean = new GeneralCodeBean();
	private Integer 		listCount;
	private String			currSearchField;
	private String			currSearchValue;
	private String			selectedId;
	
	public List<GeneralCodeBean> getArrList() {
		return arrList;
	}

	public void setArrList(List<GeneralCodeBean> arrList) {
		this.arrList = arrList;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}

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


	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
}
