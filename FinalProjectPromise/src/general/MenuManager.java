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
					"menu.selectListVisibleMenuHead", null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<MenuBean> getAllMenuHead(Integer userRoleId) {
		ArrayList<MenuBean> arr = new ArrayList<MenuBean>();

		try {
			arr = new ArrayList<MenuBean>(this.ibatis.queryForList(
					"menu.selectListAuthorizeVisibleMenuHead", userRoleId));
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
					"menu.selectListVisibleMenuByParent", parentId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<MenuBean> getAllMenuByParent(Integer parentId, Integer userRoleId) {
		ArrayList<MenuBean> arr = new ArrayList<MenuBean>();

		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("userRoleId", userRoleId);
		
		try {
			arr = new ArrayList<MenuBean>(this.ibatis.queryForList(
					"menu.selectListAuthorizeVisibleMenuByParent", map));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	public ArrayList<MenuBean> getAllMenu() {
		ArrayList<MenuBean> arr = new ArrayList<MenuBean>();

		try {
			arr = new ArrayList<MenuBean>(this.ibatis.queryForList(
					"menu.selectListAllMenu", null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public MenuBean getMenuByMenuId(int tmpMenuId) {
		MenuBean mnBean = null;
		try {
			mnBean = (MenuBean) this.ibatis.queryForObject("menu.getMenuByMenuId", tmpMenuId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mnBean;
	}
	
	public MenuBean getMenuByMenuCode(String menuCode) {
		MenuBean mnBean = null;
		try {
			mnBean = (MenuBean) this.ibatis.queryForObject("menu.getMenuByMenuCode", menuCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mnBean;
	}

	public List<MenuBean> selectListMenu(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {

		System.out.println(pageNum +" " + pageSize + " "+ col + " "+ input);
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		System.out.println(begin + " "+end);

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<MenuBean> listResult = this.ibatis.queryForList(
				"menu.selectListMenu", map);
		return listResult;
	}

	public void insertMenu(MenuBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("menu.insertMenu", bean);
			this.ibatis.commitTransaction();
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

	public void updateMenu(MenuBean bean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("menu.updateMenu", bean);
			this.ibatis.commitTransaction();
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

	public void deleteMenu(Integer menuId) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("menu.updateMenu", menuId);
			this.ibatis.commitTransaction();
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
