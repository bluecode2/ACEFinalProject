package main;

import notification.NotificationHandler;
import project_log.ProjectLogHandler;
import audit_trail.AuditTrailHandler;

public class MainClass {
	

	public static void main(String[] args) {
		NotificationHandler	noHan = new NotificationHandler();

		ProjectLogHandler prLogHandler = new ProjectLogHandler();
		

		noHan.writeNotificationToSweep();
		
		prLogHandler.backupProjectLog();
		
		AuditTrailHandler auditTrailHan = new AuditTrailHandler();
		auditTrailHan.backUpAuditTrail();
	}
}
