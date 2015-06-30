package project;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import user.UserBean;

public class ProjectForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private String task ="";
	private Integer selectedId = 0;
	private String isProc = "";
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String val;
	private List<ProjectBean> listOfProject;
	private ProjectBean pBean = new ProjectBean();
	private String dateInString;

	public String getDateInString() {
		return dateInString;
	}
	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}
	public ProjectBean getpBean() {
		return pBean;
	}
	public void setpBean(ProjectBean pBean) {
		this.pBean = pBean;
	}
	public List<ProjectBean> getListOfProject() {
		return listOfProject;
	}
	public void setListOfProject(List<ProjectBean> listOfProject) {
		this.listOfProject = listOfProject;
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
	
	public String getIsProc() {
		return isProc;
	}
	public void setIsProc(String isProc) {
		this.isProc = isProc;
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
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
	
}
