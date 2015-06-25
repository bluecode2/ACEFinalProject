package project_role;

import general.GeneralParamBean;
import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProjectRoleManager {
	private SqlMapClient ibatis;

	public ProjectRoleManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<ProjectRoleBean> getAllProjectRole(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<ProjectRoleBean> arr = new ArrayList<ProjectRoleBean>();

		try {
			arr = this.ibatis.queryForList("projectRole.getProjectRole", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arr;
	}

	public List<ProjectRoleBean> getAllProjectRoleForPopUp()
			throws SQLException {
		List<ProjectRoleBean> arr = new ArrayList<ProjectRoleBean>();

		try {
			arr = this.ibatis.queryForList(
					"projectRole.getProjectRoleForPopUp", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arr;
	}

	public ProjectRoleBean getProjectRoleById(Integer tmpProjectRoleId)
			throws SQLException {
		ProjectRoleBean projectRoleBean = (ProjectRoleBean) this.ibatis
				.queryForObject("projectRole.getProjectRoleById",
						tmpProjectRoleId);
		return projectRoleBean;
	}

	public int getCountGeneralHoliday(String column, String value)
			throws SQLException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);

		int count = (Integer) this.ibatis.queryForObject(
				"projectRole.countGeneralHoliday", map);

		return count;
	}

	public void insertProjectRole(ProjectRoleBean projectRoleBean)
			throws SQLException {

		try {
			this.ibatis.startTransaction();
			this.ibatis
					.insert("projectRole.insertProjectRole", projectRoleBean);
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

	public void updateProjectRole(ProjectRoleBean projectRoleBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("projectRole.editProjectRole", projectRoleBean);
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

	public void deleteProjectRole(ProjectRoleBean projectRoleBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis
					.update("projectRole.deleteProjectRole", projectRoleBean);
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

	public Integer getMaxProjectRoleId() throws SQLException {
		Integer maxId = (Integer) this.ibatis.queryForObject(
				"projectRole.getMaxProjectRoleId", null);
		return maxId;
	}

	public Integer getProjectRoleIdByCode() throws SQLException {
		Integer getProjectRoleIdByCode = (Integer) this.ibatis.queryForObject(
				"projectRole.getProjectRoleIdByCode", null);

		return getProjectRoleIdByCode;
	}
}
