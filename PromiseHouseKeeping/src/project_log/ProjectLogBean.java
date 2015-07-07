package project_log;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ProjectLogBean {
	private Integer projectLogId;
	private Integer projectId;
	private Integer updateBy;
	private String projectField;
	private String projectOldValue;
	private String projectNewValue;
	private Date updateDate;
	private String remarks;
	private String changeType;
	private String changeTypeInString;
	
	private String updateDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);
	
	public Integer getProjectLogId() {
		return projectLogId;
	}
	public void setProjectLogId(Integer projectLogId) {
		this.projectLogId = projectLogId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public String getProjectField() {
		return projectField;
	}
	public void setProjectField(String projectField) {
		this.projectField = projectField;
	}
	public String getProjectOldValue() {
		return projectOldValue;
	}
	public void setProjectOldValue(String projectOldValue) {
		this.projectOldValue = projectOldValue;
	}
	public String getProjectNewValue() {
		return projectNewValue;
	}
	public void setProjectNewValue(String projectNewValue) {
		this.projectNewValue = projectNewValue;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		if (updateDate != null){
			this.updateDateInString = df.format(updateDate.getTime());
		} else {
			this.updateDateInString = "";
		}
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getUpdateDateInString() {
		return updateDateInString;
	}
	public void setUpdateDateInString(String updateDateInString) {
		this.updateDateInString = updateDateInString;
		Date date = new Date();
		try {
			date = df.parse(updateDateInString);
		} catch (Exception pe){
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public String getChangeTypeInString() {
		return changeTypeInString;
	}
	public void setChangeTypeInString(String changeTypeInString) {
		this.changeTypeInString = changeTypeInString;
	}
	
	
}
