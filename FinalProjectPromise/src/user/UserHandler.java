package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class UserHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
//		return super.execute(mapping, form, request, response);
		UserForm uForm = (UserForm) form;
		UserManager uMan = new UserManager();
		
		CommonFunction.createAllowedMenu(null, request);
		
		if ("add".equalsIgnoreCase(uForm.getTask())){
			uForm.setIsAdd(true);
			uForm.setSelectedId(0);
			request.setAttribute("pageTitle", "User Entry");
			return mapping.findForward("entry");
		}
		else if ("Edit".equalsIgnoreCase(uForm.getTask())){
			
		}
		else if ("Delete".equalsIgnoreCase(uForm.getTask())){
			
		}
		
		uForm.setTask("");
		uForm.setSearchField(uForm.getCurrSearchField());
		uForm.setSearchValue(uForm.getCurrSearchValue());

		int rowCount;
		
		uForm.setListOfUser(uMan.getAllUser(
				uForm.getCurrSearchField(), uForm.getCurrSearchValue(),
				uForm.getCurrPage(), Constant.pageSize));
		rowCount = uMan.getCountUser(uForm.getCurrSearchField(),
				uForm.getCurrSearchValue());
		//
		uForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Department List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(uForm.getPageCount(),
						uForm.getCurrPage()));

		request.setAttribute("pageCount", uForm.getPageCount());
		request.setAttribute("currPage", uForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("userList");
	}
}
