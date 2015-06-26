package notification;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class NotificationBean {
	private Integer notificationId;
	private Integer employeeId;
	private String notificationDesc;
	private Date notificationDate;
	private Integer isRead;
	private Date readDate;
	private String notificationUrl;
	private String sessionParameter;
	
	private String notificationDateInString;
	private String readDateInString;

	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
		if (notificationDate != null) {
			this.notificationDateInString = df.format(notificationDate.getTime());
		} else {
			this.notificationDateInString = "";
		}
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
		if (readDate != null) {
			this.readDateInString = df.format(readDate.getTime());
		} else {
			this.readDateInString = "";
		}
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	public String getSessionParameter() {
		return sessionParameter;
	}

	public void setSessionParameter(String sessionParameter) {
		this.sessionParameter = sessionParameter;
	}
	
	public String getReadDateInString() {
		return readDateInString;
	}

	public void setReadDateInString(String readDateInString) {
		this.readDateInString = readDateInString;
		Date date = new Date();
		try {
			date = df.parse(readDateInString);
		} catch (Exception pe) {
			//pe.printStackTrace();
			this.readDateInString = "";
			date = null;
		}
		this.readDate = date;
	}
	
	public String getNotificationDateInString() {
		return notificationDateInString;
	}

	public void setNotificationDateInString(String notificationDateInString) {
		this.notificationDateInString = notificationDateInString;
		Date date = new Date();
		try {
			date = df.parse(notificationDateInString);
		} catch (Exception pe) {
			//pe.printStackTrace();
			this.notificationDateInString = "";
			date = null;
		}
		this.notificationDate = date;
	}
}
