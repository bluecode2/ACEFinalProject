package main;

import notification.NotificationHandler;

public class MainClass {
	

	public static void main(String[] args) {
		NotificationHandler	noHan = new NotificationHandler();
		
		noHan.writeNotificationToSweep();
	}
}
