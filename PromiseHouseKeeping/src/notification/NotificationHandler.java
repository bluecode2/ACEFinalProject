package notification;

import java.util.List;

public class NotificationHandler {
	
	private List<NotificationBean> 	arrTemp;
	private NotificationBean		noBean;
	
	
	public void writeNotificationToSweep() {
		NotificationManager 	noMan = new NotificationManager();
		arrTemp = noMan.getListNotificationToSweep();
		System.out.println(arrTemp.size());
	}
}
