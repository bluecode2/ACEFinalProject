package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ReportManager {
	
	private SqlMapClient ibatis;
	
	public ReportManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ReportBean> getListReports() {
		List<ReportBean> arrList = new ArrayList<ReportBean>();
		
		try {
			arrList = this.ibatis.queryForList("reports.getListReports", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	

}
