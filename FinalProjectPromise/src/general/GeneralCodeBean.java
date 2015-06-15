package general;

import java.util.Date;

public class GeneralCodeBean {

	private String 	genCodeId;
	private String 	genCodeCaption;
	private String 	parentId;
	private Integer isActive;
	private Integer genCodeIndex;
	private Date 	updateDate;
	private Integer updateBy;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
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
