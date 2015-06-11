package user;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserRoleManager {
	private SqlMapClient ibatis;
	
	public UserRoleManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<UserRoleBean> getUserRole() throws SQLException{
		List<UserRoleBean> listResult = this.ibatis.queryForList("userRole.getUserRole", null);
		return listResult;
	}
	
	public void insertUserRole(UserRoleBean userRoleBean) throws SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("userRole.insertUserRole", userRoleBean);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}

	public UserRoleBean getUserRoleEdit(int roleId) throws SQLException{
		UserRoleBean userRoleBean = (UserRoleBean) this.ibatis.queryForObject("userRole.getUserRoleEdit", roleId);
		return userRoleBean;
	}

	public void editUserRole(UserRoleBean userRoleBean) throws SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("userRole.editUserRole", userRoleBean);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}
	
	public void deleteUserRole(Integer roleId) throws SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("userRole.deleteUserRole", roleId);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}
	
	public Integer getCountUserRole(String column, String value) throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("col", column);
		map.put("input", value);
		Integer result = (Integer) this.ibatis.queryForObject("userRole.countUserRole", map);
		return result;
	}
}
