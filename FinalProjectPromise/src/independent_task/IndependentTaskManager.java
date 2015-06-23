package independent_task;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IndependentTaskManager {

	private SqlMapClient ibatis;
	
	public IndependentTaskManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<IndependentTaskBean> getListAssignTask(String col, String input, int pageNum, int pageSize, int empId) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		List arr = this.ibatis.queryForList("independentTask.getAllListAssignTask", map);
		
		return arr;
	}
	
	public int getCountAssignTask(String col, String input,int empId) throws SQLException {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("empId", empId);
		map.put("searchValue", input);
		int tmpCount = (Integer) this.ibatis.queryForObject("independentTask.getCountAssignTask", map);
		return tmpCount;
	}
	
	public void createNewAssignTask(IndependentTaskBean tsBean) throws SQLException {
		try {
			tsBean.setTaskId(getNewTaskId());
			this.ibatis.startTransaction();
			this.ibatis.insert("independentTask.insertToAssignTask", tsBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}
	
	public void editAssignTask(int taskId, String taskName, String taskDesc, int updatedBy) throws SQLException {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("taskName", taskName);
		m.put("taskDesc", taskDesc);
		m.put("updatedBy", updatedBy);
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateCommentAssignTask", m);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}
	
	public void editStatusAssignTask(int taskId, int updatedBy, String taskStatus, String remarks) throws SQLException {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateStatusAssignTask", m);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
		
	}
	public IndependentTaskBean getDataForEdit(int taskId) throws SQLException {
		
		IndependentTaskBean tBean = (IndependentTaskBean) this.ibatis.queryForObject("independentTask.getAssignTaskForEdit", taskId);	
		return tBean;
	}
	
	public int getNewTaskId() throws SQLException {
		int tmpNewId = (Integer) this.ibatis.queryForObject("independentTask.getNewTaskId", null);
		return tmpNewId;
	}
}
