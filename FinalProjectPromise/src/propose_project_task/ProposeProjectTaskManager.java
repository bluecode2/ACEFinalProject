package propose_project_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProposeProjectTaskManager {
	
	private SqlMapClient ibatis;
	
	public ProposeProjectTaskManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProposeProjectTaskBean> getAllPropProjTask(String col,
			String input, Integer pageNum, Integer pageSize, Integer projId) throws SQLException{
			List<ProposeProjectTaskBean> list = new ArrayList<ProposeProjectTaskBean>();
			
			int begin = (pageNum - 1) * pageSize;
			int end = pageNum * pageSize;
			
			Map map = new HashMap();
			map.put("searchField", col);
			map.put("searchValue", input);
			map.put("begin", begin);
			map.put("end", end);
			map.put("projId", projId);
			
			list = this.ibatis.queryForList("projProposeTask.getAllTask", map);
		
		return list;
	}
	
	public Integer getNewTaskId() throws SQLException{
		Integer newTaskId = (Integer) this.ibatis.queryForObject("projProposeTask.getnewTaskId", null);
		
		return newTaskId;
	}
	
	public void insertPropProjTask(ProposeProjectTaskBean pProjTBean) throws SQLException{
		pProjTBean.setPropTaskId(getNewTaskId());
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("projProposeTask.insertPropProjTask", pProjTBean);
			this.ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				ibatis.endTransaction();
		}
	}
	
	public Integer getCountPropProjTask(String column, String value, Integer projId)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("projId", projId);

		Integer result = (Integer) this.ibatis.queryForObject(
				"projProposeTask.countGetAllTask", map);
		return result;
	}
	
	public ProposeProjectTaskBean getPropProjTaskByTaskId(Integer propTaskId) throws SQLException{
		ProposeProjectTaskBean pProjTask = new ProposeProjectTaskBean();
		System.out.println(propTaskId+"taslId");
		pProjTask = (ProposeProjectTaskBean) this.ibatis.queryForObject("projProposeTask.getTaskById", propTaskId);
		System.out.println(pProjTask.getPropTaskId()+"test");
		return pProjTask;
	}
	
	public void editPropProjTask(ProposeProjectTaskBean pProjTaskBean) throws SQLException{
		try {
			this.ibatis.startTransaction();
			System.out.println("masuk try");
			this.ibatis.update("projProposeTask.updatePropProjTask", pProjTaskBean);
			System.out.println("Berhasil");
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("gagal");
			this.ibatis.endTransaction();
		}
	}
	
	public void delPropProjTask(Integer userId, Integer propTaskId) throws SQLException{
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("propTaskId", propTaskId);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projProposeTask.delPropProjTask", map);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
}
