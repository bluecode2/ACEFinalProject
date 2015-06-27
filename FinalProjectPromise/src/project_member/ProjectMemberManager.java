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
	
	public List<ProjectMemberBean> getAllProjectMember(Integer projId,
			Integer pageNum, Integer pageSize) {
		
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		
		Map map = new HashMap();
		map.put("projId", projId);
		map.put("begin", begin);
		map.put("end", end);
		
		
		try {
			pmbList = this.ibatis.queryForList("projectMember.getAllProjMember", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pmbList;
	}
	
	public Integer getCountProjectMember(Integer projId)
			throws SQLException, ClassNotFoundException {
		Integer result = (Integer) this.ibatis.queryForObject(
				"projectMember.countProjectMember", projId);
		return result;
	}
	
	public Integer getNewMemberId() throws SQLException{
		Integer newMemberId = (Integer) this.ibatis.queryForObject("projectMember.getProjectMemberId", null);
		return newMemberId;
	}
	
	public void insertProjectMember(ProjectMemberBean bean){

		try {
			this.ibatis.startTransaction();
			bean.setProjectRoleId(getNewMemberId());   
			this.ibatis.insert("projectMember.insertProjectMember", bean);
			this.ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<ProjectMemberBean> getProjectMemberToEvaluate(Integer projId) {
		List<ProjectMemberBean> arrMember = new ArrayList<ProjectMemberBean>();
		
		try {
			arrMember = this.ibatis.queryForList("projectMember.getAllMemberFromProject", projId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrMember;
	}
	
	public void delProjMember(Integer memberId) throws SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectMember.delProjMember", memberId);
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
	
	public List<ProjectMemberBean> getPopUpProjMember(Integer projId){
		Map map = new HashMap();
		map.put("projId", projId);
		
		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		try {
			pmbList = this.ibatis.queryForList("projectMember.getPopUpProjMember", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pmbList;
	}
}
