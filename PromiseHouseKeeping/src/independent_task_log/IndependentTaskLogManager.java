package independent_task_log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import task_log.TaskLogBean;
import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IndependentTaskLogManager {
	private SqlMapClient ibatis;
	
	public IndependentTaskLogManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<TaskLogBean> getIndependentTaskLog(){
		List<TaskLogBean> listIndependentTaskLog = new ArrayList<TaskLogBean>();
		
		try {
			listIndependentTaskLog = this.ibatis.queryForList("independentTaskLog.getListIndependentTaskLog", null);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listIndependentTaskLog;
	}
	
	public void delIndependentTaskLog(){
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("independentTaskLog.deleteIndependentTaskLog", null);
			this.ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO: handle exception
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}
}
