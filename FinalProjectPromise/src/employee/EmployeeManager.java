package employee;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
		List<EmployeeBean> arr = Collections.EMPTY_LIST;
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
	
	public List<EmployeeBean> getAllEmployeeForPopUp(String searchValue, String searchField)  {
		
		Map map = new HashMap();
		map.put("searchField", searchField);
		map.put("searchValue", searchValue);

		List<EmployeeBean> arr = Collections.EMPTY_LIST;
		try {
			arr = this.ibatis.queryForList("employee.getAllEmployeeForPopUp", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public List<EmployeeBean> getAllEmployeeForPM(String searchField,String searchValue,Integer deptId) {
		List<EmployeeBean> arr = Collections.EMPTY_LIST;
		Map map = new HashMap();
		map.put("searchField", searchField);
		map.put("searchValue", searchValue);
		map.put("deptId", deptId);
		try {
			arr = this.ibatis.queryForList("employee.getAllEmployeeForPM", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public List<EmployeeBean> getAllEmployeeForDeptHead(Integer deptId,String searchField, String searchValue)
			throws ClassNotFoundException, SQLException {
		List<EmployeeBean> arr = Collections.EMPTY_LIST;
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
		List<EmployeeBean> arr = Collections.EMPTY_LIST;
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
	
	public EmployeeBean getEmployeeByEmpId(Integer empId) {
		EmployeeBean empBean= null;
		try {

			empBean = (EmployeeBean) this.ibatis.queryForObject(
					"employee.getEmployeeByEmpId", empId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empBean;
	}

	public int getNewEmpId() throws SQLException {
		Integer tempNewEmp = (Integer) this.ibatis.queryForObject("employee.getNewEmpId", null);
		return tempNewEmp;
	}

	public boolean insertEmployee(EmployeeBean eb) {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				eb.setEmployeeId(getNewEmpId());
				this.ibatis.insert("employee.insertEmployee", eb);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean updateEmployee(EmployeeBean eb) {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("employee.updateEmployee", eb);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteEmployee(Integer empId, Integer updatedBy)
			throws ClassNotFoundException {
		boolean flag= true;
		try {
			try {
				Map map = new HashMap();
				map.put("employeeId", empId);
				map.put("updatedBy", updatedBy);
				
				ibatis.startTransaction();
				ibatis.update("employee.deleteEmployee", map);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Integer getCountEmployee(String column, String value)
			throws ClassNotFoundException, SQLException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject("employee.countEmployee", map);
		return result;
	}
	
	public List<EmployeeBean> getListEmployeeForPersonalHoliday() {
		List<EmployeeBean> arr = Collections.EMPTY_LIST;	
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForPersonalHoliday", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public List<EmployeeBean> getEmpForAssignTask(int spvId, String searchField,String searchValue) {
		List<EmployeeBean> arr = Collections.EMPTY_LIST;
		Map m = new HashMap();
		m.put("spvId",spvId);
		m.put("searchValue", searchValue);
		m.put("searchField", searchField);
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForAssignTask", m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
