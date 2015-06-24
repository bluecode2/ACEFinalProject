package projectApproval;

import java.util.List;

import org.apache.struts.action.ActionForm;

import project.ProjectBean;
import project_member.ProjectMemberBean;
import project_task.ProjectTaskBean;

public class ProjectApprovalForm extends ActionForm{

	private String 				task;
	private String 				currSearchField;
	private String 				currSearchValue;
	private String 				searchValue;
	private String 				searchField;
	private int					currPage=1;
	private int					pageCount=1;
	private List<ProjectBean> 	arrList;
	private ProjectBean 		pBean;
	private List<ProjectMemberBean> arrMember;
	private int					selectedId;
	private int					selectedProjectId;
	private List<ProjectTaskBean> arrTask;
	private String 				remarksRecord;
	
	public String getRemarksRecord() {
		return remarksRecord;
	}
	public void setRemarksRecord(String remarksRecord) {
		this.remarksRecord = remarksRecord;
	}
	public List<ProjectTaskBean> getArrTask() {
		return arrTask;
	}
	public void setArrTask(List<ProjectTaskBean> arrTask) {
		this.arrTask = arrTask;
	}
	public int getSelectedProjectId() {
		return selectedProjectId;
	}
	public void setSelectedProjectId(int selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}
	public int getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}
	public List<ProjectMemberBean> getArrMember() {
		return arrMember;
	}
	public void setArrMember(List<ProjectMemberBean> arrMember) {
		this.arrMember = arrMember;
	}
	public List<ProjectBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<ProjectBean> arrList) {
		this.arrList = arrList;
	}
	public ProjectBean getpBean() {
		return pBean;
	}
	public void setpBean(ProjectBean pBean) {
		this.pBean = pBean;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
