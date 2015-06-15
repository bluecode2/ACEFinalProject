package user;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserManager {
	
	private SqlMapClient ibatis;

	public UserManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<UserBean> getAllUser(String col, String input,
	Integer pageNum, Integer pageSize) throws ClassNotFoundException,
	SQLException {
		
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		List<UserBean> listUser = new ArrayList<UserBean>();
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
}
