package holiday;

import general.GeneralCodeBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import common.Constant;

public class GeneralHolidayForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat df = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	private List<GeneralHolidayBean> 	arrList = new ArrayList<GeneralHolidayBean>();
	private GeneralHolidayBean 			genHolidayBean  = new GeneralHolidayBean();
	private String 						task="";
	private Boolean 					isAdd = false;
	private String 						searchValue = "";
	private String 						searchValue2 = "";
	private String 						searchField = "";
	private String 						currSearchValue ;
	private String 						currSearchValue2 = Constant.DefaultValue.maxDate;
	private String 						currSearchField = "byDate";
	private Integer							currPage = 1;
	private Integer 						pageCount = 1;
	private Integer						selectedId;
	
	private Date						startDate;
	private String						startDateInString;
	private Date						endDate;
	private String						endDateInString;
	private String						checkDays;
	
	private List<GeneralCodeBean>		listOfGenCode = new ArrayList<GeneralCodeBean>();
	
	public GeneralHolidayForm() {
		// TODO Auto-generated constructor stub
		currSearchValue = df.format(new Date().getTime());
	}
	
	public List<GeneralHolidayBean> getArrList() {
		return arrList;
	}

	public void setArrList(List<GeneralHolidayBean> arrList) {
		this.arrList = arrList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public GeneralHolidayBean getGenHolidayBean() {
		return genHolidayBean;
	}

	public void setGenHolidayBean(GeneralHolidayBean genHolidayBean) {
		this.genHolidayBean = genHolidayBean;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchValue2() {
		return searchValue2;
	}

	public void setSearchValue2(String searchValue2) {
		this.searchValue2 = searchValue2;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getCurrSearchValue() {
		return currSearchValue;
	}

	public void setCurrSearchValue(String currSearchValue) {
		this.currSearchValue = currSearchValue;
	}

	public String getCurrSearchValue2() {
		return currSearchValue2;
	}

	public void setCurrSearchValue2(String currSearchValue2) {
		this.currSearchValue2 = currSearchValue2;
	}

	public String getCurrSearchField() {
		return currSearchField;
	}

	public void setCurrSearchField(String currSearchField) {
		this.currSearchField = currSearchField;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageCount() {
		if(this.pageCount.intValue()==0)
			return 1;
		else
			return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}


	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		
		if (startDate != null) {
			this.startDateInString = df.format(startDate.getTime());
		} else {
			this.startDateInString = "";
		}
	}
	public String getStartDateInString() {
		return startDateInString;
	}
	public void setStartDateInString(String startDateInString) {
		this.startDateInString = startDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(startDateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			this.startDateInString = "";
			date = null;
		}
		this.startDate = date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		
		if (endDate != null) {
			this.endDateInString = df.format(endDate.getTime());
		} else {
			this.endDateInString = "";
		}
	}
	public String getEndDateInString() {
		return endDateInString;
	}
	public void setEndDateInString(String endDateInString) {
		this.endDateInString = endDateInString;
		
		Date date = new Date();
		try {
			date = df.parse(endDateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			this.endDateInString = "";
			date = null;
		}
		this.endDate = date;
	}
	public String getCheckDays() {
		return checkDays;
	}
	public void setCheckDays(String checkDays) {
		this.checkDays = checkDays;
	}

	public List<GeneralCodeBean> getListOfGenCode() {
		return listOfGenCode;
	}

	public void setListOfGenCode(List<GeneralCodeBean> listOfGenCode) {
		this.listOfGenCode = listOfGenCode;
	}
}
