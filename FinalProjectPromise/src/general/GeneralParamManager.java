package general;

import java.sql.SQLException;
import java.util.ArrayList;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralParamManager {

	private SqlMapClient ibatis;

	public GeneralParamManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public ArrayList<GeneralParamBean> getAllGeneralParam() {
		ArrayList<GeneralParamBean> arr = null;

		try {
			arr = (ArrayList<GeneralParamBean>) this.ibatis.queryForList(
					"genParam.selectGenParam", null);
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
	
	public void get() {
		
	}
}
