package department;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crystaldecisions.b.f;
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
			e.printStackTrace();
		}
		return dept;
	}
	
	public List<DepartmentBean> getListDepartmentForSearchDialog(String col, String input) {
		
		Map m = new HashMap();
		m.put("searchValue", input);
		m.put("searchField", col);
		
		List<DepartmentBean> arr = Collections.EMPTY_LIST;

		try {
			arr = this.ibatis.queryForList("department.getDepartmentForSearchDialog", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public Integer getNewDeptId() throws SQLException {
		
		Integer tempDeptId = (Integer) ibatis.queryForObject("department.getNewDeptId", null);
		return tempDeptId;
	}

	public boolean updateDepartment(DepartmentBean dept) {
		boolean flag = true;
		try {
			try {
				ibatis.startTransaction();
				ibatis.update("department.updateDepartment", dept);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteDepartment(int deptid,int userId) {
		boolean flag = true;
		try {
			try {
				Map map = new HashMap();
				map.put("deptId", deptid);
				map.put("updatedBy", userId);
				
				ibatis.startTransaction();
				ibatis.update("department.deleteDepartment", map);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean insertDepartment(DepartmentBean dept) {
		boolean flag = true;
		try {
			try {
				ibatis.startTransaction();
				dept.setDeptId(getNewDeptId());
				ibatis.insert("department.insertDepartment", dept);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
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
