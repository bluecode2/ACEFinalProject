package propose_project_task;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import proposed_task.ProposedTaskBean;
import employee.EmployeeBean;

public class ApprovePropProjTaskForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private Integer empId;
	private Integer assignTo;
	private List<ProposeProjectTaskBean> arrList = new ArrayList<ProposeProjectTaskBean>();
	private ProposeProjectTaskBean bean  = new ProposeProjectTaskBean();
	private List <EmployeeBean> eBean = new ArrayList<EmployeeBean>();
	private String propTo;
	
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	
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
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(Integer assignTo) {
		this.assignTo = assignTo;
	}
	public List<ProposeProjectTaskBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<ProposeProjectTaskBean> arrList) {
		this.arrList = arrList;
	}
	public ProposeProjectTaskBean getBean() {
		return bean;
	}
	public void setBean(ProposeProjectTaskBean bean) {
		this.bean = bean;
	}
	public List<EmployeeBean> geteBean() {
		return eBean;
	}
	public void seteBean(List<EmployeeBean> eBean) {
		this.eBean = eBean;
	}
	public String getPropTo() {
		return propTo;
	}
	public void setPropTo(String propTo) {
		this.propTo = propTo;
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
	
}
