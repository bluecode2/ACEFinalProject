package project_member;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.ProjectBean;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectMemberManager {

	private SqlMapClient ibatis;
	
	public ProjectMemberManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<ProjectMemberBean> getAllProjectMember(
			Integer pageNum, Integer pageSize) throws SQLException{
		
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		List<ProjectBean> listProject = new ArrayList<ProjectBean>();
		Map map = new HashMap();
		map.put("begin", begin);
		map.put("end", end);
		
		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		pmbList = this.ibatis.queryForList("projectMember.getAllProjMember", map);
		return pmbList;
	}
	
	public Integer getCountProjectMember()
			throws SQLException, ClassNotFoundException {
		Integer result = (Integer) this.ibatis.queryForObject(
				"projectMember.countProjectMember", null);
		return result;
	}
	
	public Integer getMemberId() throws SQLException{
		Integer newMemberId = (Integer) this.ibatis.queryForObject("", null);
		return newMemberId;
	}
	
	public void insertProjectMember(ProjectMemberBean pMemberBean) throws SQLException{
		Integer newMemberId = getMemberId();
		pMemberBean.setMemberId(newMemberId);
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("projectMember.insertProjectMember", pMemberBean);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
		
	}
}
