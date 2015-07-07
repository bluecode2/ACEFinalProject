package rank_employee;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RankEmpManager {

	private SqlMapClient ibatis;

	public RankEmpManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<RankEmpBean> getAllEmployeeRank(String col, String input,
			Integer pageNum, Integer pageSize) {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<RankEmpBean> list = new ArrayList<RankEmpBean>();

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		try {
			list = this.ibatis.queryForList("rank.getAllEmployeeRank", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Integer getCountRankEmp(String column, String value)
			throws SQLException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer count = (Integer) this.ibatis.queryForObject(
				"rank.countEmployeeRank", map);
		return count;
	}

	public List<RankEmpBean> getListRankForSearch(String column, String value) {
		Map m = new HashMap();
		m.put("searchField", column);
		m.put("searchValue", value);
		List<RankEmpBean> arrList = new ArrayList<RankEmpBean>();
		try {
			arrList = this.ibatis.queryForList("rank.getRankListForSearch", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrList;
	}

	public RankEmpBean getRankByRankId(int tmpRankId) {
		RankEmpBean rankBean = null;
		try {
			rankBean = (RankEmpBean) this.ibatis.queryForObject(
					"rank.getRankByRankId", tmpRankId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rankBean;
	}

	public int getNewRankId() throws SQLException {
		int NewRankId = (Integer) this.ibatis.queryForObject(
				"rank.getNewRankId", null);
		return NewRankId;
	}

	public boolean insertEmployeeRank(RankEmpBean eb) {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				eb.setRankId(getNewRankId());
				this.ibatis.insert("rank.insertEmployeeRank", eb);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateEmployeeRank(RankEmpBean eb) {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("rank.updateEmployeeRank", eb);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return true;
	}

	public boolean deleteEmployeeRank(RankEmpBean eb) {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("rank.deleteEmployeeRank", eb);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
