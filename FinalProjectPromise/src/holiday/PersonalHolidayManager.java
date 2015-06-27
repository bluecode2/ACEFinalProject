package holiday;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
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
			Integer pageNum, Integer pageSize) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<PersonalHolidayBean> listResult = new ArrayList<PersonalHolidayBean>();
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("begin", begin);
		map.put("end", end);
		
		try {
			listResult = this.ibatis.queryForList(
					"personalHoliday.getPersonalHoliday", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listResult;
	}

	public PersonalHolidayBean getPersonalHolidayByHolId(int holId) {
		PersonalHolidayBean persHolidayBean = null;
		
		try {
			persHolidayBean = (PersonalHolidayBean) this.ibatis.queryForObject("personalHoliday.getPersonalHolidayByHolId", holId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public PersonalHolidayBean getPersonalHolidayEdit(int genId){
		PersonalHolidayBean persHolidayBean = null;
		
		try {
			persHolidayBean = (PersonalHolidayBean) this.ibatis
					.queryForObject("personalHoliday.getPersonalHolidayByHolId", genId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persHolidayBean;
	}

	public void editPersonalHoliday(PersonalHolidayBean persHolidayBean)
			throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("personalHoliday.editPersonalHoliday",
					persHolidayBean);
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

	public void deletePersonalHoliday(PersonalHolidayBean persHolidayBean) throws SQLException {
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("personalHoliday.deletePersonalHoliday", persHolidayBean);
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