package task;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class TaskForm extends ActionForm
{
	private String 			task;
	private String 			searchField;
	private String 			searchValue;
	private Integer 		currPage = 1;
	private Integer 		pageCount = 0;
	private Integer 		listCount;
	private String			currSearchField;
	private String			currSearchValue;
	private String			selectedId;
	private TaskBean		tkBean;
	private List<TaskBean>	arrList = null;
	
	public List<TaskBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<TaskBean> arrList) {
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
	public String getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	public TaskBean getTkBean() {
		return tkBean;
	}
	public void setTkBean(TaskBean tkBean) {
		this.tkBean = tkBean;
	}
	
}
