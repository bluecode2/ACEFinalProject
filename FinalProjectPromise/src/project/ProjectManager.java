package project;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project_member.ProjectMemberBean;
import user.UserBean;
import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.Constant;

public class ProjectManager {

	private SqlMapClient ibatis;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ProjectManager() {
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
			listProject = this.ibatis
					.queryForList("project.getAllProject", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.ibatis.endTransaction();
		}

		return listProject;
	}

	public List<ProjectBean> getProjectListForRole(String col, Integer input,
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
			listProject = this.ibatis.queryForList(
					"project.getProjectListForRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.ibatis.endTransaction();
		}

		return listProject;
	}

	public Integer getCountProjectListForRole(String column, Integer value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"project.countProjectListForRole", map);
		return result;
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

	public ProjectBean getProjectByID(Integer tempProjectID)
			 {
		ProjectBean pBean = null;
		try {
			pBean = (ProjectBean) this.ibatis.queryForObject(
					"project.getProjectbyId", tempProjectID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pBean;
	}

	public Integer getNewProjectId() throws SQLException {
		Integer newProjectId = (Integer) this.ibatis.queryForObject(
				"project.getNewProjectId", null);
		return newProjectId;
	}

	public void insertProject(ProjectBean pBean) throws SQLException,
			ParseException {
		try {
			this.ibatis.startTransaction();
			Integer newProjId = getNewProjectId();
			if (newProjId == null) {
				newProjId = 1;
			}

			pBean.setProjectId(newProjId);
			this.ibatis.insert("project.insertProject", pBean);
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

	public void updateProject(ProjectBean pBean) throws ParseException,
			SQLException {
		pBean.setUpdatedBy(1);
		pBean.setEstStartDate(sdf.parse(pBean.getEstStartDateInString()));
		pBean.setEstEndDate(sdf.parse(pBean.getEstEndDateInString()));
		Integer estMainDays = pBean.getEstEndDate().getDate()
				- pBean.getEstStartDate().getDate();
		pBean.setEstMainDays(estMainDays);
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("project.updateProject", pBean);
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

	public List<ProjectBean> getProjectInvolved(String col, String input,
			Integer pageNum, Integer pageSize, Integer empId)
			throws ClassNotFoundException, SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<ProjectBean> listProject = new ArrayList<ProjectBean>();
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("employeeId", empId);

		try {
			listProject = this.ibatis.queryForList(
					"project.getProjectInvolved", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.ibatis.endTransaction();
		}

		return listProject;
	}

	public List<ProjectBean> getListProjectToEvaluate(String col, String input,
			Integer pageNum, Integer pageSize, Integer deptId)
			throws SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		map.put("deptId", deptId);
		map.put("projectStatus", Constant.GeneralCode.PROJECT_STATUS_WAITING);

		List<ProjectBean> arr = this.ibatis.queryForList(
				"project.getAllProjectToEvaluate", map);

		return arr;
	}

	public int getCountProjectToEvaluate(String col, String input,
			Integer deptId) throws SQLException {
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("deptId", deptId);
		map.put("projectStatus", Constant.GeneralCode.PROJECT_STATUS_WAITING);
		int tmpCount = (Integer) this.ibatis.queryForObject(
				"project.countProjectToEvaluate", map);
		return tmpCount;
	}

	public void setApproveProject(int projectId, int updatedBy)
			throws SQLException {
		String projectStatus = Constant.GeneralCode.PROJECT_STATUS_APPROVE;

		Map m = new HashMap();
		m.put("projectId", projectId);
		m.put("updatedBy", updatedBy);
		m.put("projectStatus", projectStatus);

		try {
			this.ibatis.startTransaction();
			this.ibatis.update("project.setToApproveProject", m);
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

	public void setDeclineProject(int projectId, int updatedBy, String remarks)
			throws SQLException {
		String projectStatus = Constant.GeneralCode.PROJECT_STATUS_ONGOING;

		Map m = new HashMap();
		m.put("projectId", projectId);
		m.put("updatedBy", updatedBy);
		m.put("remarks", remarks);
		m.put("projectStatus", projectStatus);

		try {
			this.ibatis.startTransaction();
			this.ibatis.update("project.setToDeclineProject", m);
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

	public Integer getCountProjectInvolved(Integer empId, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("employeeId", empId);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"project.countProjectInvolved", map);
		return result;
	}
}
