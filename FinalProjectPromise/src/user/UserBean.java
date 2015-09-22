package user;

import java.text.SimpleDateFormat;
import java.util.Date;
import common.Constant;

public class UserBean {
	private Integer userId;
	private Integer userRoleId;
	private Integer employeeId;
	private Integer createdBy;
	private Integer updatedBy;
	private String 	username;
	private String 	passwordUser;
	private Integer isActive;
	private Integer isActiveDirectory;
	private Date 	createDate;
	private Date 	updateDate;
	private String 	createDateInString;
	private String 	updateDateInString;
	private String	employeeName;
	private String 	userRoleName;
	private Integer	deptId;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);

	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		
		if (createDate != null) {
			this.createDateInString = df.format(createDate.getTime());
		}else{
			this.createDateInString = "";
		}
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		
		if (updateDate != null) {
			this.updateDateInString = df.format(updateDate.getTime());
		}else{
			this.updateDateInString = "";
		}
	}

	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreateDateInString() {
		return createDateInString;
	}
	public void setCreateDateInString(String createDateInString) {
		this.createDateInString = createDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(createDateInString);
		} catch (Exception e) {

			this.createDateInString = "";
			date = null;
		}
		this.createDate = date;
	}
	public String getUpdateDateInString() {
		return updateDateInString;
	}
	public void setUpdateDateInString(String updateDateInString) {
		this.updateDateInString = updateDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(updateDateInString);
		} catch (Exception e) {

			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public Integer getIsActiveDirectory() {
		return isActiveDirectory;
	}
	public void setIsActiveDirectory(Integer isActiveDirectory) {
		this.isActiveDirectory = isActiveDirectory;
	}
}
