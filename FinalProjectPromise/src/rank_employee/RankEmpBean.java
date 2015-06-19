package rank_employee;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class RankEmpBean {
	
	private Integer rankId;
	private Integer updatedBy;
	private Integer createdBy;
	private String 	rankName;
	private Integer rankLevel;
	private Date 	createDate;
	private String 	createDateInString;
	private Date 	updateDate;
	private String 	updateDateInString;
	private String  rankCode;
	private Integer isDeleted;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	
	public Integer getRankId() {
		return rankId;
	}
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public Integer getRankLevel() {
		return rankLevel;
	}
	public void setRankLevel(Integer rankLevel) {
		this.rankLevel = rankLevel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		
		if (createDate != null) {
			this.createDateInString = df.format(createDate.getTime());
		}
		else{
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
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
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
		}
		else{
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
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public String getRankCode() {
		return rankCode;
	}
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
