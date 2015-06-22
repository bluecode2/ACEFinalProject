package proposed_task;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

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
	
	public void declineTask(ProposedTaskBean task) {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("selectedId", task.getPropTaskId());
		
		try {
			ibatis.startTransaction();
			ibatis.update("approveTask.declineTask", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void approveTask(ProposedTaskBean task) {
		Map map = new HashMap();
		map.put("userId", task.getUpdatedBy());
		map.put("selectedId", task.getPropTaskId());
		
		try {
			ibatis.startTransaction();
			ibatis.update("approveTask.approveTask", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
