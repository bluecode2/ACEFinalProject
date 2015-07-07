package user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import active_directory.ActiveDirectoryManager;
import common.CommonFunction;
import common.Constant;
import employee.EmployeeForm;
import employee.EmployeeManager;
import general.GeneralParamBean;
import general.GeneralParamManager;

public class UserHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		UserForm uForm = (UserForm) form;
		UserManager uMan = new UserManager();

		UserRoleForm uRoleForm = new UserRoleForm();
		UserRoleManager uRoleMan = new UserRoleManager();

		EmployeeForm eForm = new EmployeeForm();
		EmployeeManager eMan = new EmployeeManager();

		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");

		if ("add".equalsIgnoreCase(uForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY, us,
					request);
			request.setAttribute("pageTitle", "User Entry");
			request.setAttribute("show", false);
			uForm.setIsAdd(true);
			uForm.setSelectedId(0);
			uForm.setVal("0");
			request.setAttribute("lstUserRole", uRoleMan.getUserRoleForPopUp());
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPopUp());
			return mapping.findForward("userAdd");
		} else if ("Edit".equalsIgnoreCase(uForm.getTask())) {

			CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY, us,
					request);
			request.setAttribute("pageTitle", "User Edit");
			uForm.setIsAdd(false);
			uForm.setuBean(uMan.getUserByUserID(uForm.getSelectedId()));
			System.out.println(uForm.getuBean().getIsActiveDirectory());
			uForm.setPasswordUser(uForm.getuBean().getPasswordUser());
			uForm.setVal("0");
			request.setAttribute("lstUserRole", uRoleMan.getUserRoleForPopUp());
			request.setAttribute("lstEmployeeId", eMan.getAllEmployeeForPopUp());
			return mapping.findForward("userAdd");
		} else if ("delete".equalsIgnoreCase(uForm.getTask())) {
			uMan.delUsers(uForm.getSelectedId(),us.getUserId());
		}
		
		else if ("save".equalsIgnoreCase(uForm.getTask())) {
			Boolean isAdd = uForm.getIsAdd();
			if (isAdd) {
				ActiveDirectoryManager adMan = new ActiveDirectoryManager();
				GeneralParamManager genParMan = new GeneralParamManager();
				GeneralParamBean userBean = genParMan.getGenParamByParamId(Constant.GeneralParameter.AD_ADMIN_USERNAME);
				GeneralParamBean passBean = genParMan.getGenParamByParamId(Constant.GeneralParameter.AD_ADMIN_PASSWORD);
				
				if(adMan.checkValidUser(uForm.getuBean().getUsername(), userBean.getGenParamValue(), passBean.getGenParamValue())){
					uForm.getuBean().setIsActiveDirectory(1);
				}
				else
					uForm.getuBean().setIsActiveDirectory(0);
				uForm.getuBean().setCreatedBy(us.getUserId());
				uMan.insertUser(uForm.getuBean());
			} else {
				uForm.setVal("0");
				uForm.getuBean().setUpdatedBy(us.getUserId());
				uMan.updateUser(uForm.getuBean());
			}

			response.sendRedirect("users.do");
			return null;
		} else if ("resetPass".equalsIgnoreCase(uForm.getTask())) {
			uForm.setVal("0");
			uForm.getuBean().setUpdatedBy(us.getUserId());
			GeneralParamManager gParamMan = new GeneralParamManager();
			GeneralParamBean gParamBean = new GeneralParamBean();
			gParamBean = gParamMan.getGenParamByParamId("password");
			uForm.getuBean().setPasswordUser(gParamBean.getGenParamValue());
			uForm.getuBean().setUpdatedBy(us.getUserId());
			uMan.changePassword(uForm.getuBean());
			response.sendRedirect("users.do");
			return null;
		}
		else if ("changePassword".equalsIgnoreCase(uForm.getTask())) {
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY, us,
					request);
			uForm.setuBean(us);
			request.setAttribute("changePassword", true);
			return mapping.findForward("userAdd");
		}
		else if("saveChangePassword".equalsIgnoreCase(uForm.getTask())) {
			if (uMan.getLoginValidasi(us.getUsername(), uForm.getOldPassword()) != null){
				us.setPasswordUser(uForm.getPasswordUser());
				uForm.getuBean().setUpdatedBy(us.getUserId());
				uMan.changePassword(us);
				response.sendRedirect("home.do");
				return null;
			}
			else {
				CommonFunction.initializeHeader(Constant.MenuCode.USER_ENTRY, us,
						request);
				uForm.setVal("1");
				uForm.setTask("changePassword");
				uForm.setuBean(us);
				request.setAttribute("changePassword", true);
				return mapping.findForward("userAdd");
			}
		}
		else if("chkActiveDirectory".equalsIgnoreCase(uForm.getTask())){
			ActiveDirectoryManager adMan = new ActiveDirectoryManager();
			GeneralParamManager genParMan = new GeneralParamManager();
			GeneralParamBean userBean = genParMan.getGenParamByParamId(Constant.GeneralParameter.AD_ADMIN_USERNAME);
			GeneralParamBean passBean = genParMan.getGenParamByParamId(Constant.GeneralParameter.AD_ADMIN_PASSWORD);
			
			PrintWriter out = response.getWriter();
			if(adMan.checkValidUser(uForm.getUsername(), userBean.getGenParamValue(), passBean.getGenParamValue())){
				out.print("1");
			}
			else
				out.print("0");
			return null;
		}
		
		request.setAttribute("pageTitle", "User");
		uForm.setTask("");
		uForm.setSearchField(uForm.getCurrSearchField());
		uForm.setSearchValue(uForm.getCurrSearchValue());

		int rowCount;

		CommonFunction.initializeHeader(Constant.MenuCode.USER, us, request);

		rowCount = uMan.getCountUser(uForm.getCurrSearchField(),
				uForm.getCurrSearchValue());
		
		uForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		uForm.setListOfUser(uMan.getAllUser(uForm.getCurrSearchField(),
				uForm.getCurrSearchValue(), uForm.getCurrPage(),
				Constant.PAGE_SIZE));
	

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
