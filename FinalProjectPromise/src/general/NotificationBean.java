package general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationBean {
	private Integer notificationId;
	private Integer employeeId;
	private String notificationDesc;
	private Date notificationDate;
	private String notificationDateInString;
	private Integer isRead;
	private Date readDate;
	private String readDateInString;
	
	SimpleDateFormat df = new SimpleDateFormat();

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
		}else{
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
		}else{
			this.readDateInString = "";
		}
	}
	public String getNotificationDateInString() {
		return notificationDateInString;
	}
	public void setNotificationDateInString(String notificationDateInString) {
		this.notificationDateInString = notificationDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(notificationDateInString);
		} catch (ParseException e) {
			e.printStackTrace();
			this.notificationDateInString = "";
			date = null;
		}
		this.notificationDate = date;
	}
	public String getReadDateInString() {
		return readDateInString;
	}
	public void setReadDateInString(String readDateInString) {
		this.readDateInString = readDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(readDateInString);
		} catch (ParseException e) {
			e.printStackTrace();
			this.readDateInString = "";
			date = null;
		}
		this.readDate = date;
	}
}
