package independent_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proposed_task.ProposedTaskBean;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IndependentTaskManager {

	private SqlMapClient ibatis;
	
	public IndependentTaskManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<IndependentTaskBean> getListAssignTask(String col, String input, int pageNum, int pageSize, int empId) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		List<IndependentTaskBean> arr = new ArrayList<IndependentTaskBean>();
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		try {
			arr = this.ibatis.queryForList("independentTask.getAllListAssignTask", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public List<IndependentTaskBean> getListForIndividualTask(int pageNum, int pageSize, int empId) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		List<IndependentTaskBean> arr = new ArrayList<IndependentTaskBean>();
		
		Map map = new HashMap();
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		try {
			arr = this.ibatis.queryForList("independentTask.getListForIndividualTask", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public Float getAvgTaskProg(Integer empId) throws SQLException{
		Float avgTaskProg = (Float) this.ibatis.queryForObject("independentTask.getAvgProgress", empId);
		
		return avgTaskProg;
	}
	
	public int getCountAssignTask(String col, String input,int empId) throws SQLException {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("empId", empId);
		map.put("searchValue", input);
		int tmpCount = (Integer) this.ibatis.queryForObject("independentTask.getCountAssignTask", map);
		return tmpCount;
	}
	
	public void createNewAssignTask(IndependentTaskBean tsBean) {
		try {
			this.ibatis.startTransaction();
			tsBean.setTaskId(getNewTaskId());
			this.ibatis.insert("independentTask.insertToAssignTask", tsBean);
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
	
	public void createNewAssignTaskProj(IndependentTaskBean tsBean) {
		try {
			this.ibatis.startTransaction();
			
			this.ibatis.insert("independentTask.insertToAssignTaskProj", tsBean);
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
	
	public void editAssignTask(int taskId, String taskName, String taskDesc, int updatedBy) {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("taskName", taskName);
		m.put("taskDesc", taskDesc);
		m.put("updatedBy", updatedBy);
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateCommentAssignTask", m);
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
	
	public void editStatusAssignTask(int taskId, int updatedBy, String taskStatus, String remarks) {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateStatusAssignTask", m);
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
	
	public void editStatusAssignTaskApprove(int taskId, int updatedBy, String taskStatus, String remarks) {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		m.put("remarks", remarks);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateStatusAssignTaskApprove", m);
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
	
	public IndependentTaskBean getDataForEdit(int taskId) {
		IndependentTaskBean tBean = null;
		
		try {
			tBean = (IndependentTaskBean) this.ibatis.queryForObject("independentTask.getAssignTaskForEdit", taskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return tBean;
	}
	
	public int getNewTaskId() throws SQLException {
		int tmpNewId = (Integer) this.ibatis.queryForObject("independentTask.getNewTaskId", null);
		return tmpNewId;
	}
	
/*	public void createNewAssignTaskMap(ProposedTaskBean bean) {
		Map map = new HashMap();
		map.put("taskId", bean.getTaskId());
		map.put("taskName", bean.getPropTaskName());
		map.put("taskDesc", bean.getPropTaskDesc());
		map.put("assignedBy", bean.getPropTo());
		map.put("assignedTo", bean.getPropBy());
		map.put("estStartDateInString", bean.getEstStartDateInString());
		map.put("estEndDateInString", bean.getEstEndDateInString());
		map.put("createdBy", bean.getCreatedBy());
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("independentTask.insertToAssignTaskMap", map);
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
	}*/

	public List<IndependentTaskBean> getListMyCurrentTask(String col, String input, int pageNum, int pageSize, int empId) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		List<IndependentTaskBean> arr = new ArrayList<IndependentTaskBean>();
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		try {
			arr = this.ibatis.queryForList("independentTask.getListMyCurrentTask", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	public int getCountMyCurrentTask(String col, String input,int empId) throws SQLException {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("empId", empId);
		int tmpCount = (Integer) this.ibatis.queryForObject("independentTask.getCountMyCurrentTask", map);
		return tmpCount;
	}
	
	public void startMyCurrentTask(int taskId, int updatedBy, String taskStatus) {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.startMyCurrentTask", m);
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
	
	public void updateStatusMyCurrentTask(int taskId, int updatedBy, String taskStatus) {
		Map m = new HashMap();
		m.put("taskId", taskId);
		m.put("updatedBy", updatedBy);
		m.put("taskStatus", taskStatus);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("independentTask.updateStatusMyCurrentTask", m);
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
}
