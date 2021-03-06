package common;

import general.GeneralParamBean;
import general.GeneralParamManager;
import general.MenuBean;
import general.MenuManager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import notification.NotificationBean;
import notification.NotificationManager;
import user.UserBean;
import user_access.UserRoleMenuBean;
import user_access.UserRoleMenuManager;

public class CommonFunction {
	public static ArrayList<PageNavigator> createPagingNavigatorList(
			int pageCount, int currPage) {
		ArrayList<PageNavigator> arr = new ArrayList<PageNavigator>();
		PageNavigator page;
		if (currPage > 1) {
			page = new PageNavigator();
			page.setValue(1);
			page.setLabel("<<");
			page.setTitle("First Page");
			arr.add(page);

			page = new PageNavigator();
			page.setValue(currPage - 1);
			page.setLabel("<");
			page.setTitle("Previous Page");
			arr.add(page);
		}

		int maxShownPageCount = Constant.PAGE_NAVIGATION_SIZE;

		int frontCount = (Constant.PAGE_NAVIGATION_SIZE / 2);
		if (pageCount - currPage < Constant.PAGE_NAVIGATION_SIZE / 2) {
			frontCount += (currPage - pageCount + frontCount);
		}

		for (int i = currPage - frontCount; i < currPage; i++) {
			if (i > 0) {
				page = new PageNavigator();
				page.setValue(i);
				page.setLabel(String.valueOf(i));
				page.setTitle("Page" + String.valueOf(i));
				arr.add(page);
				maxShownPageCount--;
			}
		}

		page = new PageNavigator();
		page.setValue(currPage);
		page.setLabel(String.valueOf(currPage));
		page.setTitle("");
		arr.add(page);
		maxShownPageCount--;

		for (int i = currPage + 1; i <= pageCount && maxShownPageCount > 0; i++) {
			page = new PageNavigator();
			page.setValue(i);
			page.setLabel(String.valueOf(i));
			page.setTitle("Page " + String.valueOf(i));
			arr.add(page);
			maxShownPageCount--;
		}

		if (currPage < pageCount) {
			page = new PageNavigator();
			page.setValue(currPage + 1);
			page.setLabel(">");
			page.setTitle("Next Page");
			arr.add(page);

			page = new PageNavigator();
			page.setValue(pageCount);
			page.setLabel(">>");
			page.setTitle("Last Page");
			arr.add(page);

		}
		return arr;
	}

	public static void createAllowedMenu(UserBean user,
			HttpServletRequest request) {
		ArrayList<MenuBean> arrMenuLvl1 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl2 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl3 = new ArrayList<MenuBean>();

		MenuManager menuMan = new MenuManager();

		arrMenuLvl1 = menuMan.getAllMenuHead(user.getUserRoleId());

		for (MenuBean menuLvl1 : arrMenuLvl1) {
			ArrayList<MenuBean> tmpLvl2 = menuMan.getAllMenuByParent(
					menuLvl1.getMenuId(), user.getUserRoleId());

			for (MenuBean menuLvl2 : tmpLvl2) {
				arrMenuLvl2.add(menuLvl2);
				ArrayList<MenuBean> tmpLvl3 = menuMan.getAllMenuByParent(
						menuLvl2.getMenuId(), user.getUserRoleId());

				for (MenuBean menuLvl3 : tmpLvl3) {
					arrMenuLvl3.add(menuLvl3);
				}
			}
		}

		request.setAttribute("reportMenuCode", Constant.MenuCode.REPORTS);

		request.setAttribute("arrMenuLvl1", arrMenuLvl1);
		request.setAttribute("arrMenuLvl2", arrMenuLvl2);
		request.setAttribute("arrMenuLvl3", arrMenuLvl3);

		request.setAttribute("userName", user.getEmployeeName());
		request.setAttribute("isActiveDirectory", user.getIsActiveDirectory());
		request.setAttribute("userRoleName", user.getUserRoleName());

		// Generate Unread Notification
		NotificationManager nMan = new NotificationManager();
		List<NotificationBean> lstUnreadNotification = nMan
				.getListUnreadNotificationByEmployee(user.getEmployeeId());
		int unreadCount = nMan.getCountNotificationByEmployee(user
				.getEmployeeId(),false);
		request.setAttribute("unreadNotification", lstUnreadNotification);
		request.setAttribute("unreadCount", unreadCount);
	}

