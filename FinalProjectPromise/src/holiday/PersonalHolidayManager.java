package holiday;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PersonalHolidayManager {
	private SqlMapClient ibatis;

	public PersonalHolidayManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<PersonalHolidayBean> getPersonalHoliday(String col, String input,
			Integer pageNum, Integer pageSize) throws SQLException {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		List<PersonalHolidayBean> listResult = this.ibatis.queryForList(
				"personalHoliday.getPersonalHoliday", map);
		return listResult;
	}

	public PersonalHolidayBean getPersonalHolidayByHolId(int holId) throws SQLException {
		PersonalHolidayBean persHolidayBean = (PersonalHolidayBean) this.ibatis.queryForObject("personalHoliday.getPersonalHolidayByHolId", holId);
		return persHolidayBean;
	}
	
	public int getNewGenHolidayId() throws SQLException {
		int tmpNewHolId = (Integer) this.ibatis.queryForObject("personalHoliday.getNewHolId", null);
		return tmpNewHolId;
	}
	
	public void insertPersonalHoliday(PersonalHolidayBean persHolidayBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("personalHoliday.insertPersonalHoliday",
					persHolidayBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public PersonalHolidayBean getPersonalHolidayEdit(int genId)
			throws SQLException {
		PersonalHolidayBean persHolidayBean = (PersonalHolidayBean) this.ibatis
				.queryForObject("personalHoliday.getPersonalHolidayEdit", genId);
		return persHolidayBean;
	}

	public void editPersonalHoliday(PersonalHolidayBean persHolidayBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("personalHoliday.editPersonalHoliday",
					persHolidayBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}

	public void deletePersonalHoliday(PersonalHolidayBean persHolidayBean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("personalHoliday.deletePersonalHoliday", persHolidayBean);
			this.ibatis.commitTransaction();
		} finally {
			this.ibatis.endTransaction();
		}
	}
	
	public Integer getCountPersonalHoliday(String column, String value)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		Integer result = (Integer) this.ibatis.queryForObject(
				"personalHoliday.countPersonalHoliday", map);
		return result;
	}
}