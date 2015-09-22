package proposed_task;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ProposedTaskBean {
	private Integer propTaskId;
	private String propTaskName;
	private String propTaskDesc;
	private Date estStartDate;
	private String estStartDateInString;
	private String estStartDateDisplay;
	private Date estEndDate;
	private String estEndDateInString;
	private String estEndDateDisplay;
	private Integer propBy;
	private String propByName;
	private Integer propTo;
	private String propToName;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private Integer taskId;
	private String propStatus;
	private String propStatusName;
	private Integer estMainDays;
	private String remarks;
	private String projectName;
	private String projectId;
	
	SimpleDateFormat df2 = new SimpleDateFormat(Constant.StringFormat2.DATE_FORMAT);
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the propStatusName
	 */
	public String getPropStatusName() {
		return propStatusName;
	}

	/**
	 * @param propStatusName the propStatusName to set
	 */
	public void setPropStatusName(String propStatusName) {
		this.propStatusName = propStatusName;
	}

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);

	public Integer getPropTaskId() {
		return propTaskId;
	}

	public void setPropTaskId(Integer propTaskId) {
		this.propTaskId = propTaskId;
	}

	public String getPropTaskName() {
		return propTaskName;
	}

	public void setPropTaskName(String propTaskName) {
		this.propTaskName = propTaskName;
	}

	public String getPropTaskDesc() {
		return propTaskDesc;
	}

	public void setPropTaskDesc(String propTaskDesc) {
		this.propTaskDesc = propTaskDesc;
	}

	public Date getEstStartDate() {
		return estStartDate;
	}

	public void setEstStartDate(Date estStartDate) {
		this.estStartDate = estStartDate;
		if (estStartDate != null) {
			this.estStartDateInString = df.format(estStartDate.getTime());
		} else {
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
		} else {
			this.estEndDateInString = "";
		}
	}

	public Integer getPropBy() {
		return propBy;
	}

	public void setPropBy(Integer propBy) {
		this.propBy = propBy;
	}

	public Integer getPropTo() {
		return propTo;
	}

	public void setPropTo(Integer propTo) {
		this.propTo = propTo;
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
		} catch (Exception pe) {
		
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
		} catch (Exception pe) {
		
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getPropStatus() {
		return propStatus;
	}

	public void setPropStatus(String propStatus) {
		this.propStatus = propStatus;
	}

	
	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

	public String getEstStartDateInString() {
		return estStartDateInString;
	}

	public void setEstStartDateInString(String estStartDateInString) {
		this.estStartDateInString = estStartDateInString;
		Date date = new Date();
		try {
			date = df.parse(estStartDateInString);
		} catch (Exception pe) {
	
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
		} catch (Exception pe) {

			this.estEndDateInString = "";
			date = null;
		}
		this.estEndDate = date;
	}

	public String getPropByName() {
		return propByName;
	}

	public void setPropByName(String propByName) {
		this.propByName = propByName;
	}

	public String getPropToName() {
		return propToName;
	}

	public void setPropToName(String propToName) {
		this.propToName = propToName;
	}

	public Integer getEstMainDays() {
		return estMainDays;
	}

	public void setEstMainDays(Integer estMainDays) {
		this.estMainDays = estMainDays;
	}

	public String getEstStartDateDisplay() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return df2.format(estStartDate);
	}

	public String getEstEndDateDisplay() {
		try {
			return df2.format(estEndDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}	
}
