package department;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class DepartmentForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private List<DepartmentBean> arrList = new ArrayList<DepartmentBean>();
	private DepartmentBean selectedDept  = new DepartmentBean();
	
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String deptHeadDisplay;
	
	public List<DepartmentBean> getArrList() {
		return arrList;
	}

	public void setArrList(List<DepartmentBean> arrList) {
		this.arrList = arrList;
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

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public DepartmentBean getSelectedDept() {
		return selectedDept;
	}

	public void setSelectedDept(DepartmentBean selectedDept) {
		this.selectedDept = selectedDept;
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

	public String getDeptHeadDisplay() {
		return deptHeadDisplay;
	}

	public void setDeptHeadDisplay(String deptHeadDisplay) {
		this.deptHeadDisplay = deptHeadDisplay;
	}
}
