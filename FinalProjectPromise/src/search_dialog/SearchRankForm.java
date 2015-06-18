package search_dialog;

import org.apache.struts.action.ActionForm;

public class SearchRankForm extends ActionForm{
	private String 	searchField;
	private String 	searchValue;
	private int		rankId;
	
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
	public int getRankId() {
		return rankId;
	}
	public void setRankId(int rankId) {
		this.rankId = rankId;
	}
	
}
