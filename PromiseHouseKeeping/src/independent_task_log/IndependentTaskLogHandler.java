package independent_task_log;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import task_log.TaskLogBean;

import common.CommonFunction;
import common.Constant;

public class IndependentTaskLogHandler {

	public void backupIndependentTaskLog(){
		IndependentTaskLogManager iTMan = new IndependentTaskLogManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		
		List<TaskLogBean> listOfIndependentTaskLog = iTMan.getIndependentTaskLog();
		
		String fileName = CommonFunction.getGeneralParameterValue(Constant.GeneralParameter.BACKUP_LOG_PATH)
				+ "Independent_Task_Log_Backup_"+sdf.format(date)+".txt";
		
		FileOutputStream fileOut = null;
		PrintStream write = null;

		StringBuffer sb = new StringBuffer();

		int i = 0;
		
		if (listOfIndependentTaskLog.size() > 0) {
			try {
				fileOut = new FileOutputStream(fileName);
				write = new PrintStream(fileOut);

				sb.append("INDEPENDENT TASK LOG ("+sdf.format(date)+")");
				sb.append("\r\n");
				for (i = 0; i < 400; i++)
					sb.append("=");
				sb.append("\n\r\n");
				sb.append(String.format("%-20s", "|LOG ID"));
				sb.append(String.format("%-20s", "|TASK ID"));
				sb.append(String.format("%-20s", "|UPDATED BY"));
				sb.append(String.format("%-100s", "|FIELD TASK"));
				sb.append(String.format("%-50s", "|TASK OLD VALUE"));
				sb.append(String.format("%-50s", "|TASK NEW VALUE"));
				sb.append(String.format("%-25s", "|UPDATE DATE"));
				sb.append(String.format("%-45s", "|REMARKS"));
				sb.append(String.format("%-30s", "|CHANGE"));
				sb.append(String.format("%-30s", "|CHANGE NAME"));
				sb.append("\r\n");
				for (i = 0; i < 400; i++)
					sb.append("=");
				sb.append("\r\n");
				for (TaskLogBean tLogBean : listOfIndependentTaskLog) {
					sb.append(String.format("%-20s",
							"|" + tLogBean.getTaskLogId()));
					sb.append(String.format("%-20s", "|" + tLogBean.getTaskId()));
					sb.append(String.format("%-20s", "|" + tLogBean.getUpdateBy()));
					sb.append(String.format("%-100s", "|" + tLogBean.getTaskField()));
					sb.append(String.format("%-50s", "|" + tLogBean.getTaskOldValue()));
					sb.append(String.format("%-50s", "|" + tLogBean.getTaskNewValue()));
					sb.append(String.format("%-25s", "|" + tLogBean.getUpdateDateInString()));
					sb.append(String.format("%-45s", "|" + tLogBean.getRemarks()));
					sb.append(String.format("%-30s", "|" + tLogBean.getChangeType()));
					sb.append(String.format("%-30s", "|" + tLogBean.getChangeTypeInString()));
					sb.append("\r\n");
				}
				for (i = 0; i < 400; i++)
					sb.append("=");

				write.println(sb);
//				iTMan.delIndependentTaskLog();
				
				write.close();
				fileOut.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("Failed To Export");
			}
		}
	}
}
