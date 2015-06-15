package department;

import ibatis.IbatisHelper;

import java.sql.SQLException;
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
			arr = this.ibatis.queryForList("department.getListDepartments", map);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	

	public DepartmentBean getDepartmentByDeptId(int tmpDeptId) {
		DepartmentBean dept = null;
		try {
			dept = (DepartmentBean) ibatis.queryForObject("department.getDepartmentbyDeptId", tmpDeptId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dept;
	}
	
	public int getNewDeptId() throws SQLException {
		
		int tempDeptId = (Integer) ibatis.queryForObject("department.getNewDeptId", null);
		return tempDeptId;
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

	public void deleteDepartment(int deptid,int userId) {
		try {
			Map map = new HashMap();
			map.put("deptId", deptid);
			map.put("updatedBy", userId);
			
			ibatis.startTransaction();
			ibatis.delete("department.deleteDepartment", map);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertDepartment(DepartmentBean dept) {
		try {
			ibatis.startTransaction();
			dept.setDeptId(getNewDeptId());
			ibatis.insert("department.insertDepartment", dept);
			ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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