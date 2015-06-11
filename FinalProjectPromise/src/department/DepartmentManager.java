package department;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DepartmentManager {
	private SqlMapClient ibatis;

	public DepartmentManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public DepartmentManager(String connString, String dbUser, String dbPass) {
		
	}

	public ArrayList<DepartmentBean> getAllDepartmentsFiltered(String searchField,
			String searchValue, int pageSize, int pageNum) {
		ArrayList<DepartmentBean> arr = new ArrayList<DepartmentBean>();
		int startIdx = (pageNum - 1) * pageSize;
		int lastIdx = pageNum * pageSize;
		
		Map map = new HashMap();
		map.put("firstIndex", startIdx);
		map.put("lastIndex", lastIdx);
		map.put("searchField", searchField);
		map.put("searchValue", searchValue);
		
		try {
			arr = new ArrayList<DepartmentBean>(this.ibatis.queryForList("department.getListDepartments", map));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public ArrayList<DepartmentBean> getAllDepartmentsForField(int excludedDeptId) {
		ArrayList<DepartmentBean> arr = new ArrayList<DepartmentBean>();
		
		try {
			arr = new ArrayList<DepartmentBean>(this.ibatis.queryForList("department.getListDepartmentsForField", excludedDeptId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public DepartmentBean getDepartment(int deptid) {
		DepartmentBean dept = null;
		try {
			dept = (DepartmentBean) ibatis.queryForObject("department.getDepartment",deptid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dept;
	}

	public void updateDepartment(DepartmentBean dept) {
		try {
			ibatis.startTransaction();
			ibatis.update("department.updateDepartment", dept);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteDepartment(int deptid) {
		try {
			ibatis.startTransaction();
			ibatis.delete("department.deleteDepartment", deptid);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertDepartment(DepartmentBean deptid) {
		try {
			ibatis.startTransaction();
			ibatis.insert("department.insertDepartment", deptid);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getDepartmentMaxId() {
		Integer maxId = 0;
		try {
			maxId = (Integer) ibatis.queryForObject("department.getDepartmentMaxId",null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxId;
	}

	public int getAllDepartmmentRowCountFiltered(String searchField,
			String searchValue) {
		Integer maxid = 0;
		Map map = new HashMap();
		map.put("searchField", searchField);
		map.put("searchValue", searchValue);
		
		try {
			maxid = (Integer) this.ibatis.queryForObject("department.getRowCountDepartmentsFiltered", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxid;
	}
	
	public Integer getCountDepartment(String column, String value) throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("col", column);
		map.put("input", value);
		Integer result = (Integer) this.ibatis.queryForObject("department.countDepartment", map);
		return result;
	}
}