package main;

import notification.NotificationHandler;
import project_log.ProjectLogHandler;
import audit_trail.AuditTrailHandler;

public class MainClass {
	

	public static void main(String[] args) {
		NotificationHandler	noHan = new NotificationHandler();
		AuditTrailHandler auditTrailHan = new AuditTrailHandler();
		ProjectLogHandler prLogHandler = new ProjectLogHandler();
		
		noHan.writeNotificationToSweep();
		auditTrailHan.backUpAuditTrail();
		prLogHandler.backupProjectLog();

	}
}
