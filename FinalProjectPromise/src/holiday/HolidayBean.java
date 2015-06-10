package holiday;

import java.util.Date;

public class HolidayBean {
	private Integer holiday_id;
	private Integer employee_id;
	private Integer created_by;
	private Integer updated_by;
	private String holiday_desc;
	private Date holiday_date;
	private Integer is_exchange_day;
	private Date create_date;
	private Date update_date;
	
	public Integer getHoliday_id() {
		return holiday_id;
	}
	public void setHoliday_id(Integer holiday_id) {
		this.holiday_id = holiday_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Integer getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}
	public String getHoliday_desc() {
		return holiday_desc;
	}
	public void setHoliday_desc(String holiday_desc) {
		this.holiday_desc = holiday_desc;
	}
	public Date getHoliday_date() {
		return holiday_date;
	}
	public void setHoliday_date(Date holiday_date) {
		this.holiday_date = holiday_date;
	}
	public Integer getIs_exchange_day() {
		return is_exchange_day;
	}
	public void setIs_exchange_day(Integer is_exchange_day) {
		this.is_exchange_day = is_exchange_day;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
