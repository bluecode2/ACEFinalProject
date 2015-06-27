package notification;

import ibatis.IbatisHelper;
import independent_task.IndependentTaskBean;
import independent_task.IndependentTaskManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.ProjectBean;
import project.ProjectManager;
import project_role.ProjectRoleBean;
import project_role.ProjectRoleManager;

import com.ibatis.sqlmap.client.SqlMapClient;


import common.CommonFunction;
import common.Constant;
import employee.EmployeeBean;
import employee.EmployeeManager;

public class NotificationManager {
	private SqlMapClient ibatis;

	public NotificationManager() {
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<NotificationBean> getListAllNotificationByEmployee(Integer employeeId,Integer pageNum, Integer pageSize){
		List<NotificationBean> list = new ArrayList<NotificationBean>();
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("begin", begin);
		map.put("end", end);
	
		try {
			list =  this.ibatis.queryForList("notification.getAllNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<NotificationBean> getListUnreadNotificationByEmployee(Integer employeeId){
		List<NotificationBean> list = new ArrayList<NotificationBean>();
		
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("isRead", 0);
		map.put("begin", 0);
		map.put("end", Constant.notificationSize);
	
		try {
			list =  this.ibatis.queryForList("notification.getAllNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public NotificationBean getNotificationById(Integer notificationId){
		NotificationBean bean = null;
	
		try {
			bean =  (NotificationBean) this.ibatis.queryForObject("notification.getNotificationById", notificationId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;	
	}
	
	public Integer getNotificationNewId() throws SQLException{
		Integer newId = (Integer) this.ibatis.queryForObject("notification.getNotificationNewId", null);
		return newId;
	}
	
	public Integer getCountNotificationByEmployee(Integer employeeId) {
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		
		Integer result = 0;
		try {
			result = (Integer) this.ibatis.queryForObject(
					"notification.countNotificationByEmployee", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertNotification(NotificationBean bean){
		boolean flag = true;
		
		try {
			this.ibatis.startTransaction();
			bean.setNotificationId(getNotificationNewId());
			this.ibatis.insert("notification.insertNotification", bean);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean updateNotificationAsRead(Integer notificationId){
		boolean flag = true;
		
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("notification.updateReadNotification", notificationId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
			try {
				this.ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return flag;
	}
	
	public boolean createNotificationProjectMember(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer projectId, Integer projectRoleId){
		ProjectManager projMan = new ProjectManager();
		ProjectRoleManager projRoleMan = new ProjectRoleManager();
		
		ProjectBean projBean =  projMan.getProjectByID(projectId);
		ProjectRoleBean projRoleBean = projRoleMan.getProjectRoleById(projectRoleId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		String desc = creatorEmp.getEmployeeName() + " just assigned you as a " + projRoleBean.getProjectRoleName() +" in a new project : " + projBean.getProjectName();
		
		NotificationBean bean = new NotificationBean();
		
		bean.setEmployeeId(assignedEmployeeId);
		
		if(projectRoleId == Integer.valueOf(CommonFunction.getGeneralParameterValue(Constant.GeneralParameter.PROJECT_MANAGER_ROLE_ID)))
			bean.setNotificationUrl("project.do");
		else
			bean.setNotificationUrl("projectInvoled.do");
		bean.setNotificationDesc(desc);
		
		insertNotification(bean);
		return true;
	}
	
	public boolean createNotificationRemoveProjectMember(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer projectId, Integer projectRoleId){
		ProjectManager projMan = new ProjectManager();
		ProjectRoleManager projRoleMan = new ProjectRoleManager();
		
		ProjectBean projBean =  projMan.getProjectByID(projectId);
		ProjectRoleBean projRoleBean = projRoleMan.getProjectRoleById(projectRoleId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		String desc = creatorEmp.getEmployeeName() + " just remove your position as a " + projRoleBean.getProjectRoleName() +" from project : " + projBean.getProjectName();
		
		NotificationBean bean = new NotificationBean();
		
		bean.setEmployeeId(assignedEmployeeId);
		bean.setNotificationUrl("project.do");
		bean.setNotificationDesc(desc);
		
		insertNotification(bean);
		return true;
	}
	
	public boolean createNotificationAssignIndependentTask(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer taskId) {
		IndependentTaskManager itMan = new IndependentTaskManager();
		IndependentTaskBean itBean = itMan.getDataForEdit(taskId);
		System.out.println(taskId+" "+itBean);
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		NotificationBean bean = new NotificationBean();
		
		//not started assign, waiting, completed, decline,cancel
		String desc="";
		if(itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_NOT_STARTED)){

			desc = creatorEmp.getEmployeeName() + " assigned you a task : " + itBean.getTaskName();
			bean.setNotificationUrl("myCurrentTask.do");
		
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL)) {
			
			desc = creatorEmp.getEmployeeName() + " submitted a task to you : " + itBean.getTaskName();
			bean.setNotificationUrl("assignTask.do");
			//assign task
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_CANCELLED)) {
			
			desc = creatorEmp.getEmployeeName() + " canceled your task : " + itBean.getTaskName();
			bean.setNotificationUrl("assignTask.do");
			//assign task
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING)) {
			
			desc = creatorEmp.getEmployeeName() + " declined your task : " + itBean.getTaskName();
			bean.setNotificationUrl("myCurrentTask.do");
		
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_COMPLETED)) {
			
			desc = creatorEmp.getEmployeeName() + " approved your task : " + itBean.getTaskName();
			bean.setNotificationUrl("myCurrentTask.do");
		
		}
		
		bean.setEmployeeId(assignedEmployeeId);
		bean.setNotificationDesc(desc);	
		insertNotification(bean);
		
		return true;
	}
	
	public boolean createNotificationProposeIndependentTask(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer taskId) {
		IndependentTaskManager itMan = new IndependentTaskManager();
		IndependentTaskBean itBean = itMan.getDataForEdit(taskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		NotificationBean bean = new NotificationBean();
		
		//propose, approve, decline
		String desc="";
		if(itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_PROPOSED)){

			desc = creatorEmp.getEmployeeName() + " proposed you a task : " + itBean.getTaskName();			
			bean.setNotificationUrl("approveTask.do");
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_DECLINE)) {
		
			desc = creatorEmp.getEmployeeName() + " declined your task : " + itBean.getTaskName();
			bean.setNotificationUrl("proposedTask.do");
		}
		else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_APPROVE)) {
			
			desc = creatorEmp.getEmployeeName() + " approve your task : " + itBean.getTaskName();
			bean.setNotificationUrl("proposedTask.do");
		}

		bean.setEmployeeId(assignedEmployeeId);
		bean.setNotificationDesc(desc);
		insertNotification(bean);
		
		return true;
	}
	
	public boolean createNotification() {
		
		return true;
	}

}
