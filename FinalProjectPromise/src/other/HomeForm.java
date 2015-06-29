package other;

import independent_task.IndependentTaskBean;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class HomeForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private List<IndependentTaskBean> listTaskBean;
	private Float avgTaskProg;
	
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
	

}
