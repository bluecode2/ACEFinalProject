package project;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class ProjectBean {
	private Integer 	projectId;
	private Integer 	updatedBy;
	private Integer 	dept_id;
	private String 		deptName;
	private Integer 	employeeId;
	private Integer 	createdBy;
	private String 		projectCode;
	private String 		projectName;
	private String 		projectDesc;
	private Date 		estStartDate;
	private String 		estStartDateInString;
	private String 		estStartDateDisplay;
	private Date 		estEndDate;
	private String 		estEndDateInString;
	private String 		estEndDateDisplay;
	private Date 		actStartDate;
	private String 		actStartDateInString;
	private String 		actStartDateDisplay;
	private Date 		actEndDate;
	private String 		actEndDateInString;
	private String 		actEndDateDisplay;
	private Integer 	estMainDays;
	private Integer 	actMainDays;
	private Integer 	taskMainDays;
	private String 		projectStatus;
	private Float 		projectProgress;
	private String 		remarks;
	private Date 		createDate;
	private String 		createDateInString;
	private Date 		updateDate;
	private String 		updateDateInString;
	private String 		employeeName;
	private String 		statusCaption;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);
	SimpleDateFormat df2 = new SimpleDateFormat(Constant.StringFormat2.DATE_FORMAT);
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStatusCaption() {
		return statusCaption;
	}

	public void setStatusCaption(String statusCaption) {
		this.statusCaption = statusCaption;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public String getActStartDateInString() {
		return actStartDateInString;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
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

	public Date getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(Date actStartDate) {
		this.actStartDate = actStartDate;
		
		if (actStartDate != null) {
			this.actStartDateInString = df.format(actStartDate.getTime());
		}else{
			this.actStartDateInString = "";
		}
	}

	public Date getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
		
		if (actEndDate != null) {
			this.actEndDateInString = df.format(actEndDate.getTime());
		}else{
			this.actEndDateInString = "";
		}
	}

	public Integer getEstMainDays() {
		return estMainDays;
	}

	public void setEstMainDays(Integer estMainDays) {
		this.estMainDays = estMainDays;
	}

	public Integer getActMainDays() {
		return actMainDays;
	}

	public void setActMainDays(Integer actMainDays) {
		this.actMainDays = actMainDays;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Float getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(Float projectProgress) {
		this.projectProgress = projectProgress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getEstStartDateInString() {
		return estStartDateInString;
	}

	public void setEstStartDateInString(String estStartDateInString) {
		this.estStartDateInString = estStartDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(estStartDateInString);
		} catch (Exception e) {
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
			this.estEndDateInString = "";
			date = null;
		}
		this.estEndDate = date;
	}

	public String getActStartDateDateInString() {
		return actStartDateInString;
	}

	public void setActStartDateInString(String actStartDateInString) {
		this.actStartDateInString = actStartDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(actStartDateInString);
		} catch (Exception e) {
			this.actStartDateInString = "";
			date = null;
		}
		this.actStartDate = date;
	}

	public String getActEndDateInString() {
		return actEndDateInString;
	}

	public void setActEndDateInString(String actEndDateInString) {
		this.actEndDateInString = actEndDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(actEndDateInString);
		} catch (Exception e) {
			this.actEndDateInString = "";
			date = null;
		}
		this.actEndDate = date;
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

	public Integer getTaskMainDays() {
		return taskMainDays;
	}

	public void setTaskMainDays(Integer taskMainDays) {
		this.taskMainDays = taskMainDays;
	}
	
	public String getEstStartDateDisplay() {
		try {
			return df2.format(estStartDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}

	public String getEstEndDateDisplay() {
		try {
			return df2.format(estEndDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}

	public String getActStartDateDisplay() {
		try {
			return df2.format(actStartDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}

	public String getActEndDateDisplay() {
		try {
			return df2.format(actEndDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}
}
