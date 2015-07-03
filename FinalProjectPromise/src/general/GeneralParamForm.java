package general;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class GeneralParamForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GeneralParamBean> arrList = new ArrayList<GeneralParamBean>();
	private String task = "";
	private String selectedId = "";
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount;
	private Boolean isAdd = false;
	
	private GeneralParamBean bean = new GeneralParamBean();

	public List<GeneralParamBean> getArrList() {
		return arrList;
	}

	public void setArrList(List<GeneralParamBean> arrList) {
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

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
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
		if(this.pageCount.intValue()==0)
			return 1;
		else
			return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}
	
	
}
