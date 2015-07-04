package project_task;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import project.ProjectBean;
import project_member.ProjectMemberBean;
import propose_project_task.ProposeProjectTaskBean;
import activity.ActivityBean;

public class ProjectTaskForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String			tmpProjectStatus;
	private String			tmpEstStartDateInString;
	private String			tmpEstEndDateInString;
	private Integer			projectManagerId;
	
	//penambahan fitur pada form untuk propose project task
	private Boolean allowAdd = false;
	private String 			searchField2;
	private String 			searchValue2;
	private Integer 		currPage2 = 1;
	private Integer 		pageCount2 = 0;
	private Integer 		listCount2;
	private String			currSearchField2;
	private String			currSearchValue2;
	private List<ProposeProjectTaskBean> arrListProp = new ArrayList<ProposeProjectTaskBean>();
	private ProposeProjectTaskBean bean  = null;
	private String rankIdDisplay;
	private Integer selectTaskId;
	private String taskForProp;
	private String estStartProj;
	private String estEndProj;
	
	//penambahan fitur pada form untuk approve propose project task
	private Integer assignTo;
	private List <ProjectMemberBean> eBean = new ArrayList<ProjectMemberBean>();
	private String propTo;
	private String remarks;
	private String remarksProp;
	
	
	public String getTmpEstStartDateInString() {
		return tmpEstStartDateInString;
	}
	public void setTmpEstStartDateInString(String tmpEstStartDateInString) {
		this.tmpEstStartDateInString = tmpEstStartDateInString;
	}
	public String getTmpEstEndDateInString() {
		return tmpEstEndDateInString;
	}
	public void setTmpEstEndDateInString(String tmpEstEndDateInString) {
		this.tmpEstEndDateInString = tmpEstEndDateInString;
	}
		
	public String getEstStartProj() {
		return estStartProj;
	}
	public void setEstStartProj(String estStartProj) {
		this.estStartProj = estStartProj;
	}
	public String getEstEndProj() {
		return estEndProj;
	}
	public void setEstEndProj(String estEndProj) {
		this.estEndProj = estEndProj;
	}
	public String getTmpProjectStatus() {
		return tmpProjectStatus;
	}
	public void setTmpProjectStatus(String tmpProjectStatus) {
		this.tmpProjectStatus = tmpProjectStatus;
	}
	public String getRemarksProp() {
		return remarksProp;
	}
	public void setRemarksProp(String remarksProp) {
		this.remarksProp = remarksProp;
	}
	public Integer getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(Integer assignTo) {
		this.assignTo = assignTo;
	}
	public List<ProjectMemberBean> geteBean() {
		return eBean;
	}
	public void seteBean(List<ProjectMemberBean> eBean) {
		this.eBean = eBean;
	}
	public String getPropTo() {
		return propTo;
	}
	public void setPropTo(String propTo) {
		this.propTo = propTo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
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
		if(this.pageCount.intValue()==0)
			return 1;
		else
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
	public String getSearchField2() {
		return searchField2;
	}
	public void setSearchField2(String searchField2) {
		this.searchField2 = searchField2;
	}
	public String getSearchValue2() {
		return searchValue2;
	}
	public void setSearchValue2(String searchValue2) {
		this.searchValue2 = searchValue2;
	}
	public Integer getCurrPage2() {
		return currPage2;
	}
	public void setCurrPage2(Integer currPage2) {
		this.currPage2 = currPage2;
	}
	public Integer getPageCount2() {
		if(this.pageCount2.intValue()==0)
			return 1;
		else
			return pageCount2;
	}
	public void setPageCount2(Integer pageCount2) {
		this.pageCount2 = pageCount2;
	}
	public Integer getListCount2() {
		return listCount2;
	}
	public void setListCount2(Integer listCount2) {
		this.listCount2 = listCount2;
	}
	public String getCurrSearchField2() {
		return currSearchField2;
	}
	public void setCurrSearchField2(String currSearchField2) {
		this.currSearchField2 = currSearchField2;
	}
	public String getCurrSearchValue2() {
		return currSearchValue2;
	}
	public void setCurrSearchValue2(String currSearchValue2) {
		this.currSearchValue2 = currSearchValue2;
	}
	public Integer getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(Integer projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	
}

