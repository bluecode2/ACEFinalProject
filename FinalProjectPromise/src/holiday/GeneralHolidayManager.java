package holiday;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GeneralHolidayManager {
	private SqlMapClient ibatis;

	public GeneralHolidayManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<GeneralHolidayBean> getGeneralHoliday(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		List<GeneralHolidayBean> listResult = this.ibatis.queryForList(
				"generalHoliday.getGeneralHoliday", map);
		return listResult;
	}

	public GeneralHolidayBean getGeneralHolidayByHolId(int holId) throws SQLException {
		GeneralHolidayBean genHolBean = (GeneralHolidayBean) this.ibatis.queryForObject("generalHoliday.getGeneralHolidayByHolId", holId);
		return genHolBean;
	}
	
	public int getNewGenHolidayId() throws SQLException {
		int tmpNewHolId = (Integer) this.ibatis.queryForObject("generalHoliday.getNewHolId", null);
		return tmpNewHolId;
	}
	
	public void insertGeneralHoliday(GeneralHolidayBean genHolidayBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("generalHoliday.insertGeneralHoliday",
					genHolidayBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public GeneralHolidayBean getGeneralHolidayEdit(int genId)
			throws SQLException {
		GeneralHolidayBean genHolidayBean = (GeneralHolidayBean) this.ibatis
				.queryForObject("generalHoliday.getGeneralHolidayEdit", genId);
		return genHolidayBean;
	}

	public void editGeneralHoliday(GeneralHolidayBean genHolidayBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("generalHoliday.editGeneralHoliday",
					genHolidayBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void deleteGeneralHoliday(Integer genId) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("generalHoliday.deleteGeneralHoliday", genId);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public Integer getCountGeneralHoliday(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"generalHoliday.countGeneralHoliday", map);
		return result;
	}
}