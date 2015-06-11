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
	
	public void insertMenu(MenuBean bean) throws SQLException{
		try{
			this.ibatis.startTransaction();
			this.ibatis.insert("menu.insertMenu", bean);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}
	public void updateMenu(MenuBean bean) throws SQLException{
		try{
			this.ibatis.startTransaction();
			this.ibatis.update("menu.updateMenu", bean);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}
	public void deleteMenu(Integer menuId) throws SQLException{
		try{
			this.ibatis.startTransaction();
			this.ibatis.delete("menu.updateMenu", menuId);
			this.ibatis.commitTransaction();
		} finally{
			this.ibatis.endTransaction();
		}
	}
}
