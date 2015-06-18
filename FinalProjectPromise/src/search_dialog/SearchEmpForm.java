package search_dialog;

import org.apache.struts.action.ActionForm;

public class SearchEmpForm extends ActionForm {
	
	private String searchField;
	private String searchValue;
	
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
