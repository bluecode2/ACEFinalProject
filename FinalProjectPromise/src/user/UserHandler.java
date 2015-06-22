package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;
import employee.EmployeeForm;
import employee.EmployeeManager;
import general.GeneralParamBean;
import general.GeneralParamManager;

public class UserHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
//		return super.execute(mapping, form, request, response);
		UserForm uForm = (UserForm) form;
		UserManager uMan = new UserManager();
		
		UserRoleForm uRoleForm = new UserRoleForm();
		UserRoleManager uRoleMan = new UserRoleManager();
		
		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();
		
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		//CommonFunction.createAllowedMenu(us, request);
		
		if ("add".equalsIgnoreCase(uForm.getTask())){
			
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY,
					us, request);
			
			request.setAttribute("show", false);
			uForm.setIsAdd(true);
			uForm.setSelectedId(0);
			uForm.setVal("0");
			request.setAttribute("lstUserRole", uRoleMan.getUserRole(
					uRoleForm.getCurrSearchField(), uRoleForm.getCurrSearchValue(),
					uRoleForm.getCurrPage(), Constant.pageSize));
			request.setAttribute("lstEmployeeId", eMan.getAllEmployee(
					eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
					eForm.getCurrPage(), Constant.pageSize));
			return mapping.findForward("userAdd");
		}
		else if ("Edit".equalsIgnoreCase(uForm.getTask())){

			CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY,
					us, request);
			
			request.setAttribute("show", true);
			uForm.setIsAdd(false);
			uForm.setuBean(uMan.getUserByUserID(uForm
					.getSelectedId()));
			uForm.setPasswordUser(uForm.getuBean().getPasswordUser());
			uForm.setVal("0");
			request.setAttribute("lstUserRole", uRoleMan.getUserRole(
					uRoleForm.getCurrSearchField(), uRoleForm.getCurrSearchValue(),
					uRoleForm.getCurrPage(), Constant.pageSize));
			request.setAttribute("lstEmployeeId", eMan.getAllEmployee(
					eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
					eForm.getCurrPage(), Constant.pageSize));
			return mapping.findForward("userAdd");
		}
		else if ("delete".equalsIgnoreCase(uForm.getTask())){
			uForm.getuBean().setUpdatedBy(us.getUserId());
			uMan.delUsers(uForm.getSelectedId());
		}

		else if ("save".equalsIgnoreCase(uForm.getTask())){
			Boolean isAdd = uForm.getIsAdd();
			if (isAdd) {
				uForm.getuBean().setCreatedBy(us.getUserId());
				uForm.getuBean().setPasswordUser(uForm.getPasswordUser());
				uMan.insertUser(uForm.getuBean());
			} else {
				uForm.setVal("0");
				uForm.getuBean().setUpdatedBy(us.getUserId());
				if (uMan.getLoginValidasi(uForm.getuBean().getUsername(), uForm.getOldPassword()) != null){
					uForm.getuBean().setPasswordUser(uForm.getPasswordUser());
					uMan.updateUser(uForm.getuBean());
				}
				else {
					uForm.setVal("1");
					request.setAttribute("pageTitle", "User Edit");
					uForm.setTask("edit");
					return mapping.findForward("userAdd");
				}
			}

			response.sendRedirect("users.do");
			return null;
		}
		else if ("resetPass".equalsIgnoreCase(uForm.getTask())){
			uForm.setVal("0");
			uForm.getuBean().setUpdatedBy(us.getUserId());
			GeneralParamManager gParamMan = new GeneralParamManager();
			GeneralParamBean gParamBean = new GeneralParamBean();
			gParamBean = gParamMan.getGenParamByParamId("password");
			uForm.getuBean().setPasswordUser(gParamBean.getGenParamValue());
			uMan.updateUser(uForm.getuBean());
			response.sendRedirect("users.do");
			return null;
		}
		
		uForm.setTask("");
		uForm.setSearchField(uForm.getCurrSearchField());
		uForm.setSearchValue(uForm.getCurrSearchValue());

		int rowCount;
		

		CommonFunction.initializeHeader(Constant.MenuCode.USER,
				us, request);
		
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
		uForm.setVal("0");
		return mapping.findForward("userList");
	}
}
