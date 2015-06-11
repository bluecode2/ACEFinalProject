package general;

import holiday.GeneralHolidayBean;
import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<MenuBean> selectListMenu(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<MenuBean> listResult = this.ibatis.queryForList(
				"generalHoliday.getGeneralHoliday", map);
		return listResult;
	}

	public void insertMenu(MenuBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("menu.insertMenu", bean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void updateMenu(MenuBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("menu.updateMenu", bean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void deleteMenu(Integer menuId) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("menu.updateMenu", menuId);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public Integer getCountMenu(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject("menu.countMenu",
				map);
		return result;
	}
}
