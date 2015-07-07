package user;

import general.MenuManager;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import reports.ReportManager;
import reports.ReportRoleBean;
import reports.ReportRoleManager;
import user_access.UserRoleMenuBean;
import user_access.UserRoleMenuManager;
import common.CommonFunction;
import common.Constant;
import department.DepartmentManager;

public class UserRoleHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		UserRoleForm userRoleForm = (UserRoleForm) form;
		UserRoleManager userRoleManager = new UserRoleManager();
		MenuManager menuMan = new MenuManager();
		ReportManager rpMan = new ReportManager();
		DepartmentManager deptMan = new DepartmentManager();
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");

		// CommonFunction.createAllowedMenu(us, request);

		if ("add".equals(userRoleForm.getTask())) {
			userRoleForm.setIsAdd(true);
			request.setAttribute("pageTitle", "User Role Entry");
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE_ENTRY,
					us, request);

			return mapping.findForward("userRoleEntry");
		} else if ("save".equals(userRoleForm.getTask())) {
			Boolean isAdd = userRoleForm.getIsAdd();

			if (isAdd) {
				userRoleForm.getUserRoleBean().setUserRoleId(
						userRoleManager.getUserRoleId());
				userRoleForm.getUserRoleBean().setCreatedBy(us.getUserId());
				userRoleManager.insertUserRole(userRoleForm.getUserRoleBean());
			} else {
				userRoleForm.getUserRoleBean().setUpdatedBy(us.getUserId());
				userRoleManager.editUserRole(userRoleForm.getUserRoleBean());
			}

			response.sendRedirect("userRole.do");
			return null;
		} else if ("edit".equals(userRoleForm.getTask())) {
			request.setAttribute("pageTitle", "User Role Edit");
			CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE_ENTRY,
					us, request);
			userRoleForm.setUserRoleBean(userRoleManager
					.getUserRoleEdit(userRoleForm.getSelectedId()));

			return mapping.findForward("userRoleEntry");
		} else if ("delete".equals(userRoleForm.getTask())) {
			userRoleForm.getUserRoleBean().setUpdatedBy(us.getUserId());
			userRoleManager.deleteUserRole(userRoleForm.getSelectedId());
		} else if ("openMenuAccess".equals(userRoleForm.getTask())) {
			
			StringBuilder sbListMenuId = new StringBuilder();
			StringBuilder sbListAllowAdd = new StringBuilder();
			StringBuilder sbListAllowBack = new StringBuilder();
			StringBuilder sbListAllowSave = new StringBuilder();
			StringBuilder sbListAllowApprove = new StringBuilder();
			StringBuilder sbListAllowDecline = new StringBuilder();			

			Integer userRoleID = userRoleForm.getSelectedId();

			UserRoleMenuManager manager = new UserRoleMenuManager();
			List<UserRoleMenuBean> arrRoleMenu = manager
					.getUserRoleMenuByUserRole(userRoleID);

			for (UserRoleMenuBean bean : arrRoleMenu) {
				if (!sbListMenuId.toString().equals("")) {
					sbListMenuId.append("#");
					sbListAllowAdd.append("#");
					sbListAllowBack.append("#");
					sbListAllowSave.append("#");
					sbListAllowApprove.append("#");
					sbListAllowDecline.append("#");
				}
				
				sbListMenuId.append(bean.getMenuId().toString());
				sbListAllowAdd.append(bean.getMenuCrud().contains("C"));
				sbListAllowBack.append(bean.getMenuCrud().contains("B"));
				sbListAllowSave.append(bean.getMenuCrud().contains("U"));
				sbListAllowApprove.append(bean.getMenuCrud().contains("A"));
				sbListAllowDecline.append(bean.getMenuCrud().contains("D"));
			}

			String resp = sbListMenuId.toString() + "$" + sbListAllowAdd.toString() + "$" + sbListAllowBack.toString()
					+ "$" + sbListAllowSave.toString() + "$" + sbListAllowApprove.toString() + "$"
					+ sbListAllowDecline.toString();
			PrintWriter out = response.getWriter();
			out.println(resp);

			return null;
		} else if ("saveMenuAccess".equals(userRoleForm.getTask())) {
			String[] listMenuId = userRoleForm.getListMenuId().split("#");
			String[] listAllowAdd = userRoleForm.getListAllowAdd().split("#");
			String[] listAllowBack = userRoleForm.getListAllowBack().split("#");
			String[] listAllowSave = userRoleForm.getListAllowSave().split("#");
			String[] listAllowApprove = userRoleForm.getListAllowApprove()
					.split("#");
			String[] listAllowDecline = userRoleForm.getListAllowDecline()
					.split("#");

			UserRoleMenuManager manager = new UserRoleMenuManager();
			Integer userRoleId = userRoleForm.getSelectedId();
			List<UserRoleMenuBean> arrRoleMenu = manager
					.getUserRoleMenuByUserRole(userRoleId);
			List<UserRoleMenuBean> arrDeletedRoleMenu = new ArrayList<UserRoleMenuBean>();

			for (UserRoleMenuBean bean : arrRoleMenu) {
				if (!Arrays.asList(listMenuId).contains(
						bean.getMenuId().toString())) {
					arrDeletedRoleMenu.add(bean);
				}
			}

			for (int i = 0; i < listMenuId.length; i++) {
				if (!listMenuId[i].equals("")) {
					StringBuilder sbMenuCrud = new StringBuilder();
					if (listAllowAdd[i].equals("true"))
						sbMenuCrud.append("C");
					if (listAllowBack[i].equals("true"))
						sbMenuCrud.append("B");
					if (listAllowSave[i].equals("true"))
						sbMenuCrud.append("U");
					if (listAllowApprove[i].equals("true"))
						sbMenuCrud.append("A");
					if (listAllowDecline[i].equals("true"))
						sbMenuCrud.append("D");

					Integer menuId = Integer.valueOf(listMenuId[i]);

					UserRoleMenuBean bean = manager.getUserRoleMenu(userRoleId,
							menuId);

					if (bean == null) {
						bean = new UserRoleMenuBean();
						bean.setMenuId(menuId);
						bean.setUserRoleId(userRoleId);
						bean.setMenuCrud(sbMenuCrud.toString());

						manager.insertUserRoleMenu(bean);
					} else {
						bean.setMenuCrud(sbMenuCrud.toString());
						manager.editUserRoleMenu(bean);
					}
				}
			}

			for (UserRoleMenuBean bean : arrDeletedRoleMenu) {
				manager.deleteUserRoleMenu(bean.getUserRoleId(),
						bean.getMenuId());
			}

			return null;
		}

		else if ("openReportAccess".equals(userRoleForm.getTask())) {
			StringBuilder sbListReportId = new StringBuilder();
			Integer userRoleID = userRoleForm.getSelectedId();

			ReportRoleManager rrMan = new ReportRoleManager();

			List<ReportRoleBean> arrList = rrMan
					.getReportRoleByRoleId(userRoleID);

			for (ReportRoleBean rrBean : arrList) {
				if (!sbListReportId.toString().equals("")) {
					sbListReportId.append("#");
				}
				sbListReportId.append(rrBean.getReportId().toString());

			}
			PrintWriter out = response.getWriter();
			String resp = sbListReportId.toString();
			out.println(resp);

			return null;
		} else if ("saveReportAccess".equals(userRoleForm.getTask())) {

			String[] listReportId = userRoleForm.getListReportId().split("#");

			ReportRoleManager rrMan = new ReportRoleManager();
			Integer userRoleId = userRoleForm.getSelectedId();
			List<ReportRoleBean> arrReportMenu = rrMan
					.getReportRoleByRoleId(userRoleId);
			List<ReportRoleBean> arrDeletedRoleMenu = new ArrayList<ReportRoleBean>();

			for (ReportRoleBean rrBean : arrReportMenu) {
				if (!Arrays.asList(listReportId).contains(
						rrBean.getReportId().toString())) {
					arrDeletedRoleMenu.add(rrBean);
				}
			}

			for (int i = 0; i < listReportId.length; i++) {
				if (!listReportId[i].equals("")) {
					Integer reportId = Integer.valueOf(listReportId[i]);
					ReportRoleBean bean = rrMan.getReportRoleBean(userRoleId,
							reportId);

					if (bean == null) {
						bean = new ReportRoleBean();
						bean.setReportId(reportId);
						bean.setUserRoleId(userRoleId);
						rrMan.insertUserRoleReport(bean);
					}
				}
			}
			for (ReportRoleBean reportRoleBean : arrDeletedRoleMenu) {
				rrMan.deleteUserRoleReport(reportRoleBean);
			}
			return null;
		}
