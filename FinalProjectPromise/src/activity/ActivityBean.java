package activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ActivityBean {
	private Integer activityId;
	private Integer taskId;
	private Integer updatedBy;
	private Integer createdBy;
	private Integer employeeId;
	private String 	activityDesc;
	private Integer isCompleted;
	private Date 	createDate;
	private String 	createDateInString;
	private Date 	updateDate;
	private String 	updateDateInString;
	private String	employeeName;
	private String	taskName;
	private String	taskStatus;
	private String 	employeeCode;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public Integer getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		if (createDate != null){
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
		} catch (Exception pe){
			pe.printStackTrace();
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
		if (updateDate != null){
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
		} catch (Exception pe){
			pe.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

}
