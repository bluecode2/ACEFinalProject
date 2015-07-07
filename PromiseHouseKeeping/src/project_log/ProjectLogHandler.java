package project_log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import common.CommonFunction;
import common.Constant;
import task_log.TaskLogHandler;

public class ProjectLogHandler {
	private ProjectLogManager prLogManager;
	String strToPrint;
	String row;

	public ProjectLogHandler() {
		// TODO Auto-generated constructor stub
		prLogManager = new ProjectLogManager();
		strToPrint = "";
		row = "===========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================";
	}

	public void backupProjectLog(){
		try {
			TaskLogHandler taskLogHandler = new TaskLogHandler();

			List<ProjectLogBean> listProjectLog = prLogManager
					.getListProjectLogBeanForBackup();

			if (listProjectLog.size() > 0) {

				TreeSet<Integer> lstProjectId = new TreeSet<Integer>();

				for (ProjectLogBean projectLogBean : listProjectLog) {
					strToPrint += String
							.format("| %-12s | %-10s | %-10s | %-150s | %-100s | %-100s | %-20s | %-50s | %-10s | %-14s |\n",
									projectLogBean.getProjectLogId(),
									projectLogBean.getProjectId(),
									projectLogBean.getUpdateBy(),
									projectLogBean.getProjectField(),
									projectLogBean.getProjectOldValue(),
									projectLogBean.getProjectNewValue(),
									projectLogBean.getUpdateDateInString(),
									projectLogBean.getRemarks(),
									projectLogBean.getChangeType(),
									projectLogBean.getChangeTypeInString());
					lstProjectId.add(projectLogBean.getProjectId());
				}

				for (Integer projectId : lstProjectId) {
					taskLogHandler.backupTaskLogByProject(projectId);
				}
				printProjectLogToFile();
				taskLogHandler.printAllBackupTask();
				// prLogManager.deleteProjectLog();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void printProjectLogToFile() throws FileNotFoundException, IOException {
		Date now = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		String f = CommonFunction.getGeneralParameterValue(Constant.GeneralParameter.BACKUP_LOG_PATH) + "Project_Backup_" + sdFormat.format(now) + ".txt";
		FileOutputStream fos = null;
		PrintStream ps = null;

		fos = new FileOutputStream(f);
		ps = new PrintStream(fos);
		ps.println(row);
		ps.println(String
				.format("| %-12s | %-10s | %-10s | %-150s | %-100s | %-100s | %-20s | %-50s | %-10s | %-14s |",
						"Pro. Log Id", "Pro. Id", "Updated By",
						"Field Project", "Project Old Value",
						"Project New Value", "Update Date", "Remarks",
						"Change", "Change Name"));
		ps.println(row);
		ps.println(strToPrint);
		ps.print(row);
		
		ps.close();
		fos.close();
	}
}
