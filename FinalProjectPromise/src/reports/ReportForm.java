package reports;

import general.GeneralCodeBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import project.ProjectBean;
import department.DepartmentBean;

public class ReportForm extends ActionForm {
	private String task;
	private Integer selectedId;
	
	//untuk tampilan drop down
	private List<DepartmentBean> listOfDept;
	private Integer deptId;
	private List<GeneralCodeBean> listOfGenCode;
	private Integer genCodeId;
	private Integer empId;
	private String empName;
	private Integer projId;
	private String projName;
	
	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public List<GeneralCodeBean> getListOfGenCode() {
		return listOfGenCode;
	}

	public void setListOfGenCode(List<GeneralCodeBean> listOfGenCode) {
		this.listOfGenCode = listOfGenCode;
	}

	public Integer getGenCodeId() {
		return genCodeId;
	}

	public void setGenCodeId(Integer genCodeId) {
		this.genCodeId = genCodeId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public List<DepartmentBean> getListOfDept() {
		return listOfDept;
	}

	public void setListOfDept(List<DepartmentBean> listOfDept) {
		this.listOfDept = listOfDept;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	

}
