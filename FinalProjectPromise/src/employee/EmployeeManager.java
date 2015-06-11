package employee;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class EmployeeManager {
	
	private SqlMapClient ibatis;
	
	public EmployeeManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<EmployeeBean> getAllEmployee() throws ClassNotFoundException, SQLException {
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		list = this.ibatis.queryForList("employee.getAllEmployee", null);
		return list;
	}
	
	public void insertEmployee(EmployeeBean eb) throws ClassNotFoundException, SQLException{
		Integer autoEmpId = (Integer) this.ibatis.queryForObject("employee.getCountEmployeeId", null);
		if (autoEmpId == null){
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
	
	public void updateEmployee(EmployeeBean eb) throws ClassNotFoundException, SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("employee.updateEmployee", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
	public void deleteEmployee(Integer EmployeeId) throws ClassNotFoundException, SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("employee.deleteEmployee", EmployeeId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
	public Integer getCountEmployee(String column, String value) throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("col", column);
		map.put("input", value);
		Integer result = (Integer) this.ibatis.queryForObject("employee.countEmployee", map);
		return result;
	}
}
