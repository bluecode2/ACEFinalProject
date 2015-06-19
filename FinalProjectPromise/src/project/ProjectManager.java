package project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserBean;
import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectManager {
	
	private SqlMapClient ibatis;
	
	public ProjectManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProjectBean> getAllProject(String col, String input,
			Integer pageNum, Integer pageSize) throws ClassNotFoundException,
			SQLException {
				
				int begin = (pageNum - 1) * pageSize;
				int end = pageNum * pageSize;
				
				List<ProjectBean> listProject = new ArrayList<ProjectBean>();
				Map map = new HashMap();
				map.put("searchField", col);
				map.put("searchValue", input);
				map.put("begin", begin);
				map.put("end", end);
				
				try {
					listProject = this.ibatis.queryForList("project.getAllProject", map);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return listProject;
			}
	
	public Integer getCountProject(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"project.countProject", map);
		return result;
	}
}
