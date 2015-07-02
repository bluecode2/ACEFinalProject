package audit_trail;

import general.GeneralParamManager;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class AuditTrailHandler {

	public void backUpAuditTrail() {

		AuditTrailManager aTrailMan = new AuditTrailManager();
		GeneralParamManager gParamMan = new GeneralParamManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		
		List<AuditTrailBean> listOfAuditTrail = aTrailMan.getAuditTrail();
		String fileName = gParamMan.getGeneralParamValue()
				+ "Audit_Trail_Log"+sdf.format(date)+".txt";

		FileOutputStream fileOut = null;
		PrintStream write = null;

		StringBuffer sb = new StringBuffer();

		int i = 0;

		if (listOfAuditTrail.size() > 0) {
			try {
				fileOut = new FileOutputStream(fileName);
				write = new PrintStream(fileOut);

				sb.append("AUDIT TRAIL LOG ("+sdf.format(date)+")");
				sb.append("\r\n");
				for (i = 0; i < 400; i++)
					sb.append("=");
				sb.append("\n\r\n");
				sb.append(String.format("%-20s", "|AUDIT TRAIL ID"));
				sb.append(String.format("%-20s", "|UPDATED BY"));
				sb.append(String.format("%-30s", "|EMPLOYEE NAME"));
				sb.append(String.format("%-30s", "|TABLE NAME"));
				sb.append(String.format("%-100s", "|FIELD NAME"));
				sb.append(String.format("%-50s", "|OLD VALUE"));
				sb.append(String.format("%-500s", "|NEW VALUE"));
				sb.append(String.format("%-25s", "|UPDATE DATE"));
				sb.append(String.format("%-30s", "|CHANGE TYPE"));
				sb.append(String.format("%-30s", "|DATA ID"));
				sb.append("\r\n");
				for (i = 0; i < 400; i++)
					sb.append("=");
				sb.append("\r\n");
				for (AuditTrailBean aTB : listOfAuditTrail) {
					sb.append(String.format("%-20s",
							"|" + aTB.getAuditTrailId()));
					sb.append(String.format("%-20s", "|" + aTB.getUpdatedBy()));
					sb.append(String.format("%-30s", "|" + aTB.getUserName()));
					sb.append(String.format("%-30s", "|" + aTB.getTableName()));
					sb.append(String.format("%-100s", "|" + aTB.getFieldName()));
					sb.append(String.format("%-50s", "|" + aTB.getOldValue()));
					sb.append(String.format("%-50s", "|" + aTB.getNewValue()));
					sb.append(String.format("%-25s",
							"|" + aTB.getUpdateDateInString()));
					sb.append(String.format("%-30s", "|" + aTB.getChangeType()));
					sb.append(String.format("%-30s", "|" + aTB.getDataId()));
					sb.append("\r\n");
				}
				for (i = 0; i < 400; i++)
					sb.append("=");

				write.println(sb);
				//aTrailMan.delAuditTrail();
				
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
