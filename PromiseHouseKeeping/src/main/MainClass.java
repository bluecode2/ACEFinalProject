package main;

import audit_trail.AuditTrailHandler;
import project_log.ProjectLogHandler;
import project_log.ProjectLogManager;
import notification.NotificationHandler;

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
