package general;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import employee.EmployeeBean;

public class MenuManager {
	private SqlMapClient ibatis;

	public MenuManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public ArrayList<MenuBean> getAllMenuHead() {
		ArrayList<MenuBean> arr = new ArrayList<MenuBean>();

		try {
			arr = new ArrayList<MenuBean>(this.ibatis.queryForList(
					"menu.selectListMenuHead", null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<MenuBean> getAllMenuByParent(Integer parentId) {
		ArrayList<MenuBean> arr = new ArrayList<MenuBean>();

		try {
			arr = new ArrayList<MenuBean>(this.ibatis.queryForList(
					"menu.selectListMenuByParent", parentId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
