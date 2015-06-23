package proposed_task;

import java.sql.SQLException;
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
	
	public List<ProposedTaskBean> getListApproveTask(String col,
			String input, Integer pageNum, Integer pageSize, int userId) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("userId", userId);

	
		List<ProposedTaskBean> 	arr = this.ibatis.queryForList("approveTask.getListApproveTask", map);

		return arr;
	}
	
	public ProposedTaskBean getApproveTaskById (Integer id) throws SQLException {
		ProposedTaskBean bean = (ProposedTaskBean) this.ibatis.queryForObject("approveTask.getPropTaskByPropTaskId", id);
		return bean;
	}
	
	public void declineTask(ProposedTaskBean task) throws SQLException {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("selectedId", task.getPropTaskId());
		
		try {
			ibatis.startTransaction();
			ibatis.update("approveTask.declineTask", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			this.ibatis.endTransaction();
		}
	}
	
	public void approveTask(ProposedTaskBean task) throws SQLException {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("selectedId", task.getPropTaskId());
		
		try {
			ibatis.startTransaction();
			ibatis.update("approveTask.approveTask", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			this.ibatis.endTransaction();
		}
	}
	
	public Integer getCountApproveTask(String column, String value, int userId)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("userId", userId);
		Integer result = (Integer) this.ibatis.queryForObject(
				"approveTask.countApproveTask", map);
		return result;
	}
	
	public EmployeeBean getEmployeeBySpvId(Integer empId) throws SQLException {

		EmployeeBean empBean = (EmployeeBean) this.ibatis.queryForObject(
				"approveTask.getEmpList", empId);
		return empBean;
	}
}
