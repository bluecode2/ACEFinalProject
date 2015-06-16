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
			return mapping.findForward("userAdd");
		}
		else if ("Edit".equalsIgnoreCase(uForm.getTask())){
//			uForm.setIsAdd(true);
//			uForm.setSelectedId(0);
//			request.setAttribute("pageTitle", "User Edit");
//			
//			uForm.setuBean(uMan.getUserByUserID(uForm
//					.getSelectedId()));
//			
//			return mapping.findForward("userAdd");
		}
		else if ("Delete".equalsIgnoreCase(uForm.getTask())){
			
		}
	/*	else if ("save".equalsIgnoreCase(uForm.getTask())){
			Boolean isAdd = uForm.getIsAdd();

			if (uForm.getuBean().getUserId() == 0)
				uForm.getuBean().setUserId(null);

			if (isAdd) {
				uForm.getuBean().setCreateBy(1);
//				dMan.insertDepartment(dForm.getSelectedDept());
			} else {
				uForm.getuBean().setUpdateBy(1);
//				dMan.updateDepartment(dForm.getSelectedDept());
			}

			response.sendRedirect("users.do");
			return null;
		}*/
		
		uForm.setTask("");
		uForm.setSearchField(uForm.getCurrSearchField());
		uForm.setSearchValue(uForm.getCurrSearchValue());

		int rowCount;
		
		
		rowCount = uMan.getCountUser(uForm.getCurrSearchField(),
				uForm.getCurrSearchValue());
		System.out.println("rowCount selesai");
		uForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));
System.out.println("pageCount end");
		uForm.setListOfUser(uMan.getAllUser(
				uForm.getCurrSearchField(), uForm.getCurrSearchValue(),
				uForm.getCurrPage(), Constant.pageSize));
System.out.println("isi list selesai");
		request.setAttribute("pageTitle", "User List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(uForm.getPageCount(),
						uForm.getCurrPage()));

		request.setAttribute("pageCount", uForm.getPageCount());
		request.setAttribute("currPage", uForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);
		
		return mapping.findForward("userList");
	}
}
