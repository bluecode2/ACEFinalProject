package search_dialog;

import org.apache.struts.action.ActionForm;

public class SearchGlobalForm extends ActionForm{

	private Integer globalId;
	private String searchField;
	private String searchValue;
	
	public Integer getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
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
