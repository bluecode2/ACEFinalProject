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
			String input, Integer pageNum, Integer pageSize, Integer projId){
			List<ProposeProjectTaskBean> list = new ArrayList<ProposeProjectTaskBean>();
			
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
	
	public Integer getNewTaskId() throws SQLException{
		Integer newTaskId = (Integer) this.ibatis.queryForObject("projProposeTask.getnewTaskId", null);
		
		return newTaskId;
	}
	
	public void insertPropProjTask(ProposeProjectTaskBean pProjTBean){
		try {
			pProjTBean.setPropTaskId(getNewTaskId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("projProposeTask.insertPropProjTask", pProjTBean);
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
	
	public Integer getCountPropProjTask(String column, String value, Integer projId) throws SQLException{
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("projId", projId);

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
	
	public void editPropProjTask(ProposeProjectTaskBean pProjTaskBean){
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projProposeTask.updatePropProjTask", pProjTaskBean);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("gagal");
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void delPropProjTask(Integer userId, Integer propTaskId){
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("propTaskId", propTaskId);
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projProposeTask.delPropProjTask", map);
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
	
}
