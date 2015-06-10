package holiday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralHolidayBean {
	private Integer genHolidayId;
	private String genHolidayName;
	private Date genHolidayDate;
	private String genHolidayDateInString;

	SimpleDateFormat df = new SimpleDateFormat();

	public Integer getGenHolidayId() {
		return genHolidayId;
	}

	public void setGenHolidayId(Integer genHolidayId) {
		this.genHolidayId = genHolidayId;
	}

	public String getGenHolidayName() {
		return genHolidayName;
	}

	public void setGenHolidayName(String genHolidayName) {
		this.genHolidayName = genHolidayName;
	}

	public Date getGenHolidayDate() {
		return genHolidayDate;
	}

	public void setGenHolidayDate(Date genHolidayDate) {
		this.genHolidayDate = genHolidayDate;

		if (genHolidayDate != null) {
			this.genHolidayDateInString = df.format(genHolidayDate.getTime());
		} else {
			this.genHolidayDateInString = "";
		}
	}

	public String getGenHolidayDateInString() {
		return genHolidayDateInString;
	}

	public void setGenHolidayDateInString(String genHolidayDateInString) {
		this.genHolidayDateInString = genHolidayDateInString;

		Date date = new Date();
		try {
			date = df.parse(genHolidayDateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.genHolidayDateInString = "";
			date = null;
		}
		this.genHolidayDate = date;
	}
}
