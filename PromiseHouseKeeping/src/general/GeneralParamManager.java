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
	
	public String getGeneralParamValue(String genParamId){
		String paramValue = "";
		try {
			paramValue = (String) this.ibatis.queryForObject("genParam.getGenParamByParamId", genParamId);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paramValue;
	}
}
