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
import project_task.ProjectTaskBean;
import project_task.ProjectTaskManager;
import proposed_task.ProposedTaskBean;
import proposed_task.ProposedTaskManager;

import com.ibatis.sqlmap.client.SqlMapClient;
import common.Constant;
import department.DepartmentBean;
import department.DepartmentManager;
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
		map.put("end", Constant.NOTIFICATION_SIZE);
	
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
	
	public Integer getCountNotificationByEmployee(Integer employeeId,Boolean isRead) {
		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("isRead", isRead?1:0);
		
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
			try {
				this.ibatis.startTransaction();
				bean.setNotificationId(getNotificationNewId());
				this.ibatis.insert("notification.insertNotification", bean);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public boolean updateNotificationAsRead(Integer notificationId){
		boolean flag = true;
		
		try {
			try {
				this.ibatis.startTransaction();
				this.ibatis.update("notification.updateReadNotification", notificationId);
				this.ibatis.commitTransaction();
			} finally {
				this.ibatis.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
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
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			String desc = creatorEmp.getEmployeeName() + " just assigned you as a " + projRoleBean.getProjectRoleName() +" in a new project : " + projBean.getProjectName();
			NotificationBean bean = new NotificationBean();
			bean.setEmployeeId(assignedEmployeeId);
			
			if(projectRoleId.intValue() == projRoleMan.getProjectManagerRoleId().intValue())
				bean.setNotificationUrl("project.do");
			else
				bean.setNotificationUrl("projectInvolved.do");
			
			bean.setNotificationDesc(desc);	
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationRemoveProjectMember(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer projectId, Integer projectRoleId){
		ProjectManager projMan = new ProjectManager();
		ProjectRoleManager projRoleMan = new ProjectRoleManager();
		
		ProjectBean projBean =  projMan.getProjectByID(projectId);
		ProjectRoleBean projRoleBean = projRoleMan.getProjectRoleById(projectRoleId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			String desc = creatorEmp.getEmployeeName() + " just remove your position as a " + projRoleBean.getProjectRoleName() +" from project : " + projBean.getProjectName();
			
			NotificationBean bean = new NotificationBean();
			
			bean.setEmployeeId(assignedEmployeeId);
			bean.setNotificationUrl("project.do");
			bean.setNotificationDesc(desc);
			
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationAssignIndependentTask(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer taskId) {
		IndependentTaskManager itMan = new IndependentTaskManager();
		IndependentTaskBean itBean = itMan.getDataForEdit(taskId);

		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			NotificationBean bean = new NotificationBean();
			String desc="";
			if(itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_NOT_STARTED)){	
	
				desc = creatorEmp.getEmployeeName() + " assigned you a task : " + itBean.getTaskName();
				bean.setNotificationUrl("myCurrentTask.do");
			
			}
			else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL)) {
				
				desc = creatorEmp.getEmployeeName() + " submitted a task to you : " + itBean.getTaskName();
				bean.setNotificationUrl("assignTask.do");
			
			}
			else if (itBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_CANCELLED)) {
				
				desc = creatorEmp.getEmployeeName() + " canceled your task : " + itBean.getTaskName();
				bean.setNotificationUrl("assignTask.do");
			
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
		}
		return true;
	}
	
	public boolean createNotificationProposeIndependentTask(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer taskId) {
		ProposedTaskManager ptMan = new ProposedTaskManager();
		ProposedTaskBean ptBean = ptMan.getPropTaskByPropTaskId(taskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			NotificationBean bean = new NotificationBean();
			String desc="";
			if(ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_PROPOSED)){
	
				desc = creatorEmp.getEmployeeName() + " proposed you a task : " + ptBean.getPropTaskName();			
				bean.setNotificationUrl("approveTask.do");
			}
			else if (ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_DECLINE)) {
			
				desc = creatorEmp.getEmployeeName() + " declined your propose task : " + ptBean.getPropTaskName();
				bean.setNotificationUrl("proposedTask.do");
			}
			else if (ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_APPROVE)) {
				
				desc = creatorEmp.getEmployeeName() + " approve your propose task : " + ptBean.getPropTaskName();
				bean.setNotificationUrl("proposedTask.do");
			}
	
			bean.setEmployeeId(assignedEmployeeId);
			bean.setNotificationDesc(desc);
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationProject(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer projectId) {
		ProjectManager pjMan = new ProjectManager();
		ProjectBean pjBean = pjMan.getProjectByID(projectId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
			
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			NotificationBean bean = new NotificationBean();
			String desc="";
			if (creatorEmployeeId != assignedEmployeeId) {	
				if (pjBean.getProjectStatus().equals(Constant.GeneralCode.PROJECT_STATUS_ONGOING)) {
					desc = creatorEmp.getEmployeeName() + " declined your project : " + pjBean.getProjectName();
					bean.setNotificationUrl("project.do");
				}
				else if (pjBean.getProjectStatus().equals(Constant.GeneralCode.PROJECT_STATUS_COMPLETED)) {
					desc = creatorEmp.getEmployeeName() + " accepted  your project : " + pjBean.getProjectName();
					bean.setNotificationUrl("project.do");
				}
				bean.setEmployeeId(assignedEmployeeId);
				bean.setNotificationDesc(desc);	
				insertNotification(bean);
			}
		}
		return true;
	}

	public boolean createNotificationSubmitedProject(Integer creatorEmployeeId, Integer deptId,  Integer projectId) {
		ProjectManager pjMan = new ProjectManager();
		ProjectBean pjBean = pjMan.getProjectByID(projectId);
		
		DepartmentManager dpMan = new DepartmentManager();
		DepartmentBean dpbean = dpMan.getDepartmentByDeptId(deptId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
	
		if (creatorEmployeeId.intValue() != dpbean.getDeptHeadId().intValue()) {
			NotificationBean bean = new NotificationBean();
			String  desc = creatorEmp.getEmployeeName() + " was waiting you to evaluate : " + pjBean.getProjectName();
			bean.setNotificationUrl("projectApproval.do");
			bean.setSessionParameter("projectId#"+pjBean.getProjectId());
		
			bean.setEmployeeId(dpbean.getDeptHeadId());
			bean.setNotificationDesc(desc);	
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationHapusProposeTask(Integer creatorEmployeeId, Integer empId,  Integer proposeTaskId) {
		ProposedTaskManager ptMan = new ProposedTaskManager();
		ProposedTaskBean ptBean = ptMan.getPropTaskByPropTaskId(proposeTaskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != empId.intValue()) {	
			NotificationBean bean = new NotificationBean();	
			String  desc = creatorEmp.getEmployeeName() + " was deleting his approve task to you : " +ptBean.getPropTaskName() ;
			bean.setNotificationUrl("#");
		
			bean.setEmployeeId(empId);
			bean.setNotificationDesc(desc);	
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationPauseIndependentTask(Integer creatorEmployeeId, Integer empId,  Integer taskId) {
		IndependentTaskManager itMan = new IndependentTaskManager();
		IndependentTaskBean itBean = itMan.getDataForEdit(taskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != empId.intValue()) {	
			NotificationBean bean = new NotificationBean();
			
			String  desc = creatorEmp.getEmployeeName() + " was pausing his task : " +itBean.getTaskName();
			bean.setNotificationUrl("assignTask.do");
		
			bean.setEmployeeId(empId);
			bean.setNotificationDesc(desc);	
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationProjectTask(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer taskId) {
		ProjectTaskManager ptMan = new ProjectTaskManager();
		ProjectTaskBean ptBean = ptMan.getTaskById(taskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			NotificationBean bean = new NotificationBean();
			String desc="";
			if(ptBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_NOT_STARTED)){
	
				desc = creatorEmp.getEmployeeName() + " assigned you a task : " + ptBean.getTaskName() +" in project "+ptBean.getProjectName();
				bean.setNotificationUrl("projectInvolvedTask.do");
			
			}
			else if (ptBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_WAITING_FOR_APPROVAL)) {
				desc = creatorEmp.getEmployeeName() + " submitted a task to you : " + ptBean.getTaskName()+" in project "+ptBean.getProjectName();
				bean.setNotificationUrl("projectTask.do");
			}
			else if (ptBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_CANCELLED)) {
				desc = creatorEmp.getEmployeeName() + " canceled your task : " + ptBean.getTaskName() +" in project "+ptBean.getProjectName();
				bean.setNotificationUrl("projectInvolvedTask.do");
			}
			else if (ptBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING)) {
				desc = creatorEmp.getEmployeeName() + " declined your task : " + ptBean.getTaskName() +" in project "+ptBean.getProjectName();
				bean.setNotificationUrl("projectInvolvedTask.do");
			}
			else if (ptBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_COMPLETED)) {
				desc = creatorEmp.getEmployeeName() + " approved your task : " + ptBean.getTaskName() +" in project "+ptBean.getProjectName();
				bean.setNotificationUrl("projectInvolvedTask.do");
			}
			
			bean.setEmployeeId(assignedEmployeeId);
			bean.setSessionParameter("projectId#"+ptBean.getProjectId());
			bean.setNotificationDesc(desc);	
			insertNotification(bean);
		}
		return true;
	}
	
	public boolean createNotificationProposeTaskProject(Integer creatorEmployeeId, Integer assignedEmployeeId,  Integer propTaskId) {
		ProposedTaskManager ptMan = new ProposedTaskManager();
		ProposedTaskBean ptBean = ptMan.getPropTaskByPropTaskId(propTaskId);
		
		EmployeeManager empMan = new EmployeeManager();
		EmployeeBean creatorEmp = empMan.getEmployeeByEmpId(creatorEmployeeId);
		
		if (creatorEmployeeId.intValue() != assignedEmployeeId.intValue()) {
			NotificationBean bean = new NotificationBean();
			String desc="";
			if(ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_PROPOSED)){
	
				desc = creatorEmp.getEmployeeName() + " proposed you a task " + ptBean.getPropTaskName() + " in project " + ptBean.getProjectName();			
				bean.setNotificationUrl("projectTask.do");
				bean.setSessionParameter("projectId#"+ptBean.getProjectId());
			}
			else if (ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_DECLINE)) {
			
				desc = creatorEmp.getEmployeeName() + " declined your propose task : " + ptBean.getPropTaskName() + " in project " + ptBean.getProjectName();
				bean.setNotificationUrl("proposedTask.do");
			}
			else if (ptBean.getPropStatus().equals(Constant.GeneralCode.TASK_STATUS_APPROVE)) {
				
				desc = creatorEmp.getEmployeeName() + " approve your propose task : " + ptBean.getPropTaskName()+ " in project " + ptBean.getProjectName();
				bean.setNotificationUrl("projectInvolvedTask.do");
				bean.setSessionParameter("projectId#"+ptBean.getProjectId());
			}
			bean.setEmployeeId(assignedEmployeeId);
			bean.setNotificationDesc(desc);
			insertNotification(bean);
		}
		return true;
	}
}
