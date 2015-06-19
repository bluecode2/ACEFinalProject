package proposed_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProposedTaskManager {
	private SqlMapClient ibatis;

	public ProposedTaskManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ProposedTaskBean> getAllPropTaskFiltered(String col,
			String input, Integer pageNum, Integer pageSize, int userId) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProposedTaskBean> arr = null;
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("userId", userId);

		try {
			arr = this.ibatis.queryForList("proposedTask.getListProposedTask", map);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	

	public ProposedTaskBean getPropTaskByPropTaskId(int propTaskId) {
		ProposedTaskBean task = null;
		try {
			task = (ProposedTaskBean) ibatis.queryForObject("proposedTask.getPropTaskByPropTaskId", propTaskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public List<ProposedTaskBean> getListPropTaskForSearchDialog(String col, String input) {
		
		Map m = new HashMap();
		m.put("searchValue", input);
		m.put("searchField", col);
		
		List<ProposedTaskBean> arr = new ArrayList<ProposedTaskBean>();

		try {
			arr = this.ibatis.queryForList("proposedTask.getPropTaskForSearchDialog", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public Integer newPropTaskId() throws SQLException {
		
		Integer propTaskId = (Integer) ibatis.queryForObject("proposedTask.newPropTaskId", null);
		return propTaskId;
	}

	public void updateProposedTask(ProposedTaskBean task) {
		try {
			ibatis.startTransaction();
			ibatis.update("proposedTask.updateProposedTask", task);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteProposedTask(int propTaskId,int userId) {
		try {
			Map map = new HashMap();
			map.put("propTaskId", propTaskId);
			map.put("updatedBy", userId);
			
			ibatis.startTransaction();
			ibatis.delete("proposedTask.deleteProposedTask", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertProposedTask(ProposedTaskBean task) {
		try {
			ibatis.startTransaction();
			task.setPropTaskId(newPropTaskId());
			ibatis.insert("proposedTask.insertProposedTask", task);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getCountProposedTask(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"proposedTask.countProposedTask", map);
		return result;
	}
}