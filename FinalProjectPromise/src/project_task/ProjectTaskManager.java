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

	public void editProjectTask(ProjectTaskBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectTask.updateProjectTask", bean);
			this.ibatis.commitTransaction();
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
	
	public void updateTaskStat(ProjectTaskBean bean) throws SQLException{//Integer taskId, Integer updatedBy, String taskStat) throws SQLException {
		/*Map map = new HashMap();
		System.out.println("masuk Manager");
		map.put("taskId", taskId);
		map.put("updatedBy", updatedBy);
		map.put("taskStatus", taskStat);
		System.out.println(taskId +" update "+ updatedBy +" stat "+ taskStat +" testing manager");*/
	
		try {
			this.ibatis.startTransaction();
			System.out.println("berhasil masuk edit");
			this.ibatis.update("projectTask.updateProjectStat", bean);
			System.out.println("Berhasil Edit");
			this.ibatis.commitTransaction();
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

	public void editStatusRemarksProjectTask(int taskId, int updatedBy,
			String taskStatus, String remarks) throws SQLException {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);

		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectTask.updateStatusRemarksProjectTask", m);
			this.ibatis.commitTransaction();
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
	
	public void editStatusProjectTask(int taskId, int updatedBy,
			String taskStatus) throws SQLException {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);

		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectTask.updateStatusProjectTask", m);
			this.ibatis.commitTransaction();
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
	
	public ProjectTaskBean getDataForstatus(Integer taskId) throws SQLException{
		ProjectTaskBean pTaskBean = new ProjectTaskBean();
		pTaskBean = (ProjectTaskBean) this.ibatis.queryForObject("projectTask.getDataForstatus", taskId);
		return pTaskBean;
	}
}
