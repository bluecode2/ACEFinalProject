package proposed_task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

import employee.EmployeeBean;

public class ApproveTaskManager {

	private SqlMapClient ibatis;

	public ApproveTaskManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ProposedTaskBean> getListApproveTask(String col, String input,
			Integer pageNum, Integer pageSize, int employeeId)  {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProposedTaskBean> arr = Collections.EMPTY_LIST;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("employeeId", employeeId);

		try {
			arr = this.ibatis.queryForList(
					"approveTask.getListApproveTask", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public ProposedTaskBean getApproveTaskById(Integer id)  {
		ProposedTaskBean bean = null;
		
		try {
			bean = (ProposedTaskBean) this.ibatis.queryForObject("approveTask.getPropTaskByPropTaskId", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public void declineTask(ProposedTaskBean task)  {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("selectedId", task.getPropTaskId());

		try {
			try {
				ibatis.startTransaction();
				ibatis.update("approveTask.declineTask", map);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void approveTask(ProposedTaskBean task)  {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("taskId", task.getTaskId());
		map.put("selectedId", task.getPropTaskId());

		try {
			try {
				ibatis.startTransaction();
				ibatis.update("approveTask.approveTask", map);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getCountApproveTask(String column, String value, int employeeId) throws SQLException{
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("employeeId", employeeId);
		Integer result = (Integer) this.ibatis.queryForObject(
				"approveTask.countApproveTask", map);
		return result;
	}

	public List<EmployeeBean> getEmployeeBySpvId(Integer empId){
		List<EmployeeBean> empBean = Collections.EMPTY_LIST;
		
		try {
			empBean = this.ibatis.queryForList(
					"approveTask.getEmpList", empId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empBean;
	}
	
	public void addRemarksProposedTask(int userId, int propId, String remarks) {
		
		Map m = new HashMap();
		m.put("userId", userId);
		m.put("propTaskId", propId);
		m.put("remarks", remarks);
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("proposedTask.addRemarksProposedTask", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
