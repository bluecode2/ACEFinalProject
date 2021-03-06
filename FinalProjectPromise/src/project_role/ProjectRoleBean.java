package project_role;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ProjectRoleBean {
	private Integer projectRoleId;
	private Integer updatedBy;
	private Integer createdBy;
	private String projectRoleName;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private String projectRoleCode;
	private Integer isDeleted;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);
	
	public Integer getProjectRoleId() {
		return projectRoleId;
	}
	public void setProjectRoleId(Integer projectRoleId) {
		this.projectRoleId = projectRoleId;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getProjectRoleName() {
		return projectRoleName;
	}
	public void setProjectRoleName(String projectRoleName) {
		this.projectRoleName = projectRoleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;

		if (createDate != null) {
			this.createDateInString = df.format(createDate.getTime());
		} else {
			this.createDateInString = "";
		}
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
			// TODO Auto-generated catch block
			this.createDateInString = "";
			date = null;
		}
		this.createDate = date;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;

		if (updateDate != null) {
			this.updateDateInString = df.format(updateDate.getTime());
		} else {
			this.updateDateInString = "";
		}
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
			// TODO Auto-generated catch block
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public String getProjectRoleCode() {
		return projectRoleCode;
	}
	public void setProjectRoleCode(String projectRoleCode) {
		this.projectRoleCode = projectRoleCode;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
