package user;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserRoleManager {
	private SqlMapClient ibatis;

	public UserRoleManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<UserRoleBean> getUserRole(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<UserRoleBean> arr = (List<UserRoleBean>) this.ibatis.queryForList(
				"userRole.getUserRole", map);

		return arr;
	}

	public List<UserRoleBean> getUserRoleForPopUp() throws SQLException {

		List<UserRoleBean> arr = (List<UserRoleBean>) this.ibatis.queryForList(
				"userRole.getUserRoleForPopUp", null);

		return arr;
	}

	public void insertUserRole(UserRoleBean userRoleBean) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("userRole.insertUserRole", userRoleBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserRoleBean getUserRoleEdit(int tmpUserRoleId) throws SQLException {
		UserRoleBean userRoleBean = (UserRoleBean) this.ibatis.queryForObject(
				"userRole.getUserRoleByUserRoleId", tmpUserRoleId);
		return userRoleBean;
	}

	public void editUserRole(UserRoleBean userRoleBean) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("userRole.editUserRole", userRoleBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUserRole(Integer roleId) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.delete("userRole.deleteUserRole", roleId);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getCountUserRole(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"userRole.countUserRole", map);
		return result;
	}

	public int getUserRoleId() throws SQLException {
		int tmpUserRoleId = (Integer) this.ibatis.queryForObject(
				"userRole.getUserRoleId", null);
		return tmpUserRoleId;
	}
}
