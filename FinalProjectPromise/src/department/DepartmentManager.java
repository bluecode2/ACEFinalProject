package department;

import general.GeneralParamBean;
import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DepartmentManager {
	private SqlMapClient ibatis;

	public DepartmentManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<DepartmentBean> getAllDepartmentsFiltered(String col,
			String input, Integer pageNum, Integer pageSize) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<DepartmentBean> arr = null;
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		try {
			arr = this.ibatis
					.queryForList("department.getListDepartments", map);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public ArrayList<DepartmentBean> getAllDepartmentsForField(
			int excludedDeptId) {
		ArrayList<DepartmentBean> arr = new ArrayList<DepartmentBean>();

		try {
			arr = new ArrayList<DepartmentBean>(this.ibatis.queryForList(
					"department.getListDepartmentsForField", excludedDeptId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public DepartmentBean getDepartment(int deptid) {
		DepartmentBean dept = null;
		try {
			dept = (DepartmentBean) ibatis.queryForObject(
					"department.getDepartment", deptid);
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
			maxId = (Integer) ibatis.queryForObject(
					"department.getDepartmentMaxId", null);
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
			maxid = (Integer) this.ibatis.queryForObject(
					"department.getRowCountDepartmentsFiltered", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxid;
	}

	public Integer getCountDepartment(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"department.countDepartment", map);
		return result;
	}
}