package activity;

import java.sql.SQLException;
import java.util.List;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ActivityManager {

	private SqlMapClient ibatis;

	public ActivityManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ActivityBean> getActivityWithTaskId(int id) throws SQLException {
		
		List<ActivityBean> arr = this.ibatis.queryForList("activity.getActivityWithTaskId", id);
		
		return arr;
	}

}
