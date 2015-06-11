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
		map.put("col", col);
		map.put("input", input);
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

	public void insertEmployee(EmployeeBean eb) throws ClassNotFoundException,
			SQLException {
		Integer autoEmpId = (Integer) this.ibatis.queryForObject(
				"employee.getCountEmployeeId", null);
		if (autoEmpId == null) {
			autoEmpId = 1;
		}
		eb.setEmployeeId(autoEmpId);
		try {
			this.ibatis.startTransaction();
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
		map.put("col", column);
		map.put("input", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"employee.countEmployee", map);
		return result;
	}
}