package general;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

public class GeneralParamForm extends ActionForm {

	private ArrayList<GeneralParamBean> arrList = new ArrayList<GeneralParamBean>();

	public ArrayList<GeneralParamBean> getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList<GeneralParamBean> arrList) {
		this.arrList = arrList;
	}
}
