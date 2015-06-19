package department;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class DepartmentBean {
	private Integer deptId;
	private Integer deptHeadId;
	private Integer createdBy;
	private Integer updatedBy;
	private String deptCode;
	private String deptName;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private Integer isDeleted;

	private String deptHeadCode;
	private String deptHeadName;
	private String deptHeadDisplay;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(Integer deptHeadId) {
		this.deptHeadId = deptHeadId;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
			pe.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeptHeadCode() {
		return deptHeadCode;
	}

	public void setDeptHeadCode(String deptHeadCode) {
		this.deptHeadCode = deptHeadCode;
	}

	public String getDeptHeadName() {
		return deptHeadName;
	}

	public void setDeptHeadName(String deptHeadName) {
		this.deptHeadName = deptHeadName;
	}
}
