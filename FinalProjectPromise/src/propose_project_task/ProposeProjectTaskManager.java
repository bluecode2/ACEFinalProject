package propose_project_task;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			String input, Integer pageNum, Integer pageSize, Integer projId){
			List<ProposeProjectTaskBean> list = Collections.EMPTY_LIST;
			
			int begin = (pageNum - 1) * pageSize;
			int end = pageNum * pageSize;
			
			Map map = new HashMap();
			map.put("searchField", col);
			map.put("searchValue", input);
			map.put("begin", begin);
			map.put("end", end);
			map.put("projId", projId);
			
			try {
				list = this.ibatis.queryForList("projProposeTask.getAllTask", map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return list;
	}
	
	public List<ProposeProjectTaskBean> getAllPropProjTask(String col,
			String input, Integer pageNum, Integer pageSize, Integer projId,Integer proposedById){
			List<ProposeProjectTaskBean> list = Collections.EMPTY_LIST;
			
			int begin = (pageNum - 1) * pageSize;
			int end = pageNum * pageSize;
			
			Map map = new HashMap();
			map.put("searchField", col);
			map.put("searchValue", input);
			map.put("begin", begin);
			map.put("end", end);
			map.put("projId", projId);
			map.put("proposedBy", proposedById);
			
			try {
				list = this.ibatis.queryForList("projProposeTask.getAllTask", map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return list;
	}
	
	public Integer getNewTaskId() throws SQLException{
		Integer newTaskId = (Integer) this.ibatis.queryForObject("projProposeTask.getnewTaskId", null);
		
		return newTaskId;
	}
	
	public boolean insertPropProjTask(ProposeProjectTaskBean pProjTBean){
		boolean flag = true;
		try {
			pProjTBean.setPropTaskId(getNewTaskId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("projProposeTask.insertPropProjTask", pProjTBean);
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
	
	public Integer getCountPropProjTask(String column, String value, Integer projId) throws SQLException{
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("projId", projId);

		Integer result = (Integer) this.ibatis.queryForObject(
				"projProposeTask.countGetAllTask", map);
		return result;
	}
	
	public Integer getCountPropProjTask(String column, String value, Integer projId, Integer proposedBy) throws SQLException{
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("projId", projId);
		map.put("proposedBy", proposedBy);

		Integer result = (Integer) this.ibatis.queryForObject(
				"projProposeTask.countGetAllTask", map);
		return result;
	}
	
	public ProposeProjectTaskBean getPropProjTaskByTaskId(Integer propTaskId){
		ProposeProjectTaskBean pProjTask = new ProposeProjectTaskBean();

		try {
			pProjTask = (ProposeProjectTaskBean) this.ibatis.queryForObject("projProposeTask.getTaskById", propTaskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pProjTask;
	}
	
	public boolean editPropProjTask(ProposeProjectTaskBean pProjTaskBean){
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projProposeTask.updatePropProjTask", pProjTaskBean);
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
	
	public boolean delPropProjTask(Integer userId, Integer propTaskId){
		boolean flag = true;
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("propTaskId", propTaskId);
		
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("projProposeTask.delPropProjTask", map);
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
	
}
