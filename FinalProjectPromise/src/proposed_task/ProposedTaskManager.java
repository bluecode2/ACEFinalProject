package proposed_task;

import holiday.GeneralHolidayBean;
import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			String input, Integer pageNum, Integer pageSize, int empId)
			{
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProposedTaskBean> arr = Collections.EMPTY_LIST;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);

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
			task = (ProposedTaskBean) ibatis.queryForObject(
					"proposedTask.getPropTaskByPropTaskId", propTaskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}

	public List<ProposedTaskBean> getListPropTaskForSearchDialog(String col,
			String input) {
		Map m = new HashMap();
		m.put("searchValue", input);
		m.put("searchField", col);

		List<ProposedTaskBean> arr = Collections.EMPTY_LIST;

		try {
			arr = this.ibatis.queryForList(
					"proposedTask.getPropTaskForSearchDialog", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public Integer newPropTaskId() throws SQLException {
		Integer propTaskId = (Integer) ibatis.queryForObject(
				"proposedTask.newPropTaskId", null);
		return propTaskId;
	}

	public void updateProposedTask(ProposedTaskBean bean) {
		try {
			try {
				ibatis.startTransaction();
				ibatis.update("proposedTask.updateProposedTask", bean);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteProposedTask(int propTaskId, int userId)
			throws ClassNotFoundException, SQLException {
		Map m = new HashMap();
		m.put("propTaskId", propTaskId);
		m.put("userId", userId);

		try {
			try {
				ibatis.startTransaction();
				ibatis.delete("proposedTask.deleteProposedTask", m);
				ibatis.commitTransaction();
			} finally {
				ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertProposedTask(ProposedTaskBean bean) {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("proposedTask.insertProposedTask", bean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getCountProposedTask(String column, String value, int empId)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("empId", empId);

		Integer result = (Integer) this.ibatis.queryForObject(
				"proposedTask.countProposedTask", map);
		return result;
	}
}