/*		else if ("openReportAccess".equals(userRoleForm.getTask())) {
			String listReportId = "";
			Integer userRoleID = userRoleForm.getSelectedId();

			ReportRoleManager rrMan = new ReportRoleManager();

			List<ReportRoleBean> arrList = rrMan
					.getReportRoleByRoleId(userRoleID);

			for (ReportRoleBean rrBean : arrList) {
				if (!listReportId.equals("")) {
					listReportId += "#";
				}
				listReportId += rrBean.getReportId().toString();
			}
			PrintWriter out = response.getWriter();
			String resp = listReportId;
			out.println(resp);

			return null;
		} else if ("saveReportAccess".equals(userRoleForm.getTask())) {

			String[] listReportId = userRoleForm.getListReportId().split("#");

			ReportRoleManager rrMan = new ReportRoleManager();
			Integer userRoleId = userRoleForm.getSelectedId();
			List<ReportRoleBean> arrReportMenu = rrMan
					.getReportRoleByRoleId(userRoleId);
			List<ReportRoleBean> arrDeletedRoleMenu = new ArrayList<ReportRoleBean>();

			for (ReportRoleBean rrBean : arrReportMenu) {
				if (!Arrays.asList(listReportId).contains(
						rrBean.getReportId().toString())) {
					arrDeletedRoleMenu.add(rrBean);
				}
			}

			for (int i = 0; i < listReportId.length; i++) {
				if (!listReportId[i].equals("")) {
					Integer reportId = Integer.valueOf(listReportId[i]);
					ReportRoleBean bean = rrMan.getReportRoleBean(userRoleId,
							reportId);

					if (bean == null) {
						bean = new ReportRoleBean();
						bean.setReportId(reportId);
						bean.setUserRoleId(userRoleId);
						rrMan.insertUserRoleReport(bean);
					}
				}
			}
			for (ReportRoleBean reportRoleBean : arrDeletedRoleMenu) {
				rrMan.deleteUserRoleReport(reportRoleBean);
			}
			return null;
		} */
		else if ("openDepartmentAccess".equals(userRoleForm.getTask())) {
			StringBuilder sbListDeptId = new StringBuilder();

			Integer userRoleID = userRoleForm.getSelectedId();

			UserRoleDepartmentManager rrMan = new UserRoleDepartmentManager();

			List<UserRoleDepartmentBean> arrList = rrMan
					.getUserRoleDepartmentByUserRole(userRoleID);

			for (UserRoleDepartmentBean rrBean : arrList) {
				if (!sbListDeptId.toString().equals("")) {
					sbListDeptId.append("#");

				}
				sbListDeptId.append(rrBean.getDeptId().toString());
			}
			PrintWriter out = response.getWriter();
			String resp = sbListDeptId.toString();
			out.println(resp);

			return null;
		} else if ("saveDepartmentAccess".equals(userRoleForm.getTask())) {

			String[] listDeptId = userRoleForm.getListDeptId().split("#");

			UserRoleDepartmentManager rrMan = new UserRoleDepartmentManager();
			Integer userRoleId = userRoleForm.getSelectedId();
			List<UserRoleDepartmentBean> arrDeptAccess = rrMan
					.getUserRoleDepartmentByUserRole(userRoleId);
			List<UserRoleDepartmentBean> arrDeletedDeptAccess = new ArrayList<UserRoleDepartmentBean>();

			for (UserRoleDepartmentBean rrBean : arrDeptAccess) {
				if (!Arrays.asList(listDeptId).contains(
						rrBean.getDeptId().toString())) {
					arrDeletedDeptAccess.add(rrBean);
				}
			}

			for (int i = 0; i < listDeptId.length; i++) {
				Integer deptId = Integer.valueOf(listDeptId[i]);
				UserRoleDepartmentBean bean = rrMan.getUserRoleDepartment(
						userRoleId, deptId);

				if (bean == null) {
					bean = new UserRoleDepartmentBean();
					bean.setDeptId(deptId);
					bean.setUserRoleId(userRoleId);
					rrMan.insertUserRoleDepartment(bean);
				}
			}
			for (UserRoleDepartmentBean reportRoleBean : arrDeletedDeptAccess) {
				rrMan.deleteUserRoleDepartment(reportRoleBean);
			}
			return null;
		}

		request.setAttribute("pageTitle", "User Role");
		userRoleForm.setTask("");
		userRoleForm.setSearchField(userRoleForm.getCurrSearchField());
		userRoleForm.setSearchValue(userRoleForm.getCurrSearchValue());

		int rowCount;
		userRoleForm.setArrList(userRoleManager.getUserRole(
				userRoleForm.getCurrSearchField(),
				userRoleForm.getCurrSearchValue(), userRoleForm.getCurrPage(),
				Constant.PAGE_SIZE));

		rowCount = userRoleManager.getCountUserRole(
				userRoleForm.getCurrSearchField(),
				userRoleForm.getCurrSearchValue());

		userRoleForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		CommonFunction.initializeHeader(Constant.MenuCode.USER_ROLE, us,
				request);
		request.setAttribute("lstReport", rpMan.getListReports());
		request.setAttribute("lstMenu", menuMan.getAllMenu());
		request.setAttribute("lstDepartment",
				deptMan.getListDepartmentForSearchDialog("", ""));

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(userRoleForm.getPageCount(),
						userRoleForm.getCurrPage()));

		request.setAttribute("pageCount", userRoleForm.getPageCount());
		request.setAttribute("currPage", userRoleForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("userRoleList");
	}
}
