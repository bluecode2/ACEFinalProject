package user;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm{
	private List<UserBean> listOfUser;

	public List<UserBean> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<UserBean> listOfUser) {
		this.listOfUser = listOfUser;
	}
	
	
	
}
