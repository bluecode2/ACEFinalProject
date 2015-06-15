package general;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class GeneralCodeBean {

	private String 	genCodeId;
	private String 	genCodeCaption;
	private String 	parentId;
	private Integer isActive;
	private Integer genCodeIndex;
	private Date 	updateDate;
	private Integer updatedBy;
	private String	updateDateInString;
	
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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
			e.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		
		if (updateDate!= null) {
			this.updateDateInString = df.format(updateDate.getTime());
		} else {
			this.updateDateInString = "";
		}
	}

	public String getGenCodeId() {
		return genCodeId;
	}

	public void setGenCodeId(String genCodeId) {
		this.genCodeId = genCodeId;
	}

	public String getGenCodeCaption() {
		return genCodeCaption;
	}

	public void setGenCodeCaption(String genCodeCaption) {
		this.genCodeCaption = genCodeCaption;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getGenCodeIndex() {
		return genCodeIndex;
	}

	public void setGenCodeIndex(Integer genCodeIndex) {
		this.genCodeIndex = genCodeIndex;
	}

}
