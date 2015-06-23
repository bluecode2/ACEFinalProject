package project_member;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ProjectMemberBean {
	private Integer memberId;
	private Integer projectRoleId;
	private Integer employeeId;
	private Integer projectId;
	private String projCode;
	private String projName;
	private String empName;
	private String projRoleName;
	private String projRoleCode;
	private Integer isDeleted;
	private String estStartDateInString;
	private String estEndDateInString;
	private Date estStartDate;
	private Date estEndDate;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
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
	public String getProjRoleName() {
		return projRoleName;
	}
	public void setProjRoleName(String projRoleName) {
		this.projRoleName = projRoleName;
	}
	public String getProjRoleCode() {
		return projRoleCode;
	}
	public void setProjRoleCode(String projRoleCode) {
		this.projRoleCode = projRoleCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProjectRoleId() {
		return projectRoleId;
	}
	public void setProjectRoleId(Integer projectRoleId) {
		this.projectRoleId = projectRoleId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getEstStartDateInString() {
		return estStartDateInString;
	}

	public void setEstStartDateDateInString(String estStartDateInString) {
		this.estStartDateInString = estStartDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(estStartDateInString);
		} catch (Exception e) {
			e.printStackTrace();
			this.estStartDateInString = "";
			date = null;
		}
		this.estStartDate = date;
	}

	public String getEstEndDateInString() {
		return estEndDateInString;
	}

	public void setEstEndDateInString(String estEndDateInString) {
		this.estEndDateInString = estEndDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(estEndDateInString);
		} catch (Exception e) {
			e.printStackTrace();
			this.estEndDateInString = "";
			date = null;
		}
		this.estEndDate = date;
	}
	
	public Date getEstStartDate() {
		return estStartDate;
	}

	public void setEstStartDate(Date estStartDate) {
		this.estStartDate = estStartDate;
		
		if (estStartDate != null) {
			this.estStartDateInString = df.format(estStartDate.getTime());
		}else{
			this.estStartDateInString = "";
		}
	}

	public Date getEstEndDate() {
		return estEndDate;
	}

	public void setEstEndDate(Date estEndDate) {
		this.estEndDate = estEndDate;
		
		if (estEndDate != null) {
			this.estEndDateInString = df.format(estEndDate.getTime());
		}else{
			this.estEndDateInString = "";
		}
	}
}
