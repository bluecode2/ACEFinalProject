package holiday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.Constant;

public class PersonalHolidayBean {
	private Integer holidayId;
	private Integer employeeId;
	private Integer createdBy;
	private Integer updatedBy;
	private String holidayDesc;
	private Date holidayDate;
	private String holidayDateInString;
	private Boolean isExchangeDay;
	private Integer isExchangeDayInt;
	private Date createDate;
	private String createDateInString;
	private Date updateDate;
	private String updateDateInString;
	private String empDisplay;
	private String employeeName;
	private String holidayDateDisplay;
	private Integer isDeleted;
	private String holidayType;
	private String holidayTypeName;
	
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	SimpleDateFormat df2 = new SimpleDateFormat(Constant.StringFormat2.dateFormat);
	
	public String getHolidayDateDisplay() {
		try {
			return df2.format(holidayDate);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
	}
	
	public Integer getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getHolidayDesc() {
		return holidayDesc;
	}
	public void setHolidayDesc(String holidayDesc) {
		this.holidayDesc = holidayDesc;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
		
		if (holidayDate != null) {
			this.holidayDateInString = df.format(holidayDate.getTime());
		} else {
			this.holidayDateInString = "";
		}
	}
	public String getHolidayDateInString() {
		return holidayDateInString;
	}
	public void setHolidayDateInString(String holidayDateInString) {
		this.holidayDateInString = holidayDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(holidayDateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.holidayDateInString = "";
			date = null;
		}
		this.holidayDate = date;
	}
	public Boolean getIsExchangeDay() {
		return isExchangeDay;
	}
	public void setIsExchangeDay(Boolean isExchangeDay) {
		this.isExchangeDay = isExchangeDay;
	}
	public Integer getIsExchangeDayInt() {
		if(getIsExchangeDay()==null)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	public void setIsExchangeDayInt(Integer isExchangeDayInt) {
		this.isExchangeDayInt = isExchangeDayInt;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		if (createDate != null){
			this.createDateInString = df.format(createDate.getTime());
		} else {
			this.createDateInString = "";
		}
	}
	public String getCreateDateInString() {
		return createDateInString;
	}
	public void setCreateDateInString(String createDateInString) {
		this.createDateInString = createDateInString;
		Date date = new Date();
		try {
			date = df.parse(createDateInString);
		} catch (Exception pe){
			//pe.printStackTrace();
			this.createDateInString = "";
			date = null;
		}
		this.createDate = date;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		if (updateDate != null){
			this.updateDateInString = df.format(updateDate.getTime());
		} else {
			this.updateDateInString = "";
		}
	}
	public String getUpdateDateInString() {
		return updateDateInString;
	}
	public void setUpdateDateInString(String updateDateInString) {
		this.updateDateInString = updateDateInString;
		Date date = new Date();
		try {
			date = df.parse(updateDateInString);
		} catch (Exception pe){
			//pe.printStackTrace();
			this.updateDateInString = "";
			date = null;
		}
		this.updateDate = date;
	}
	public String getEmpDisplay() {
		return empDisplay;
	}
	public void setEmpDisplay(String empDisplay) {
		this.empDisplay = empDisplay;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getHolidayTypeName() {
		return holidayTypeName;
	}

	public void setHolidayTypeName(String holidayTypeName) {
		this.holidayTypeName = holidayTypeName;
	}
}
