package project;

import java.util.List;

import org.apache.struts.action.ActionForm;

import project_member.ProjectMemberBean;

public class ProjectInvolvedForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String task ="";
	private Integer selectedId;
	private String isProc = "";
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String val;
	private List<ProjectBean> listOfProjectInvolved;
	private ProjectBean projectBean = new ProjectBean();
	private List<ProjectMemberBean> arrMember;
	
	public List<ProjectMemberBean> getArrMember() {
		return arrMember;
	}
	public void setArrMember(List<ProjectMemberBean> arrMember) {
		this.arrMember = arrMember;
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
	public List<ProjectBean> getListOfProjectInvolved() {
		return listOfProjectInvolved;
	}
	public void setListOfProjectInvolved(List<ProjectBean> listOfProjectInvolved) {
		this.listOfProjectInvolved = listOfProjectInvolved;
	}
	public ProjectBean getProjectBean() {
		return projectBean;
	}
	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}
}
