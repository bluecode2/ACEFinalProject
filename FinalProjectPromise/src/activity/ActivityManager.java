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
		try {
			bean = (ActivityBean) this.ibatis.queryForObject("activity.getActivityById", activityId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	public Integer getNewActivityId() throws SQLException {
		Integer newId =  (Integer) this.ibatis.queryForObject("activity.getActivityNewId", null);
		return newId;
	}
	
	public Integer insertActivity(ActivityBean bean){
		Integer maxId = null;
		try {
			this.ibatis.startTransaction();
			bean.setActivityId(getNewActivityId());
			this.ibatis.insert("activity.insertActivity", bean);
			maxId =  (Integer) this.ibatis.queryForObject("activity.getActivityMaxId", null);
			this.ibatis.commitTransaction();
			this.ibatis.endTransaction();
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
		return maxId;
	}
	
	public void updateActivity(ActivityBean bean){
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("activity.updateActivity", bean);
			this.ibatis.commitTransaction();
			this.ibatis.endTransaction();
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
	
	public void deleteActivity(Integer activityId){
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("activity.deleteActivity", activityId);
			this.ibatis.commitTransaction();
			this.ibatis.endTransaction();
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
