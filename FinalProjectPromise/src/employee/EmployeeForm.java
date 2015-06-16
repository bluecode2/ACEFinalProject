package employee;

import java.util.List;

import org.apache.struts.action.ActionForm;

import department.DepartmentBean;

public class EmployeeForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private List<EmployeeBean> listOfEmployee;
	private EmployeeBean selectedEmp  = new EmployeeBean();
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<EmployeeBean> getListOfEmployee() {
		return listOfEmployee;
	}

	public void setListOfEmployee(List<EmployeeBean> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
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

	public EmployeeBean getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(EmployeeBean selectedEmp) {
		this.selectedEmp = selectedEmp;
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
	
}
