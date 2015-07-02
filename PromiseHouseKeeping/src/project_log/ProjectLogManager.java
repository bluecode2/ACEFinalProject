package project_log;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectLogManager {
	private SqlMapClient ibatis;

	public ProjectLogManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProjectLogBean> getListProjectLogBeanForBackup(){
		List<ProjectLogBean> lstBean = new ArrayList<ProjectLogBean>();
		
		try {
			lstBean = this.ibatis.queryForList("projectLog.getprojectLogForBackup", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return lstBean;
	}
	
	public void deleteProjectLog(){
		try {
			ibatis.startTransaction();
			ibatis.delete("projectLog.deleteprojectLog", null);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
