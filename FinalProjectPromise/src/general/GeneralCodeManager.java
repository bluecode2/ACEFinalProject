package general;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralCodeManager {
	private SqlMapClient ibatis;

	public GeneralCodeManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public ArrayList<GeneralCodeBean> getAllGeneralCode() {
		ArrayList<GeneralCodeBean> arr = null;

		try {
			arr = (ArrayList<GeneralCodeBean>) this.ibatis.queryForList(
					"genCode.selectGeneralCode", null);
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

	public void deleteGeneralCodeByCodeId(String genCodeId)
			throws SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("genCode.deleteGeneralCode", genCodeId);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

}
