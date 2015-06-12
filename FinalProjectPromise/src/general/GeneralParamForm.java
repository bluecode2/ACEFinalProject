package general;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class GeneralParamForm extends ActionForm {

	private ArrayList<GeneralParamBean> arrList = new ArrayList<GeneralParamBean>();
	private String searchCategory;
	private String searchItem;
	private Integer page;
	private Integer countPage;
	private String task;
	
	private GeneralParamBean bean = new GeneralParamBean();

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCountPage() {
		return countPage;
	}

	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public GeneralParamBean getBean() {
		return bean;
	}

	public void setBean(GeneralParamBean bean) {
		this.bean = bean;
	}
	
	
}
