package user;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;

public class UserManager {

	private SqlMapClient ibatis;

	public UserManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public UserBean getUserLogin(String username, String password)
			throws SQLException {
		UserBean uBean = null;

		Map m = new HashMap();
		m.put("username", username);
		m.put("password", password);

		uBean = (UserBean) this.ibatis.queryForObject("users.getBeanLogin", m);

		return uBean;
	}
	public int validasiLogin(String username, String password){
		Map m = new HashMap();
		m.put("username", username);
		m.put("password", password);
		
		int valLogin = 0;
		
		try {
			valLogin = (Integer) this.ibatis.queryForObject("users.validasiLogin", m);			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return valLogin;
		
	}
	

	public UserBean getUserByUsername(String username) throws SQLException {
		UserBean uBean = null;

		Map m = new HashMap();
		m.put("username", username);

		uBean = (UserBean) this.ibatis.queryForObject(
				"users.getUserByUserName", m);
		return uBean;
	}

	public List<UserBean> getAllUser(String col, String input, Integer pageNum,
			Integer pageSize) throws ClassNotFoundException, SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<UserBean> listUser = Collections.EMPTY_LIST;
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		try {
			listUser = this.ibatis.queryForList("users.getAllUser", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listUser;
	}

	public Integer getCountUser(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"users.countUser", map);
		return result;
	}

	public Integer getNewUserID() throws SQLException, ClassNotFoundException {
		Integer newUserID = (Integer) this.ibatis.queryForObject(
				"users.getNewUserID", null);
		return newUserID;
	}

	public void insertUser(UserBean uBean) throws SQLException,
			ClassNotFoundException {
		try {
			try {
				this.ibatis.startTransaction();
				uBean.setUserId(getNewUserID());
				this.ibatis.insert("users.insertNewUser", uBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(UserBean uBean) throws SQLException,
			ClassNotFoundException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("users.updateUser", uBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changePassword(UserBean uBean) throws SQLException,
			ClassNotFoundException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("users.changePassword", uBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserBean getUserByUserID(Integer tempUserID) throws SQLException,
			ClassNotFoundException {
		UserBean uBean = null;
		uBean = (UserBean) this.ibatis.queryForObject("users.getUserByUserID",
				tempUserID);
		return uBean;
	}

	public void delUsers(Integer userId, Integer updatedBy) throws SQLException {
		Map m = new HashMap();
		m.put("updatedBy", updatedBy);
		m.put("userId", userId);
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("users.delUsers", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteUserByEmpId(Integer empId,Integer updatedBy) {
		
		Map m = new HashMap();
		m.put("updatedBy", updatedBy);
		m.put("empId", empId);
		
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("users.deleteEmployee", m);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
