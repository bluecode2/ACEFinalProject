package employee;

import general.GeneralCodeBean;
import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class EmployeeManager {

	private SqlMapClient ibatis;

	public EmployeeManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<EmployeeBean> getAllEmployee(String col, String input,
			Integer pageNum, Integer pageSize) throws ClassNotFoundException,
			SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<EmployeeBean> arr = null;
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		try {
			arr = this.ibatis.queryForList("employee.getAllEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public List<EmployeeBean> getAllEmployeeForDeptHead(Integer deptId,String searchField, String searchValue)
			throws ClassNotFoundException, SQLException {
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
		
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);
		
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForDeptHead",
					map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public List<EmployeeBean> getListEmployeeForSupervisor(Integer deptId, Integer rankLevel, String col, String input)
			throws ClassNotFoundException, SQLException {
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
		
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("rankLevel", rankLevel);
		map.put("searchValue", input);
		map.put("searchField", col);
		
		
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForSupervisor",
					map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}
	
	public EmployeeBean getEmployeeByEmpId(Integer empId) throws SQLException {

		EmployeeBean empBean = (EmployeeBean) this.ibatis.queryForObject(
				"employee.getEmployeeByEmpId", empId);
		return empBean;
	}

	public int getNewEmpId() throws SQLException {
		int tempNewEmp = (Integer) this.ibatis.queryForObject(
				"employee.getNewEmpId", null);
		return tempNewEmp;
	}

	public void insertEmployee(EmployeeBean eb) throws ClassNotFoundException,
			SQLException {
		try {
			this.ibatis.startTransaction();
			eb.setEmployeeId(getNewEmpId());
			this.ibatis.insert("employee.insertEmployee", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}

	public void updateEmployee(EmployeeBean eb) throws ClassNotFoundException,
			SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("employee.updateEmployee", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}

	public void deleteEmployee(Integer EmployeeId)
			throws ClassNotFoundException, SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("employee.deleteEmployee", EmployeeId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}

	public Integer getCountEmployee(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"employee.countEmployee", map);
		return result;
	}
	
	public List<EmployeeBean> getListEmployeeForPersonalHoliday()
			throws ClassNotFoundException, SQLException {
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
		
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForPersonalHoliday", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}
}
