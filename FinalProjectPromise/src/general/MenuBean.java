package general;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuBean {
	private Integer menuId;
	private Integer updatedBy;
	private Integer createdBy;
	private String menuCode;
	private String menuCaption;
	private Integer parentId;
	private String menuCrud;
	private Integer menuIndex;
	private String menuUrl;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private Integer isParent;
	private String parentName;
	private String createdByName;
	private String updatedByName;
	
	
	private Boolean isAllowAdd;
	private Boolean isAllowBack;
	private Boolean isAllowSave;
	private Boolean isAllowApprove;
	private Boolean isAllowDecline;
	
	SimpleDateFormat df = new SimpleDateFormat(common.Constant.StringFormat.dateFormat);

	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuCaption() {
		return menuCaption;
	}
	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getMenuCrud() {
		return menuCrud;
	}
	public void setMenuCrud(String menuCrud) {
		this.menuCrud = menuCrud;
	}
	public Integer getMenuIndex() {
		return menuIndex;
	}
	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	public String getCreateDateInString() {
		return createDateInString;
	}
	public void setCreateDateInString(String createDateInString) {
		this.createDateInString = createDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(createDateInString);
		} catch (Exception e) {
			//e.printStackTrace();
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
			//e.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public Integer getIsParent() {
		return isParent;
	}
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
	public Boolean getIsAllowAdd() {
		return menuCrud.contains("C");
	}
	public Boolean getIsAllowBack() {
		return isAllowBack;
	}
	public Boolean getIsAllowSave() {
		return isAllowSave;
	}
	public Boolean getIsAllowApprove() {
		return isAllowApprove;
	}
	public Boolean getIsAllowDecline() {
		return isAllowDecline;
	}
	
	
}