	public static void initializeHeader(String menuCode, UserBean user,
			HttpServletRequest request) {
		ArrayList<MenuBean> arrMenuLvl1 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl2 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl3 = new ArrayList<MenuBean>();

		MenuManager menuMan = new MenuManager();
		MenuBean menu = menuMan.getMenuByMenuCode(menuCode);
		UserRoleMenuManager roleMenuManager = new UserRoleMenuManager();
		UserRoleMenuBean roleMenuBean = roleMenuManager.getUserRoleMenu(
				user.getUserRoleId(), menu.getMenuId());

		arrMenuLvl1 = menuMan.getAllMenuHead(user.getUserRoleId());

		for (MenuBean menuLvl1 : arrMenuLvl1) {
			ArrayList<MenuBean> tmpLvl2 = menuMan.getAllMenuByParent(
					menuLvl1.getMenuId(), user.getUserRoleId());

			for (MenuBean menuLvl2 : tmpLvl2) {
				arrMenuLvl2.add(menuLvl2);
				ArrayList<MenuBean> tmpLvl3 = menuMan.getAllMenuByParent(
						menuLvl2.getMenuId(), user.getUserRoleId());

				for (MenuBean menuLvl3 : tmpLvl3) {
					arrMenuLvl3.add(menuLvl3);
				}
			}
		}

		request.setAttribute("reportMenuCode", Constant.MenuCode.REPORTS);

		request.setAttribute("arrMenuLvl1", arrMenuLvl1);
		request.setAttribute("arrMenuLvl2", arrMenuLvl2);
		request.setAttribute("arrMenuLvl3", arrMenuLvl3);

		request.setAttribute("userName", user.getEmployeeName());
		request.setAttribute("isActiveDirectory", user.getIsActiveDirectory());
		request.setAttribute("userRoleName", user.getUserRoleName());

		request.setAttribute("pageTitle", menu.getMenuCaption());

		request.setAttribute("btnAddVisible", roleMenuBean.getMenuCrud()
				.contains("C"));
		request.setAttribute("btnBackVisible", roleMenuBean.getMenuCrud()
				.contains("B"));
		request.setAttribute("btnSaveVisible", roleMenuBean.getMenuCrud()
				.contains("U"));
		request.setAttribute("btnApproveVisible", roleMenuBean.getMenuCrud()
				.contains("A"));
		request.setAttribute("btnDeclineVisible", roleMenuBean.getMenuCrud()
				.contains("D"));

		// Generate Unread Notification
		NotificationManager nMan = new NotificationManager();
		List<NotificationBean> lstUnreadNotification = nMan
				.getListUnreadNotificationByEmployee(user.getEmployeeId());
		int unreadCount = nMan.getCountNotificationByEmployee(user
				.getEmployeeId(),false);
		request.setAttribute("unreadNotification", lstUnreadNotification);
		request.setAttribute("unreadCount", unreadCount);

		// Generate BreadCrumb
		ArrayList<MenuBean> arrBreadCrumb = new ArrayList<MenuBean>();
		arrBreadCrumb.add(menu);
		generateBreadCrumb(menu.getParentId(), arrBreadCrumb);

		request.setAttribute("breadCrumb", arrBreadCrumb);
		request.setAttribute("currMenuId", menu.getMenuId());
	}

	private static void generateBreadCrumb(Integer parentId,
			ArrayList<MenuBean> arrBreadCrumb) {
		if (parentId == null)
			return;
		MenuManager menuMan = new MenuManager();
		MenuBean parent = menuMan.getMenuByMenuId(parentId);
		if (parent != null) {
			arrBreadCrumb.add(0, parent);
			generateBreadCrumb(parent.getParentId(), arrBreadCrumb);
		} else
			return;
	}

	public static String getGeneralParameterValue(String genParamId) {
		GeneralParamManager manager = new GeneralParamManager();
		GeneralParamBean bean = manager.getGenParamByParamId(genParamId);

		return bean.getGenParamValue();
	}
}
