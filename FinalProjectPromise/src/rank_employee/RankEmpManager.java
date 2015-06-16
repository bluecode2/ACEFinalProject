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
			Integer pageNum, Integer pageSize) throws ClassNotFoundException,
			SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<RankEmpBean> list = this.ibatis.queryForList(
				"rank.getAllEmployeeRank", map);
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

	public RankEmpBean getRankByRankId(int tmpRankId) throws SQLException {
		RankEmpBean rankBean = (RankEmpBean) this.ibatis.queryForObject("rank.getRankByRankId", tmpRankId);
		return rankBean;
	}
	
	public int getNewRankId() throws SQLException {
		int NewRankId = (Integer) this.ibatis.queryForObject("rank.getNewRankId", null);
		return NewRankId;
	}
	
	public void insertEmployeeRank(RankEmpBean eb)
			throws ClassNotFoundException, SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("rank.insertEmployeeRank", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}

	public void updateEmployeeRank(RankEmpBean eb)
			throws ClassNotFoundException, SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("rank.updateEmployeeRank", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}

	public void deleteEmployeeRank(Integer EmployeeId)
			throws ClassNotFoundException, SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("rank.deleteEmployeeRank", EmployeeId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
}
