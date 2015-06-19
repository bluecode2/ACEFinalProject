package user_access;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserRoleMenuManager {
	private SqlMapClient ibatis;

	public UserRoleMenuManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<UserRoleMenuBean> getUserRoleMenuByUserRole(Integer userRoleId)
			throws SQLException {

		List<UserRoleMenuBean> arr = new ArrayList<UserRoleMenuBean>();
		
		try {
			arr = (List<UserRoleMenuBean>) this.ibatis.queryForList(
					"userRoleMenu.getUserRoleMenuByUserRole", userRoleId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return arr;
	}

	public void insertUserRoleMenu(UserRoleMenuBean userRoleMenuBean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("userRoleMenu.insertUserRoleMenu", userRoleMenuBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public UserRoleMenuBean getUserRoleMenu(Integer userRoleId, Integer menuId) throws SQLException {
		Map map = new HashMap();
		map.put("userRoleId", userRoleId);
		map.put("menuId", menuId);
		
		UserRoleMenuBean userRoleBean = (UserRoleMenuBean) this.ibatis.queryForObject(
				"userRoleMenu.getUserRoleMenu", map);
		return userRoleBean;
	}

	public void editUserRoleMenu(UserRoleMenuBean userRoleBeanMenu) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("userRoleMenu.editUserRoleMenu", userRoleBeanMenu);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void deleteUserRoleMenu(Integer userRoleId,Integer menuId) throws SQLException {
		Map map = new HashMap();
		map.put("userRoleId", userRoleId);
		map.put("menuId", menuId);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("userRoleMenu.deleteUserRoleMenu", map);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}
}
