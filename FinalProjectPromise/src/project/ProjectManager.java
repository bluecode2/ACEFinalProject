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
		pBean.setProjectId(getNewProjectId());
		pBean.setCreatedBy(1);
		pBean.setEstStartDate(sdf.parse(pBean.getEstStartDateInString()));
		pBean.setEstEndDate(sdf.parse(pBean.getEstEndDateInString()));
		Integer estMainDays = pBean.getEstStartDate().getDate() - pBean.getEstEndDate().getDate();
		pBean.setEstMainDays(estMainDays);
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("project.insertProject", pBean);
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
		Integer estMainDays = pBean.getEstStartDate().getDate() - pBean.getEstEndDate().getDate();
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
