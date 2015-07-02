package notification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationHandler {

	private List<NotificationBean> arrTemp;
	NotificationManager noMan = new NotificationManager();

	public void writeNotificationToSweep() {
		arrTemp = noMan.getListNotificationToSweep();
		String row = "==========================================================================================================================================================================================";
		Date now = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("ddMMyyyy");
		if (arrTemp.size() > 0) {

			String f = "E:\\Notification_BackUp_" + sdFormat.format(now)
					+ ".txt";
			FileOutputStream fo = null;
			PrintStream wt = null;

			try {
				fo = new FileOutputStream(f);
				wt = new PrintStream(fo);
				wt.println(row);
				wt.println(String.format(
						"| %-10s | %-20s | %-100s | %-20s | %-20s |",
						"Notif. ID", "Employee Name", "Message", "Send Date",
						"Read Date"

				));
				wt.println(row);
				for (NotificationBean noBean : arrTemp) {
					wt.println(String.format(
							"| %-10s | %-20s | %-100s | %-20s | %-20s |",
							noBean.getNotificationId(),
							noBean.getEmployeeName(),
							noBean.getNotificationDesc(),
							noBean.getNotificationDateInString(),
							noBean.getReadDateInString()

					));
				}

				wt.close();
				fo.close();
				System.out.println("Write to the file success");
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
