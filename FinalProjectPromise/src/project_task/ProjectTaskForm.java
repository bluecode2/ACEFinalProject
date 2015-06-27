package project_task;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import project.ProjectBean;
import propose_project_task.ProposeProjectTaskBean;
import activity.ActivityBean;

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
	private Integer 		testingId =0;
	private String showDiv = "true";
	
	//penambahan fitur pada form untuk propose project task
	private Boolean allowAdd = false;
	private List<ProposeProjectTaskBean> arrListProp = new ArrayList<ProposeProjectTaskBean>();
	private ProposeProjectTaskBean bean  = new ProposeProjectTaskBean();
	private String rankIdDisplay;
	private Integer selectTaskId;
	private String taskForProp;
	
	public String getTaskForProp() {
		return taskForProp;
	}
	public void setTaskForProp(String taskForProp) {
		this.taskForProp = taskForProp;
	}
	public Integer getSelectTaskId() {
		return selectTaskId;
	}
	public void setSelectTaskId(Integer selectTaskId) {
		this.selectTaskId = selectTaskId;
	}
	

	public Boolean getAllowAdd() {
		return allowAdd;
	}
	public void setAllowAdd(Boolean allowAdd) {
		this.allowAdd = allowAdd;
	}
	public List<ProposeProjectTaskBean> getArrListProp() {
		return arrListProp;
	}
	public void setArrListProp(List<ProposeProjectTaskBean> arrListProp) {
		this.arrListProp = arrListProp;
	}
	public ProposeProjectTaskBean getBean() {
		return bean;
	}
	public void setBean(ProposeProjectTaskBean bean) {
		this.bean = bean;
	}
	public String getRankIdDisplay() {
		return rankIdDisplay;
	}
	public void setRankIdDisplay(String rankIdDisplay) {
		this.rankIdDisplay = rankIdDisplay;
	}
	public String getShowDiv() {
		return showDiv;
	}
	public void setShowDiv(String showDiv) {
		this.showDiv = showDiv;
	}
	public Integer getTestingId() {
		return testingId;
	}
	public void setTestingId(Integer testingId) {
		this.testingId = testingId;
	}
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

