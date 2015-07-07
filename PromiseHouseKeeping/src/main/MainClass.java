package main;

import java.io.IOException;

import independent_task_log.IndependentTaskLogHandler;
import notification.NotificationHandler;
import project_log.ProjectLogHandler;
import audit_trail.AuditTrailHandler;

public class MainClass {
	

	public static void main(String[] args) throws IOException {
		NotificationHandler	noHan = new NotificationHandler();
		AuditTrailHandler auditTrailHan = new AuditTrailHandler();
		ProjectLogHandler prLogHandler = new ProjectLogHandler();
		IndependentTaskLogHandler iTHan = new IndependentTaskLogHandler();
		
		noHan.writeNotificationToSweep();
		auditTrailHan.backUpAuditTrail();
		prLogHandler.backupProjectLog();
		iTHan.backupIndependentTaskLog();

	}
}
