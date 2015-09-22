package holiday;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PersonalHolidayManager {
	private SqlMapClient ibatis;

	public PersonalHolidayManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}

	public List<PersonalHolidayBean> getPersonalHoliday(String col, String input, String input2,
			Integer pageNum, Integer pageSize) {
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		List<PersonalHolidayBean> listResult = Collections.EMPTY_LIST;
		
		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("searchValue2", input2);
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
	
	public boolean insertPersonalHoliday(PersonalHolidayBean persHolidayBean){
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.insert("personalHoliday.insertPersonalHoliday",
						persHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
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

	public boolean editPersonalHoliday(PersonalHolidayBean persHolidayBean){
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("personalHoliday.editPersonalHoliday",
						persHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deletePersonalHoliday(PersonalHolidayBean persHolidayBean) throws SQLException {
		boolean flag = true;
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("personalHoliday.deletePersonalHoliday", persHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public Integer getCountPersonalHoliday(String column, String value, String value2)
			throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("searchValue2", value2);
		Integer result = (Integer) this.ibatis.queryForObject(
				"personalHoliday.countPersonalHoliday", map);
		return result;
	}
	
	public List<PersonalHolidayBean> getPersonalHolidayForCalendar(Integer month, Integer year, Integer employeeId) {

		Map map = new HashMap();
		map.put("month", month);
		map.put("year", year);
		map.put("employeeId", employeeId);
		
		List<PersonalHolidayBean> listResult = Collections.EMPTY_LIST;

		try {
			listResult = this.ibatis.queryForList(
					"personalHoliday.getPersonalHolidayForCalendar", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listResult;
	}
}
