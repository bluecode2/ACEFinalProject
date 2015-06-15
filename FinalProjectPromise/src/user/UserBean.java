package user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBean {
	private Integer userId;
	private Integer userRoleId;
	private Integer employeeId;
	private Integer createBy;
	private Integer updateBy;
	private String username;
	private String password;
	private Integer isActive;
	private Date createDate;
	private Date updateDate;
	private String createDateInString;
	private String updateDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat();

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateDateInString() {
		return createDateInString;
	}
	public void setCreateDateInString(String createDateInString) {
		this.createDateInString = createDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(createDateInString);
		} catch (ParseException e) {
			e.printStackTrace();
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
		} catch (ParseException e) {
			e.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
}
