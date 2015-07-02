package general;

import java.sql.SQLException;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.Constant;

public class GeneralParamManager {
	
	private SqlMapClient ibatis;
	
	public GeneralParamManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public String getGeneralParamValue(){
		String genParamId = Constant.GeneralParameter.BACK_UP_LOG_PATH;
		String paramValue = "";
		try {
			paramValue = (String) this.ibatis.queryForObject("", genParamId);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paramValue;
	}
}
