package other;

import independent_task.IndependentTaskBean;

import java.util.List;

import org.apache.struts.action.ActionForm;

import project.ProjectBean;

public class HomeForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String task = "";
	private Integer currentMonth;
	private Integer currentYear;
	private List<IndependentTaskBean> listTaskBean;
	private Float avgTaskProg;
	private List<ProjectBean> listProjBean;
	private Float avgProjProg;
	
	public List<ProjectBean> getListProjBean() {
		return listProjBean;
	}
	public void setListProjBean(List<ProjectBean> listProjBean) {
		this.listProjBean = listProjBean;
	}
	public Float getAvgProjProg() {
		return avgProjProg;
	}
	public void setAvgProjProg(Float avgProjProg) {
		this.avgProjProg = avgProjProg;
	}
	public Float getAvgTaskProg() {
		return avgTaskProg;
	}
	public void setAvgTaskProg(Float avgTaskProg) {
		this.avgTaskProg = avgTaskProg;
	}
	public List<IndependentTaskBean> getListTaskBean() {
		return listTaskBean;
	}
	public void setListTaskBean(List<IndependentTaskBean> listTaskBean) {
		this.listTaskBean = listTaskBean;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(Integer currentMonth) {
		this.currentMonth = currentMonth;
	}
	/**
	 * @return the currentYear
	 */
	public Integer getCurrentYear() {
		return currentYear;
	}
	/**
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}
}
