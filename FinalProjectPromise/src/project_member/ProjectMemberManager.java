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
			Integer pageNum, Integer pageSize) throws SQLException{
		
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		System.out.println(projId);
		List<ProjectBean> listProject = new ArrayList<ProjectBean>();
		Map map = new HashMap();
		map.put("projId", projId);
		map.put("begin", begin);
		map.put("end", end);
		
		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		pmbList = this.ibatis.queryForList("projectMember.getAllProjMember", map);
		return pmbList;
	}
	
	public Integer getCountProjectMember(Integer projId)
			throws SQLException, ClassNotFoundException {
		Integer result = (Integer) this.ibatis.queryForObject(
				"projectMember.countProjectMember", projId);
		return result;
	}
	
	public Integer getMemberId() throws SQLException{
		Integer newMemberId = (Integer) this.ibatis.queryForObject("projectMember.getProjectMemberId", null);
		return newMemberId;
	}
	
	public void insertProjectMember(Integer pRoleId, Integer empId, Integer projId) throws SQLException{
		Integer newMemberId = getMemberId();
		if (newMemberId == null){
			newMemberId = 1;
		}
		Map map = new HashMap();
		map.put("newMemberId", newMemberId);
		map.put("pRoleId", pRoleId);
		map.put("empId", empId);
		map.put("projId", projId);
		try {
			this.ibatis.startTransaction();
			System.out.println("masuk try");
			this.ibatis.insert("projectMember.insertProjectMember", map);
			System.out.println("selesai try");
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
	
	public List<ProjectMemberBean> getProjectMemberToEvaluate(Integer projId) throws SQLException {
		List<ProjectMemberBean> arrMember = this.ibatis.queryForList("projectMember.getAllMemberFromProject", projId);
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
	
	public List<ProjectMemberBean> getPopUpProjMember(Integer projId) throws SQLException{
		System.out.println(projId);
		List<ProjectBean> listProject = new ArrayList<ProjectBean>();
		Map map = new HashMap();
		map.put("projId", projId);
		
		List<ProjectMemberBean> pmbList = new ArrayList<ProjectMemberBean>();
		pmbList = this.ibatis.queryForList("projectMember.getPopUpProjMember", map);
		return pmbList;
	}
}
