package user;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserRoleForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<UserRoleBean> 			arrList = new ArrayList<UserRoleBean>();
	private UserRoleBean 				userRoleBean  = new UserRoleBean();
	private String 						task="";
	private Boolean 					isAdd = false;
	private String 						searchValue;
	private String 						searchField;
	private String 						currSearchValue = "";
	private String 						currSearchField = "";
	private Integer 					currPage = 1;
	private Integer 					pageCount = 1;
	private Integer						selectedId;
	
	public List<UserRoleBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<UserRoleBean> arrList) {
		this.arrList = arrList;
	}
	public UserRoleBean getUserRoleBean() {
		return userRoleBean;
	}
	public void setUserRoleBean(UserRoleBean userRoleBean) {
		this.userRoleBean = userRoleBean;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Boolean getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
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
	public Integer getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
}