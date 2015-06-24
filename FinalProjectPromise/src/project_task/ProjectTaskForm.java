package project_task;

import independent_task.IndependentTaskBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import activity.ActivityBean;
import project.ProjectBean;

public class ProjectTaskForm extends ActionForm
{
	private String 			task = "";
	private String 			searchField;
	private String 			searchValue;
	private Integer 		currPage = 1;
	private Integer 		pageCount = 0;
	private Integer 		listCount;
	private String			currSearchField;
	private String			currSearchValue;
	private Integer			selectedId;
	private Integer 		projectId;
	private ProjectBean prjBean = null;
	private ProjectTaskBean		tkBean = new ProjectTaskBean();
	private List<ProjectTaskBean>	arrList = new ArrayList<ProjectTaskBean>();
	private Boolean 		isAdd = false;
	private int				selectedEdit;
	private String			statusTask;
	private String			remarksRecord;
	private Integer			empId;
	private List<ActivityBean> arrActivity;
	

	public List<ActivityBean> getArrActivity() {
		return arrActivity;
	}
	public void setArrActivity(List<ActivityBean> arrActivity) {
		this.arrActivity = arrActivity;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getRemarksRecord() {
		return remarksRecord;
	}
	public void setRemarksRecord(String remarksRecord) {
		this.remarksRecord = remarksRecord;
	}
	public String getStatusTask() {
		return statusTask;
	}
	public void setStatusTask(String statusTask) {
		this.statusTask = statusTask;
	}
	public int getSelectedEdit() {
		return selectedEdit;
	}
	public void setSelectedEdit(int selectedEdit) {
		this.selectedEdit = selectedEdit;
	}
	public Boolean getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}
	public List<ProjectTaskBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<ProjectTaskBean> arrList) {
		this.arrList = arrList;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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
	public Integer getListCount() {
		return listCount;
	}
	public void setListCount(Integer listCount) {
		this.listCount = listCount;
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
	public Integer getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
	public ProjectTaskBean getTkBean() {
		return tkBean;
	}
	public void setTkBean(ProjectTaskBean tkBean) {
		this.tkBean = tkBean;
	}
	public ProjectBean getPrjBean() {
		return prjBean;
	}
	public void setPrjBean(ProjectBean prjBean) {
		this.prjBean = prjBean;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
}

