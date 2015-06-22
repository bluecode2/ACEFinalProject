package project;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserBean;
import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectManager {
	
	private SqlMapClient ibatis;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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
	
	public ProjectBean getUserByUserID(Integer tempProjectID) throws SQLException, ClassNotFoundException{
		ProjectBean pBean = null;
		pBean = (ProjectBean) this.ibatis.queryForObject("project.getProjectbyId", tempProjectID);
		return pBean;
	}
	
	public Integer getNewProjectId() throws SQLException{
		Integer newProjectId = (Integer) this.ibatis.queryForObject("project.getNewProjectId", null);
		return newProjectId;
	}
	
	public void insertProject(ProjectBean pBean) throws SQLException, ParseException{
		Integer newProjId = getNewProjectId();
		if (newProjId == null){
			newProjId = 1;
		}
		
		pBean.setProjectId(newProjId);
		pBean.setCreatedBy(1);
		pBean.setEstStartDate(sdf.parse(pBean.getEstStartDateInString()));
		pBean.setEstEndDate(sdf.parse(pBean.getEstEndDateInString()));
		Integer estMainDays = pBean.getEstEndDate().getDate() - pBean.getEstStartDate().getDate() ;
		pBean.setEstMainDays(estMainDays);
		System.out.println("Proj ID "+pBean.getProjectId());
		System.out.println("Dept ID "+pBean.getDept_id());
		System.out.println("Employee ID "+pBean.getEmployeeId());
		System.out.println("created by "+pBean.getCreatedBy());
		System.out.println("proj code "+pBean.getProjectCode());
		System.out.println("Proj name "+pBean.getProjectName());
		System.out.println("Proj desc "+pBean.getProjectDesc());
		System.out.println("est start date "+pBean.getEstStartDateInString());
		System.out.println("est end date "+pBean.getEstEndDateInString());
		System.out.println("est main days "+pBean.getEstMainDays());
		try {
			this.ibatis.startTransaction();
			System.out.println("berhasil masuk ibatis");
			this.ibatis.insert("project.insertProject", pBean);
			System.out.println("sukses ibatis");
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.ibatis.endTransaction();
		}
		
	}
	
	public void updateProject(ProjectBean pBean) throws ParseException, SQLException{
		pBean.setUpdatedBy(1);
		pBean.setEstStartDate(sdf.parse(pBean.getEstStartDateInString()));
		pBean.setEstEndDate(sdf.parse(pBean.getEstEndDateInString()));
		Integer estMainDays = pBean.getEstEndDate().getDate() - pBean.getEstStartDate().getDate();
		pBean.setEstMainDays(estMainDays);
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("project.updateProject", pBean);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.ibatis.endTransaction();
		}
	}
	
}
