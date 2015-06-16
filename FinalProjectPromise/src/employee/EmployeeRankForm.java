package employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class EmployeeRankForm extends ActionForm  {
	
	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private List<RankEmpBean> arrList = new ArrayList<RankEmpBean>();
	private RankEmpBean selectedDept  = new RankEmpBean();
	
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String rankIdDisplay;
	
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}

	public Integer getSelectedId() {
		return selectedId;
	}
	
	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
	
	public Boolean getIsAdd() {
		return isAdd;
	}
	
	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}
	
	public List<RankEmpBean> getArrList() {
		return arrList;
	}
	
	public void setArrList(List<RankEmpBean> arrList) {
		this.arrList = arrList;
	}
	
	public RankEmpBean getSelectedDept() {
		return selectedDept;
	}
	
	public void setSelectedDept(RankEmpBean selectedDept) {
		this.selectedDept = selectedDept;
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
	
	public String getRankIdDisplay() {
		return rankIdDisplay;
	}
	
	public void setRankIdDisplay(String rankIdDisplay) {
		this.rankIdDisplay = rankIdDisplay;
	}

	

}
