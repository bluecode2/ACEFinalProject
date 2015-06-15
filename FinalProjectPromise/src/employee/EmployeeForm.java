package employee;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class EmployeeForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EmployeeBean> listOfEmployee;
	private String task;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<EmployeeBean> getListOfEmployee() {
		return listOfEmployee;
	}

	public void setListOfEmployee(List<EmployeeBean> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
	}
}
