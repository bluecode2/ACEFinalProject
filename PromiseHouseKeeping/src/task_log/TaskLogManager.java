package task_log;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import task_log.TaskLogBean;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TaskLogManager {
	private SqlMapClient ibatis;

	public TaskLogManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<TaskLogBean> getListTaskLogByProject(Integer projectId){
		List<TaskLogBean> lstBean = new ArrayList<TaskLogBean>();
		
		try {
			lstBean = this.ibatis.queryForList("taskLog.getListTaskLogByProject", projectId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return lstBean;
	}
	
	public void deleteTaskLogByProject(Integer projectId){
		try {
			ibatis.startTransaction();
			ibatis.delete("taskLog.deleteTaskLogByProject", projectId);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
