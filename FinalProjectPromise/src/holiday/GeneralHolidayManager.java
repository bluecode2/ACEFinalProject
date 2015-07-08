package holiday;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			String input2, Integer pageNum, Integer pageSize) {

		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		Map map = new HashMap();
		map.put("searchField", col);
		map.put("searchValue", input);
		map.put("searchValue2", input2);
		map.put("begin", begin);
		map.put("end", end);
		List<GeneralHolidayBean> listResult = Collections.EMPTY_LIST;

		try {
			listResult = this.ibatis.queryForList(
					"generalHoliday.getGeneralHoliday", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listResult;
	}

	public GeneralHolidayBean getGeneralHolidayByHolId(int holId)
		{
		GeneralHolidayBean genHolBean = null;
		
		try {
			genHolBean = (GeneralHolidayBean) this.ibatis.queryForObject("generalHoliday.getGeneralHolidayByHolId",
							holId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genHolBean;
	}

	public int getNewGenHolidayId() throws SQLException {
		int tmpNewHolId = (Integer) this.ibatis.queryForObject(
				"generalHoliday.getNewHolId", null);
		return tmpNewHolId;
	}

	public void insertGeneralHoliday(GeneralHolidayBean genHolidayBean)
			throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				int newId = getNewGenHolidayId();
				genHolidayBean.setGenHolidayId(newId);
				this.ibatis.insert("generalHoliday.insertGeneralHoliday",genHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GeneralHolidayBean getGeneralHolidayEdit(int genId){
		GeneralHolidayBean genHolidayBean = null;
			try {
				genHolidayBean = (GeneralHolidayBean) this.ibatis
					.queryForObject("generalHoliday.getGeneralHolidayEdit", genId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return genHolidayBean;
	}

	public void editGeneralHoliday(GeneralHolidayBean genHolidayBean)
			throws SQLException {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("generalHoliday.editGeneralHoliday",
						genHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGeneralHoliday(GeneralHolidayBean genHolidayBean) {
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("generalHoliday.deleteGeneralHoliday",
						genHolidayBean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getCountGeneralHoliday(String column, String value,
			String value2) throws SQLException, ClassNotFoundException {
		Map map = new HashMap();
		map.put("searchField", column);
		map.put("searchValue", value);
		map.put("searchValue2", value2);
		Integer result = (Integer) this.ibatis.queryForObject(
				"generalHoliday.countGeneralHoliday", map);
		return result;
	}

	public void generateWeekend(String startDate, String endDate,
			String checkDays, Integer createdBy) throws SQLException,
			ClassNotFoundException {
		try {
			try {
				Map map = new HashMap();
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("checkDays", checkDays);
				map.put("createdBy", createdBy);

				ibatis.startTransaction();
				this.ibatis.insert("generalHoliday.generateWeekend", map);
				ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<GeneralHolidayBean> getGeneralHolidayForCalendar(Integer month, Integer year) {

		Map map = new HashMap();
		map.put("month", month);
		map.put("year", year);
		
		List<GeneralHolidayBean> listResult = Collections.EMPTY_LIST;

		try {
			listResult = this.ibatis.queryForList(
					"generalHoliday.getGeneralHolidayForCalendar", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listResult;
	}
}