package general;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralParamManager {

	private SqlMapClient ibatis;

	public GeneralParamManager() {
		// TODO Auto-generated constructor stub
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<GeneralParamBean> getAllGeneralParam(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);

		List<GeneralParamBean>	arr = new  ArrayList<GeneralParamBean>(); 
		
		try {
			arr = this.ibatis.queryForList(
					"genParam.selectGenParam", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return arr;
	}
	public GeneralParamBean getGenParamByParamId(String genParamId) {
		GeneralParamBean genParamBean = null;
		try {
			genParamBean = (GeneralParamBean) this.ibatis.queryForObject("genParam.getGenParamByGenParamId", genParamId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genParamBean;
	}

	public int getCountGeneralParam(String column, String value)
			throws SQLException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);

		int count = (Integer) this.ibatis.queryForObject(
				"genParam.countGenParam", map);

		return count;
	}

	public void insertGeneralParam(GeneralParamBean genParamBean)
			throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("genParam.insertGenParam", genParamBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGeneralParam(GeneralParamBean genParamBean)
			throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("genParam.updateGenParam", genParamBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGeneralParam(GeneralParamBean genParamBean) throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("genParam.deleteGenParam", genParamBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNewParamId() throws SQLException{
		String maxId = (String) this.ibatis.queryForObject("genParam.getNewParamId", null);
		return maxId;
	}
}
