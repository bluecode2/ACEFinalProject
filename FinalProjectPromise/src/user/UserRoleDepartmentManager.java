package user;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reports.ReportRoleBean;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserRoleDepartmentManager {
private SqlMapClient ibatis;
	
	public UserRoleDepartmentManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<UserRoleDepartmentBean> getUserRoleDepartmentByUserRole(Integer roleId) {
		
		List<UserRoleDepartmentBean> arrList = new ArrayList<UserRoleDepartmentBean>();
		
		try {
			arrList = this.ibatis.queryForList("userRoleDepartment.getUserRoleDepartmentByUserRole", roleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrList;
	}
	
	public UserRoleDepartmentBean getUserRoleDepartment(Integer roleId, Integer deptId) {
		UserRoleDepartmentBean rrBean = null;
		
		Map m = new HashMap();
		m.put("roleId", roleId);
		m.put("deptId", deptId);
		
		try {
			rrBean = (UserRoleDepartmentBean) this.ibatis.queryForObject("userRoleDepartment.getUserRoleDepartment", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rrBean;
	}
	
	public void insertUserRoleDepartment(UserRoleDepartmentBean rrBean){
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("userRoleDepartment.insertUserRoleDepartment", rrBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteUserRoleDepartment(UserRoleDepartmentBean rrBean) {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.delete("userRoleDepartment.deleteUserRoleDepartment", rrBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
