package notification;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class NotificationManager {
	private SqlMapClient ibatis;

	public NotificationManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<NotificationBean> getListAllNotificationByEmployee(Integer employeeId,Integer pageNum, Integer pageSize){
		List<NotificationBean> list = new ArrayList<NotificationBean>();
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("begin", begin);
		map.put("end", end);
	
		try {
			list =  this.ibatis.queryForList("notification.getAllNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<NotificationBean> getListUnreadNotificationByEmployee(Integer employeeId){
		List<NotificationBean> list = new ArrayList<NotificationBean>();
		
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("isRead", 0);
		map.put("begin", 0);
		map.put("end", 5);
	
		try {
			list =  this.ibatis.queryForList("notification.getAllNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public NotificationBean getNotificationById(Integer notificationId){
		NotificationBean bean = null;
	
		try {
			bean =  (NotificationBean) this.ibatis.queryForObject("notification.getNotificationById", notificationId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;	
	}
	
	public Integer getNotificationNewId() throws SQLException{
		Integer newId = (Integer) this.ibatis.queryForObject("notification.getNotificationNewId", null);
		return newId;
	}
	
	public Integer getCountNotificationByEmployee(Integer employeeId) {
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		
		Integer result = 0;
		try {
			result = (Integer) this.ibatis.queryForObject(
					"notification.countNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer getCountNotificationByEmployee(Integer employeeId,Boolean isRead) {
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("isRead", isRead?1:0);
		
		Integer result = 0;
		try {
			result = (Integer) this.ibatis.queryForObject(
					"notification.countNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertNotification(NotificationBean bean){
		boolean flag = true;
		
		try {
			this.ibatis.startTransaction();
			bean.setNotificationId(getNotificationNewId());
			this.ibatis.insert("notification.insertNotification", bean);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean updateNotificationAsRead(Integer notificationId){
		boolean flag = true;
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("notification.updateReadNotification", notificationId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return flag;
	}
}
