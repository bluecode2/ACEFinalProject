package search_dialog;

import org.apache.struts.action.ActionForm;

public class SearchUserRoleForm extends ActionForm{

	private Integer userRoleID;
	private String searchField;
	private String searchValue;
	
	
	public Integer getUserRoleID() {
		return userRoleID;
	}
	public void setUserRoleID(Integer userRoleID) {
		this.userRoleID = userRoleID;
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
}
