package audit_trail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditTrailBean {
	SimpleDateFormat df = new SimpleDateFormat();
	private Integer auditTrailId;
	private Integer userId;
	private String tableName;
	private String fieldName;
	private String oldValue;
	private String newValue;
	private Date updateDate;
	private String updateDateInString;
	private Integer updateBy;
	
	public Integer getAuditTrailId() {
		return auditTrailId;
	}
	public void setAuditTrailId(Integer auditTrailId) {
		this.auditTrailId = auditTrailId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
		} catch (ParseException pe){
			pe.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
}
