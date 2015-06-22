package project_member;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectMemberManager {

	private SqlMapClient ibatis;
	
	public ProjectMemberManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProjectMemberBean> getAllProjectMember(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException{
		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		pmbList = this.ibatis.queryForList("projectMember.getAllProjMember", null);
		return pmbList;
	}
	
	public Integer getCountProjectMember(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"projectMember.countProjectMember", map);
		return result;
	}
}
