package project_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			String input, int pageNum, int pageSize, int projectId) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProjectTaskBean> arr = Collections.EMPTY_LIST;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("projectId", projectId);

	try {
		arr = this.ibatis.queryForList("projectTask.getAllProjectTaskByProjectId", map);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return arr;
	}

	public int getCountAssignTaskByProjectId(String col, String input,
			Integer projectId) throws SQLException  {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("projectId", projectId);

		int tmpCount = (Integer) this.ibatis.queryForObject(
				"projectTask.getCountProjectTaskByProject", map);
		return tmpCount;
	}

	public boolean createNewOProjectTask(ProjectTaskBean tsBean)
			 {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("projectTask.insertToProjectTask", tsBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean editProjectTask(ProjectTaskBean bean)  {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.updateProjectTask", bean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public boolean updateTaskStat(ProjectTaskBean bean) {
	boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.updateProjectStat", bean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean editStatusRemarksProjectTask(int taskId, int updatedBy,
			String taskStatus, String remarks) {
		boolean flag = true;
		
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);

		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.updateStatusRemarksProjectTask", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public void startProjectTask(int taskId, int updatedBy,
			String taskStatus)  {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);

		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.startProjectTask", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void submitProjectTask(int taskId, int updatedBy,
			String taskStatus)  {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.submitProjectTask", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editStatusProjectTask(int taskId, int updatedBy,
			String taskStatus)  {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);

		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projectTask.updateStatusProjectTask", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ProjectTaskBean getTaskById(int taskId)  {
		
		ProjectTaskBean tBean = null;
		
		try {
			tBean = (ProjectTaskBean) this.ibatis.queryForObject(
					"projectTask.getProjectTaskByTaskId", taskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tBean;
	}

	public int getNewTaskId() throws SQLException  {
		int tmpNewId = (Integer) this.ibatis.queryForObject("projectTask.getNewTaskId", null);
		return tmpNewId;
	}
	
	public ProjectTaskBean getDataForstatus(Integer taskId) {
		ProjectTaskBean pTaskBean = null;
		try {
			pTaskBean = (ProjectTaskBean) this.ibatis.queryForObject("projectTask.getDataForstatus", taskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pTaskBean;
	}
}
