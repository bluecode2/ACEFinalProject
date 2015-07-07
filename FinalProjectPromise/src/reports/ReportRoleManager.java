package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ReportRoleManager {

	private SqlMapClient ibatis;
	
	public ReportRoleManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ReportRoleBean> getReportRoleByRoleId(Integer roleId) {
		
		List<ReportRoleBean> arrList = new ArrayList<ReportRoleBean>();
		
		try {
			arrList = this.ibatis.queryForList("userRoleReport.getUserReportsByUserRole", roleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	
	public ReportRoleBean getReportRoleBean(Integer roleId, Integer reportId) {
		ReportRoleBean rrBean = null;
		
		Map m = new HashMap();
		m.put("roleId", roleId);
		m.put("reportId", reportId);
		
		try {
			rrBean = (ReportRoleBean) this.ibatis.queryForObject("userRoleReport.getUserReportBean", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rrBean;
	}
	
	public void insertUserRoleReport(ReportRoleBean rrBean) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("userRoleReport.insertUserReport", rrBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteUserRoleReport(ReportRoleBean rrBean) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.delete("userRoleReport.deleteUserReport", rrBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
