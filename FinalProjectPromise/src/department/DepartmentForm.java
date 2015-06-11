package department;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class DepartmentForm extends ActionForm{
	private ArrayList<DepartmentBean> arrList = new ArrayList<DepartmentBean>();

	public ArrayList<DepartmentBean> getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList<DepartmentBean> arrList) {
		this.arrList = arrList;
	}
}
