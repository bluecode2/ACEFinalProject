package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralParamForm extends ActionForm {

	private ArrayList<GeneralParamBean> arrList = new ArrayList<GeneralParamBean>();
	private String searchCategory;
	private String searchItem;

	public ArrayList<GeneralParamBean> getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList<GeneralParamBean> arrList) {
		this.arrList = arrList;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}
}
