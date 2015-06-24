package independent_task;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import activity.ActivityBean;

public class AssignTaskForm extends ActionForm
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
	private IndependentTaskBean		tkBean = new IndependentTaskBean();
	private List<IndependentTaskBean>	arrList = new ArrayList<IndependentTaskBean>();
	private Boolean 		isAdd = false;
	private int				selectedEdit;
	private String			statusTask;
	private String			remarksRecord;
	private List<ActivityBean> arrActivity;

	public List<ActivityBean> getArrActivity() {
		return arrActivity;
	}
	public void setArrActivity(List<ActivityBean> arrActivity) {
		this.arrActivity = arrActivity;
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
	public List<IndependentTaskBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<IndependentTaskBean> arrList) {
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
	public IndependentTaskBean getTkBean() {
		return tkBean;
	}
	public void setTkBean(IndependentTaskBean tkBean) {
		this.tkBean = tkBean;
	}
	
}
