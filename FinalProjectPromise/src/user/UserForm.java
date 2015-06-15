package user;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm{
	private List<UserBean> listOfUser;
	private UserBean uBean = new UserBean();
	private String task ="";
	private String searchValue;
	private String searchField;
	private String currSearchValue = "";
	private String currSearchField = "";
	private Integer selectedId = 0;
	private Integer currPage = 1;
	private Integer pageCount = 1;
	
	public UserBean getuBean() {
		return uBean;
	}

	public void setuBean(UserBean uBean) {
		this.uBean = uBean;
	}

	public List<UserBean> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<UserBean> listOfUser) {
		this.listOfUser = listOfUser;
	}
	
	
	
}
