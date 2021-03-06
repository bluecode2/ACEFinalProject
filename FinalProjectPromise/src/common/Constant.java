package common;

public class Constant {
	public static class StringFormat{
		public static final String DATE_FORMAT = "yyyy-MM-dd";
	}
	
	public static class StringFormat2{
		public static final String DATE_FORMAT = "dd MMMM yyyy";
	}
	
	public static class DefaultValue{
		public static final String MIN_DATE = "1900-01-01";
		public static final String MAX_DATE = "2100-12-31";
	}
	
	public static final Integer PAGE_SIZE = 10;
	public static final Integer NOTIFICATION_SIZE = 5;
	public static final Integer PAGE_NAVIGATION_SIZE = 5;
	
	public static class MenuCode{
		public static final String DEPARTMENT = "MS0101";
		public static final String DEPARTMENT_ENTRY = "MS0101-E";
		
		public static final String EMPLOYEE_RANK = "MS0102";
		public static final String EMPLOYEE_RANK_ENTRY = "MS0102-E";
		
		public static final String EMPLOYEE = "MS0103";
		public static final String EMPLOYEE_ENTRY = "MS0103-E";
		
		public static final String PROJECT_ROLE = "MS0104";
		public static final String PROJECT_ROLE_ENTRY = "MS0104-E";
		
		public static final String HOLIDAY = "MS0105";
		public static final String GENERAL_HOLIDAY = "MS010501";
		public static final String GENERAL_HOLIDAY_ENTRY = "MS010501-E";
		public static final String GENERATE_HOLIDAY_ENTRY = "MS010501-G";
		public static final String PERSONAL_HOLIDAY = "MS010502";
		public static final String PERSONAL_HOLIDAY_ENTRY = "MS010502-E";
		
		public static final String USER_MANAGEMENT = "MS02";
		public static final String USER = "MS0201";
		public static final String USER_ENTRY = "MS0201-E";
		public static final String USER_ROLE = "MS0202";
		public static final String USER_ROLE_ENTRY = "MS0202-E";
		
		public static final String SYSTEM_CONFIGURATION = "MS03";
		
		public static final String MENU_MANAGEMENT = "MS0301";
		
		public static final String GENERAL_CODE = "MS0302";
		public static final String GENERAL_CODE_ENTRY = "MS0302-E";
		public static final String GENERAL_PARAMETER = "MS0303";
		public static final String GENERAL_PARAMETER_ENTRY = "MS0303-E";
		
		public static final String PROJECT = "TR0101";
		public static final String PROJECT_ENTRY = "TR0101-E";
		public static final String PROJECT_MEMBER = "TR0101-M";
		public static final String PROJECT_TASK = "TR010101";
		public static final String PROJECT_TASK_ENTRY = "TR010101-E";
		public static final String PROJECT_INVOLVED = "TR0102";
		public static final String PROPOSE_PROJECT_TASK = "TR0102-E";
		public static final String APPROVE_PROJECT_TASK = "TR0102-A";
		public static final String PROJECT_INVOLVED_TASK = "TR010201";
		public static final String PROJECT_APPROVAL = "TR0103";
		public static final String PROJECT_APPROVAL_EVALUATE = "TR0103-E";
		
		public static final String PROPOSE_INDEPENDENT_TASK = "TR0201";
		public static final String PROPOSE_INDEPENDENT_TASK_ENTRY = "TR0201-E";
		public static final String APPROVE_PROPOSED_INDEPENDENT_TASK = "TR0202";
		public static final String ASSIGN_TASK_LIST= "TR0203" ; 
		public static final String ASSIGN_TASK_ENTRY= "TR0203-E"; 
		public static final String CURRENT_TASK_LIST= "TR0204"; 

		
		public static final String NOTIFICATIONS= "NT01";
		
		public static final String REPORTS = "ZZ01";
		
	}
	
	public static class GeneralCode{
		public static final String PERSONAL_HOLIDAY_TYPE = "PH_TYPE";
		public static final String GENERAL_HOLIDAY_TYPE = "GH_TYPE";
		
		public static final String PROJECT_STATUS_NOT_STARTED = "PR_STAT_01";
		public static final String PROJECT_STATUS_ONGOING = "PR_STAT_02";
		public static final String PROJECT_STATUS_WAITING_FOR_APPROVAL = "PR_STAT_03";
		public static final String PROJECT_STATUS_COMPLETED = "PR_STAT_04";
		public static final String PROJECT_STATUS_ON_HOLD = "PR_STAT_05";
		public static final String PROJECT_STATUS_FORCE_CLOSED = "PR_STAT_98";
		public static final String PROJECT_STATUS_CANCELLED = "PR_STAT_99";
		
		public static final String TASK_STATUS_PROPOSED = "TA_STAT_01";
		public static final String TASK_STATUS_NOT_STARTED = "TA_STAT_02";
		public static final String TASK_STATUS_ONGOING = "TA_STAT_03";
		public static final String TASK_STATUS_WAITING_FOR_APPROVAL = "TA_STAT_04";
		public static final String TASK_STATUS_COMPLETED = "TA_STAT_05";
		public static final String TASK_STATUS_ON_HOLD = "TA_STAT_06";
		public static final String TASK_STATUS_APPROVE = "TA_STAT_07";
		public static final String TASK_STATUS_DECLINE = "TA_STAT_98";
		public static final String TASK_STATUS_FORCE = "TA_STAT_97";
		public static final String TASK_STATUS_CANCELLED = "TA_STAT_99";

	}
	
	public static class GeneralParameter{
		public static final String AD_ADMIN_USERNAME = "ADAdminUser";
		public static final String AD_ADMIN_PASSWORD = "ADAdminPass";
		public static final String PROJECT_MANAGER_ROLE_ID = "ProjectManager";
		public static final String APPLICATION_PROPERTIES_PATH = "appPropPath";
	}
	
	public static class GeneralHolidayType{
		public static final String WEEKEND = "GH_TYPE_01";
	}
}
