package employee;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class EmployeeForm extends ActionForm{

	private List<EmployeeBean> listOfEmployee;

	public List<EmployeeBean> getListOfEmployee() {
		return listOfEmployee;
	}

	public void setListOfEmployee(List<EmployeeBean> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
	}
}
