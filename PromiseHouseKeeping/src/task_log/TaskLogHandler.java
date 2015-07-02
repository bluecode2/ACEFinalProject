package task_log;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskLogHandler {
	private TaskLogManager taskLogManager;
	private List<TaskLogBean> lstBean;
	private String strToPrint;
	private List<Integer> lstProjectId;
	String row;
	
	public TaskLogHandler() {
		// TODO Auto-generated constructor stub
		taskLogManager = new TaskLogManager();
		lstBean = new ArrayList<TaskLogBean>();
		lstProjectId = new ArrayList<Integer>();
		strToPrint = "";
		row = "=========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================";
	}
	
	public void backupTaskLogByProject(Integer projectId){
		
		List<TaskLogBean> tmpList = taskLogManager.getListTaskLogByProject(projectId);
		
		for (TaskLogBean taskLogBean : tmpList) {
			lstBean.add(taskLogBean);
			strToPrint += String
					.format("| %-10s | %-10s | %-10s | %-150s | %-100s | %-100s | %-20s | %-50s | %-10s | %-14s |\n",
							taskLogBean.getTaskLogId(),
							taskLogBean.getTaskId(),
							taskLogBean.getUpdateBy(),
							taskLogBean.getTaskField(),
							taskLogBean.getTaskOldValue(),
							taskLogBean.getTaskNewValue(),
							taskLogBean.getUpdateDateInString(),
							taskLogBean.getRemarks(),
							taskLogBean.getChangeType(),
							taskLogBean.getChangeTypeInString());
		}
		
		lstProjectId.add(projectId);
	}
	
	public void printAllBackupTask(){
	Date now = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		String f = "E:\\Task_BackUp_" + sdFormat.format(now) + ".txt";
		FileOutputStream fos = null;
		PrintStream ps = null;
		try {
			fos = new FileOutputStream(f);
			ps 	= new PrintStream(fos);
			ps.println(row);
			ps.println(String
					.format("| %-10s | %-10s | %-10s | %-150s | %-100s | %-100s | %-20s | %-50s | %-10s | %-14s |",
							"Log Id",
							"Task Id",
							"Updated By",
							"Field Task",
							"Task Old Value",
							"Task New Value",
							"Update Date",
							"Remarks",
							"Change",
							"Change Name"));
			ps.println(row);
			ps.println(strToPrint);
			ps.print(row);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteAllBackupTask(){
		for (Integer projectId : lstProjectId) {
			//taskLogManager.deleteTaskLogByProject(projectId);
		}
		
	}
	
	
	
}
