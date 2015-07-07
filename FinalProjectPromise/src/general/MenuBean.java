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

	SimpleDateFormat df = new SimpleDateFormat(
			common.Constant.StringFormat.DATE_FORMAT);

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
		if (menuCrud != null) {
			this.isAllowAdd = menuCrud.contains("C");
			this.isAllowBack = menuCrud.contains("B");
			this.isAllowSave = menuCrud.contains("U");
			this.isAllowApprove = menuCrud.contains("A");
			this.isAllowDecline = menuCrud.contains("D");
		}
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
		} else {
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
		} else {
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

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public Boolean getIsAllowAdd() {
		return isAllowAdd;
	}

	public void setIsAllowAdd(Boolean isAllowAdd) {
		this.isAllowAdd = isAllowAdd;
	}

	public Boolean getIsAllowBack() {
		return isAllowBack;
	}

	public void setIsAllowBack(Boolean isAllowBack) {
		this.isAllowBack = isAllowBack;
	}

	public Boolean getIsAllowSave() {
		return isAllowSave;
	}

	public void setIsAllowSave(Boolean isAllowSave) {
		this.isAllowSave = isAllowSave;
	}

	public Boolean getIsAllowApprove() {
		return isAllowApprove;
	}

	public void setIsAllowApprove(Boolean isAllowApprove) {
		this.isAllowApprove = isAllowApprove;
	}

	public Boolean getIsAllowDecline() {
		return isAllowDecline;
	}

	public void setIsAllowDecline(Boolean isAllowDecline) {
		this.isAllowDecline = isAllowDecline;
	}

}
