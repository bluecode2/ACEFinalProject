package proposed_task;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import employee.EmployeeBean;

public class ApproveTaskForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String task = "";
	private Integer selectedId = 0;
	private Boolean isAdd = false;
	private Integer empId;
	private Integer assignTo;
	private List<ProposedTaskBean> arrList = new ArrayList<ProposedTaskBean>();
	private ProposedTaskBean bean  = new ProposedTaskBean();
	private List <EmployeeBean> eBean = new ArrayList<EmployeeBean>();
	private String propTo;
	
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer currPage = 1;
	private Integer pageCount = 1;
	private String remarksRecord;
	
	public String getRemarksRecord() {
		return remarksRecord;
	}
	public void setRemarksRecord(String remarksRecord) {
		this.remarksRecord = remarksRecord;
	}
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
	public List<ProposedTaskBean> getArrList() {
		return arrList;
	}
	public void setArrList(List<ProposedTaskBean> arrList) {
		this.arrList = arrList;
	}
	public ProposedTaskBean getBean() {
		return bean;
	}
	public void setBean(ProposedTaskBean bean) {
		this.bean = bean;
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
		if(this.pageCount.intValue()==0)
			return 1;
		else
			return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPropTo() {
		return propTo;
	}
	public void setPropTo(String propTo) {
		this.propTo = propTo;
	}
	public List <EmployeeBean> geteBean() {
		return eBean;
	}
	public void seteBean(List <EmployeeBean> eBean) {
		this.eBean = eBean;
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
	
}
