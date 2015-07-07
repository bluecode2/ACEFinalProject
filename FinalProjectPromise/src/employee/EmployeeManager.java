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

		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
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
	
	public List<EmployeeBean> getAllEmployeeForPopUp()  {
		
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>() ;
		try {
			arr = this.ibatis.queryForList("employee.getAllEmployeeForPopUp", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public List<EmployeeBean> getAllEmployeeForPM(String searchField,String searchValue,Integer deptId) {
		
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
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

	public int getNewEmpId() throws SQLException  {
		
		Integer tempNewEmp = (Integer) this.ibatis.queryForObject("employee.getNewEmpId", null);
		
		return tempNewEmp;
	}

	public void insertEmployee(EmployeeBean eb) throws ClassNotFoundException,
			SQLException {
		try {
			this.ibatis.startTransaction();
			eb.setEmployeeId(getNewEmpId());
			this.ibatis.insert("employee.insertEmployee", eb);
			this.ibatis.commitTransaction();
			this.ibatis.endTransaction();
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

	public void updateEmployee(EmployeeBean eb) throws ClassNotFoundException,
			SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("employee.updateEmployee", eb);
			this.ibatis.commitTransaction();
			this.ibatis.endTransaction();
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

	public void deleteEmployee(Integer empId, Integer updatedBy)
			throws ClassNotFoundException {
		try {
			Map map = new HashMap();
			map.put("employeeId", empId);
			map.put("updatedBy", updatedBy);
			
			ibatis.startTransaction();
			ibatis.update("employee.deleteEmployee", map);
			ibatis.commitTransaction();
			this.ibatis.endTransaction();
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

	public Integer getCountEmployee(String column, String value)
			throws ClassNotFoundException, SQLException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject("employee.countEmployee", map);

		return result;
	}
	
	public List<EmployeeBean> getListEmployeeForPersonalHoliday() {
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
		
		try {
			arr = this.ibatis.queryForList("employee.getEmployeeForPersonalHoliday", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}
	
	public List<EmployeeBean> getEmpForAssignTask(int spvId, String searchField,String searchValue) {
		List<EmployeeBean> arr = new ArrayList<EmployeeBean>();
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
