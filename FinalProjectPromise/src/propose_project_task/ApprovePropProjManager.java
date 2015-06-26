package propose_project_task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

import employee.EmployeeBean;

public class ApprovePropProjManager {

	private SqlMapClient ibatis;
	
	public ApprovePropProjManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProposeProjectTaskBean> getAllPropTask(String col, String input,
			Integer pageNum, Integer pageSize, Integer empId) throws SQLException{
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		List<ProposeProjectTaskBean> list = new ArrayList<ProposeProjectTaskBean>();
		list = this.ibatis.queryForList("appPropProjTask.getListToApp", map);
		
		return list;
	}
	
	public Integer getCountAllPropTask(String column, String value, Integer empId)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("empId", empId);
		Integer result = (Integer) this.ibatis.queryForObject(
				"appPropProjTask.countListToApp", map);
		return result;
	}
	
	public List<EmployeeBean> getEmployeeBySpvId(Integer empId)
			throws SQLException {
		List<EmployeeBean> empBean = this.ibatis.queryForList(
				"approveTask.getEmpList", empId);

		return empBean;
	}
	
}