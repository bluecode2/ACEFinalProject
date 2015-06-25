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
			String input, Integer pageNum, Integer pageSize, Integer userId) throws SQLException{
			List<ProposeProjectTaskBean> list = new ArrayList<ProposeProjectTaskBean>();
			
			int begin = (pageNum - 1) * pageSize;
			int end = pageNum * pageSize;
			
			Map map = new HashMap();
			map.put("searchField", col);
			map.put("searchValue", input);
			map.put("begin", begin);
			map.put("end", end);
			map.put("userId", userId);
			
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
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
	public Integer getCountPropProjTask(String column, String value, int userId)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("userId", userId);

		Integer result = (Integer) this.ibatis.queryForObject(
				"projProposeTask.countGetAllTask", map);
		return result;
	}
	
}
