package project_role;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ProjectRoleForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private List<ProjectRoleBean> arrList;
	private ProjectRoleBean projectRoleBean  = new ProjectRoleBean();
	
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
	public List<ProjectRoleBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<ProjectRoleBean> arrList) {
		this.arrList = arrList;
	}
	public ProjectRoleBean getProjectRoleBean() {
		return projectRoleBean;
	}
	public void setProjectRoleBean(ProjectRoleBean projectRoleBean) {
		this.projectRoleBean = projectRoleBean;
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
