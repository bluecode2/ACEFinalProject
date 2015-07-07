package reports;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import department.DepartmentBean;

public class ReportManager {
	
	private SqlMapClient ibatis;
	
	public ReportManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ReportBean> getListReports() {
		List<ReportBean> arrList = Collections.EMPTY_LIST;
		
		try {
			arrList = this.ibatis.queryForList("reports.getListReports", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	
	public List<DepartmentBean> getListDeptFromUserRole(Integer userRoleId) throws SQLException{
		List<DepartmentBean> list = Collections.EMPTY_LIST;
		list = this.ibatis.queryForList("reports.getListDeptByUserRole", userRoleId);
		return list;
	}
	
	public List<ReportBean> getListParentReportsByUserRole(Integer userRoleId) {
		List<ReportBean> arrList = Collections.EMPTY_LIST;
		
		try {
			arrList = this.ibatis.queryForList("reports.getListParentReportsByUserRole", userRoleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	
	public List<ReportBean> getListChildReportsByUserRole(Integer userRoleId, Integer parentId) {
		List<ReportBean> arrList = Collections.EMPTY_LIST;
		
		Map m = new HashMap();
		m.put("userRoleId", userRoleId);
		m.put("parentId", parentId);
		
		try {
			arrList = this.ibatis.queryForList("reports.getListChildReportsByUserRole", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	
	public ReportBean getReportById(Integer reportId) {
		ReportBean bean = null;
		
		try {
			bean = (ReportBean) this.ibatis.queryForObject("reports.getReportById", reportId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;
	}

}
