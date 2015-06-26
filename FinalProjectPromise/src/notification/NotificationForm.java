package notification;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class NotificationForm extends ActionForm {
	private String task;
	private Integer selectedId;
	private NotificationBean bean;
	private ArrayList<NotificationBean> lstBean = new ArrayList<NotificationBean>();
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
	public NotificationBean getBean() {
		return bean;
	}
	public void setBean(NotificationBean bean) {
		this.bean = bean;
	}
	public ArrayList<NotificationBean> getLstBean() {
		return lstBean;
	}
	public void setLstBean(ArrayList<NotificationBean> lstBean) {
		this.lstBean = lstBean;
	}
	
	
}
