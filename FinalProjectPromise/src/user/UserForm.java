package user;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String task ="";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private List<UserBean> listOfUser;
	private UserBean uBean = new UserBean();
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String passwordUser;
	
	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public UserBean getuBean() {
		return uBean;
	}

	public void setuBean(UserBean uBean) {
		this.uBean = uBean;
	}

	public List<UserBean> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<UserBean> listOfUser) {
		this.listOfUser = listOfUser;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
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
	
	
	
}
