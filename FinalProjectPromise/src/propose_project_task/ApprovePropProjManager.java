package propose_project_task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proposed_task.ProposedTaskBean;
import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

import employee.EmployeeBean;

public class ApprovePropProjManager {

	private SqlMapClient ibatis;
	
	public ApprovePropProjManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProposeProjectTaskBean> getAllPropTask(String col, String input,
			Integer pageNum, Integer pageSize, Integer empId){
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("empId", empId);
		
		List<ProposeProjectTaskBean> list = new ArrayList<ProposeProjectTaskBean>();
		
		try {
			list = this.ibatis.queryForList("appPropProjTask.getListToApp", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Integer getCountAllPropTask(String column, String value, Integer empId) throws SQLException{
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("empId", empId);
		Integer result = (Integer) this.ibatis.queryForObject(
				"appPropProjTask.countListToApp", map);
		return result;
	}
	
	public List<EmployeeBean> getEmployeeBySpvId(Integer empId){
		List<EmployeeBean> empBean = null;
		
		try {
			empBean = this.ibatis.queryForList("approveTask.getEmpList", empId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empBean;
	}
	
	public ProposeProjectTaskBean getApproveTaskById(Integer id)  {
		ProposeProjectTaskBean bean  = null;
		try {
			bean = (ProposeProjectTaskBean) this.ibatis.queryForObject(
					"appPropProjTask.getPropTaskByPropTaskId", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	public void createNewAssignTaskMap(ProposeProjectTaskBean bean)  {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("appPropProjTask.insertToAssignTaskMap", bean);
			this.ibatis.commitTransaction();
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
	
	public void approveTask(ProposeProjectTaskBean pPropProjTask) {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("appPropProjTask.approveTask", pPropProjTask);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void declineTask(ProposeProjectTaskBean bean)  {
		try {
			ibatis.startTransaction();
			ibatis.update("appPropProjTask.declineTask", bean);
			ibatis.commitTransaction();
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
}
