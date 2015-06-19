package holiday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class GeneralHolidayBean {
	private Integer genHolidayId;
	private String genHolidayName;
	private Date genHolidayDate;
	private String genHolidayDateInString;
	private Integer isGenerated;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private Integer isDeleted = 0;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);

	public Integer getGenHolidayId() {
		return genHolidayId;
	}
	public void setGenHolidayId(Integer genHolidayId) {
		this.genHolidayId = genHolidayId;
	}
	public String getGenHolidayName() {
		return genHolidayName;
	}
	public void setGenHolidayName(String genHolidayName) {
		this.genHolidayName = genHolidayName;
	}
	public Date getGenHolidayDate() {
		return genHolidayDate;
	}
	public void setGenHolidayDate(Date genHolidayDate) {
		this.genHolidayDate = genHolidayDate;

		if (genHolidayDate != null) {
			this.genHolidayDateInString = df.format(genHolidayDate.getTime());
		} else {
			this.genHolidayDateInString = "";
		}
	}
	public String getGenHolidayDateInString() {
		return genHolidayDateInString;
	}
	public void setGenHolidayDateInString(String genHolidayDateInString) {
		this.genHolidayDateInString = genHolidayDateInString;

		Date date = new Date();
		try {
			date = df.parse(genHolidayDateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			this.genHolidayDateInString = "";
			date = null;
		}
		this.genHolidayDate = date;
	}
	public Integer getIsGenerated() {
		return isGenerated;
	}
	public void setIsGenerated(Integer isGenerated) {
		this.isGenerated = isGenerated;
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
}
