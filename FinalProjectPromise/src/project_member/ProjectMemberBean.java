package project_member;

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
}
