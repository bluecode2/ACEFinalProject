package activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ActivityManager {

	private SqlMapClient ibatis;

	public ActivityManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ActivityBean> getActivityByTaskId(int id) {
		
		List<ActivityBean> arr = new ArrayList<ActivityBean>();
		try {
			arr = this.ibatis.queryForList("activity.getActivityByTaskId", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public ActivityBean getActivityById(Integer activityId){
		ActivityBean bean = null;
		
		
		return bean;
	}

}
