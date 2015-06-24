package project_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectTaskManager {
	private SqlMapClient ibatis;

	public ProjectTaskManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ProjectTaskBean> getListProjectTaskByProjectId(String col,
			String input, int pageNum, int pageSize, int projectId)
			throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("projectId", projectId);

		List arr = this.ibatis.queryForList(
				"projectTask.getAllProjectTaskByProjectId", map);

		return arr;
	}

	public int getCountAssignTaskByProjectId(String col, String input,
			Integer projectId) throws SQLException {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("projectId", projectId);

		int tmpCount = (Integer) this.ibatis.queryForObject(
				"projectTask.getCountProjectTaskByProject", map);
		return tmpCount;
	}

	public void createNewOProjectTask(ProjectTaskBean tsBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			tsBean.setTaskId(getNewTaskId());
			this.ibatis.insert("projectTask.insertToProjectTask", tsBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void editProjectTask(ProjectTaskBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectTask.updateProjectTask", bean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void editStatusProjectTask(int taskId, int updatedBy,
			String taskStatus, String remarks) throws SQLException {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);

		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectTask.updateStatusProjectTask", m);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}

	}

	public ProjectTaskBean getDataForEdit(int taskId) throws SQLException {

		ProjectTaskBean tBean = (ProjectTaskBean) this.ibatis.queryForObject(
				"projectTask.getProjectTaskByTaskId", taskId);
		return tBean;
	}

	public int getNewTaskId() throws SQLException {
		int tmpNewId = (Integer) this.ibatis.queryForObject(
				"projectTask.getNewTaskId", null);
		return tmpNewId;
	}
}
