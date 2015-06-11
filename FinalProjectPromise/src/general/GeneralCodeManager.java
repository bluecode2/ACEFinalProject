package general;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralCodeManager {
	private SqlMapClient ibatis;

	public GeneralCodeManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<GeneralCodeBean> getAllGeneralCode(String col, String input,
			Integer begin, Integer end) {
		List<GeneralCodeBean> arr = null;
		Map map = new HashMap();
		map.put("col", col);
		map.put("input", input);
		map.put("begin", begin);
		map.put("end", end);

		try {
			arr = (List<GeneralCodeBean>) this.ibatis.queryForList(
					"genCode.selectGeneralCode", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public void insertGeneralCode(GeneralCodeBean genCodebean)
			throws SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("genCode.insertGeneralCode", genCodebean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}

	}

	public void updateGeneralCode(GeneralCodeBean genCodebean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("genCode.updateGeneralCode", genCodebean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}

	}

	public void deleteGeneralCodeByCodeId(String genCodeId) throws SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("genCode.deleteGeneralCode", genCodeId);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

}
