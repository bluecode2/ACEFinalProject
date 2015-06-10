package user;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuBean {
	private Integer menuId;
	private Integer updatedBy;
	private Integer createdBy;
	private String menuCode;
	private String menuCaption;
	private String parentId;
	private String menuCrud;
	private Integer menuIndex;
	private String menuUrl;
	private Date createDate;
	private Date updateDate;
	
	
	private String createDateInString;
	private String updateDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat();

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * @return the menuCaption
	 */
	public String getMenuCaption() {
		return menuCaption;
	}

	/**
	 * @param menuCaption the menuCaption to set
	 */
	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the menuCrud
	 */
	public String getMenuCrud() {
		return menuCrud;
	}

	/**
	 * @param menuCrud the menuCrud to set
	 */
	public void setMenuCrud(String menuCrud) {
		this.menuCrud = menuCrud;
	}

	/**
	 * @return the menuIndex
	 */
	public Integer getMenuIndex() {
		return menuIndex;
	}

	/**
	 * @param menuIndex the menuIndex to set
	 */
	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}

	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createDateInString
	 */
	public String getCreateDateInString() {
		return createDateInString;
	}

	/**
	 * @param createDateInString the createDateInString to set
	 */
	public void setCreateDateInString(String createDateInString) {
		this.createDateInString = createDateInString;
	}

	/**
	 * @return the updateDateInString
	 */
	public String getUpdateDateInString() {
		return updateDateInString;
	}

	/**
	 * @param updateDateInString the updateDateInString to set
	 */
	public void setUpdateDateInString(String updateDateInString) {
		this.updateDateInString = updateDateInString;
	}

	/**
	 * @return the df
	 */
	public SimpleDateFormat getDf() {
		return df;
	}

	/**
	 * @param df the df to set
	 */
	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}


	
}
