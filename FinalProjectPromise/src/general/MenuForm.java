package general;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class MenuForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 			task;
	private String 			searchField;
	private String 			searchValue;
	private String 			currSearchValue;
	private String 			currSearchField;
	private MenuBean 		mnBean;
	private List<MenuBean> 	arrList;
	private Integer 		currPage;
	private Integer 		pageCount;
	private Integer 		listCount;
	
	public Integer getListCount() {
		return listCount;
	}
	public void setListCount(Integer listCount) {
		this.listCount = listCount;
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
	public MenuBean getMnBean() {
		return mnBean;
	}
	public void setMnBean(MenuBean mnBean) {
		this.mnBean = mnBean;
	}
	public List<MenuBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<MenuBean> arrList) {
		this.arrList = arrList;
	}
	
}
