package search_dialog;

import org.apache.struts.action.ActionForm;

public class SearchEmpAssignedToForm extends ActionForm {

	private Integer spvId;
	private String searchField;
	private String searchValue;
	
	public Integer getSpvId() {
		return spvId;
	}
	public void setSpvId(Integer spvId) {
		this.spvId = spvId;
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
