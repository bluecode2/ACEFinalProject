package task_log;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class TaskLogBean {
	private Integer taskLogId;
	private Integer taskId;
	private Integer updateBy;
	private String taskField;
	private String taskOldValue;
	private String taskNewValue;
	private Date updateDate;
	private String remarks;
	private String changeType;
	private String changeTypeInString;
	
	private String updateDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);

	public Integer getTaskLogId() {
		return taskLogId;
	}

	public void setTaskLogId(Integer taskLogId) {
		this.taskLogId = taskLogId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getTaskField() {
		return taskField;
	}

	public void setTaskField(String taskField) {
		this.taskField = taskField;
	}

	public String getTaskOldValue() {
		return taskOldValue;
	}

	public void setTaskOldValue(String taskOldValue) {
		this.taskOldValue = taskOldValue;
	}

	public String getTaskNewValue() {
		return taskNewValue;
	}

	public void setTaskNewValue(String taskNewValue) {
		this.taskNewValue = taskNewValue;
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
