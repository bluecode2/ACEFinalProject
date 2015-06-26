package notification;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class NotificationForm extends ActionForm {
	private String task;
	private Integer selectedId;
	private NotificationBean bean;
	private List<NotificationBean> lstBean = new ArrayList<NotificationBean>();
	private Integer currPage = 1;
	private Integer pageCount = 1;
	
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
	public List<NotificationBean> getLstBean() {
		return lstBean;
	}
	public void setLstBean(List<NotificationBean> lstBean) {
		this.lstBean = lstBean;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
}
