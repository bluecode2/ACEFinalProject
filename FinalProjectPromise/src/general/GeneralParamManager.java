package general;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralParamManager {

	private SqlMapClient ibatis;

	public GeneralParamManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<GeneralParamBean> getAllGeneralParam(String col, String input,
			Integer begin, Integer end) {

		List<GeneralParamBean> arr = null;
		Map map = new HashMap();
		map.put("col", col);
		map.put("input", input);
		map.put("begin", begin);
		map.put("end", end);
		try {
			arr = (List<GeneralParamBean>) this.ibatis.queryForList(
					"genParam.selectGenParam", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public void insertGeneralParam(GeneralParamBean genParamBean)
			throws SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("genParam.insertGenParam", genParamBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}

	}

	public void updateGeneralParam(GeneralParamBean genParamBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("genParam.updateGenParam", genParamBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void deleteGeneralParam(String genParamId) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("genParam.deleteGenparam", genParamId);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

}
