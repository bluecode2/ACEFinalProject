package general;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class GeneralParamBean {
	private String 	genParamId;
	private String 	genParamDesc;
	private String 	genParamValue;
	private Integer isActive;
	private Integer updatedBy;
	private Date 	updateDate;
	private String 	updateDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.DATE_FORMAT);
	
	public String getGenParamId() {
		return genParamId;
	}
	public void setGenParamId(String genParamId) {
		this.genParamId = genParamId;
	}
	public String getGenParamDesc() {
		return genParamDesc;
	}
	public void setGenParamDesc(String genParamDesc) {
		this.genParamDesc = genParamDesc;
	}
	public String getGenParamValue() {
		return genParamValue;
	}
	public void setGenParamValue(String genParamValue) {
		this.genParamValue = genParamValue;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
}
