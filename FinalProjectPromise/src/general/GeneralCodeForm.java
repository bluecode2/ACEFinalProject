package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralCodeForm extends ActionForm {

	private ArrayList<GeneralCodeBean> arrCodeBean = new ArrayList<GeneralCodeBean>();

	public ArrayList<GeneralCodeBean> getArrCodeBean() {
		return arrCodeBean;
	}

	public void setArrCodeBean(ArrayList<GeneralCodeBean> arrCodeBean) {
		this.arrCodeBean = arrCodeBean;
	}

}
