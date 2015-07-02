package notification;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class NotificationManager {
	private SqlMapClient ibatis;

	public NotificationManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<NotificationBean> getListNotificationToSweep() {
		List<NotificationBean> arr = new ArrayList<NotificationBean>();
		try {
			arr = this.ibatis.queryForList("notification.getAllNotification", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arr;
	}
	
	public void deleteNotificationToSweep() {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("notification.deleteNotification", null);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
