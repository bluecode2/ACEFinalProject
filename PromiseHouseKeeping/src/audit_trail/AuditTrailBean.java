package audit_trail;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class AuditTrailBean {
	
	private Integer auditTrailId;
	private Integer updatedBy;
	private String tableName;
	private String fieldName;
	private String oldValue;
	private String newValue;
	private String changeType;
	private String dataId;
	private Date updateDate;
	private String updateDateInString;
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	SimpleDateFormat sdf = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	public Integer getAuditTrailId() {
		return auditTrailId;
	}
	public void setAuditTrailId(Integer auditTrailId) {
		this.auditTrailId = auditTrailId;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		if (updateDate != null){
			this.updateDateInString = sdf.format(updateDate.getTime());
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
			date = sdf.parse(updateDateInString);
		} catch (Exception pe){
			
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
}
