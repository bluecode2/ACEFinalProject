package common;

import general.MenuBean;
import general.MenuManager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import user.UserBean;

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

		int maxShownPageCount = Constant.pageNavigatorSize;

		int frontCount = (Constant.pageNavigatorSize / 2);
		if (pageCount - currPage < Constant.pageNavigatorSize / 2) {
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
			ArrayList<MenuBean> tmpLvl2 = menuMan.getAllMenuByParent(menuLvl1
					.getMenuId(),user.getUserRoleId());

			for (MenuBean menuLvl2 : tmpLvl2) {
				arrMenuLvl2.add(menuLvl2);
				ArrayList<MenuBean> tmpLvl3 = menuMan
						.getAllMenuByParent(menuLvl2.getMenuId(),user.getUserRoleId());

				for (MenuBean menuLvl3 : tmpLvl3) {
					arrMenuLvl3.add(menuLvl3);
				}
			}
		}

		request.setAttribute("arrMenuLvl1", arrMenuLvl1);
		request.setAttribute("arrMenuLvl2", arrMenuLvl2);
		request.setAttribute("arrMenuLvl3", arrMenuLvl3);

		if (user != null)
			request.setAttribute("userName", user.getEmployeeName());
	}

	
	public static void initializeHeader(String menuCode, UserBean user,
			HttpServletRequest request) {
		ArrayList<MenuBean> arrMenuLvl1 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl2 = new ArrayList<MenuBean>();
		ArrayList<MenuBean> arrMenuLvl3 = new ArrayList<MenuBean>();

		MenuManager menuMan = new MenuManager();
		MenuBean menu = menuMan.getMenuByMenuId(menuCode);
		
		
		arrMenuLvl1 = menuMan.getAllMenuHead(user.getUserRoleId());

		for (MenuBean menuLvl1 : arrMenuLvl1) {
			ArrayList<MenuBean> tmpLvl2 = menuMan.getAllMenuByParent(menuLvl1
					.getMenuId(),user.getUserRoleId());

			for (MenuBean menuLvl2 : tmpLvl2) {
				arrMenuLvl2.add(menuLvl2);
				ArrayList<MenuBean> tmpLvl3 = menuMan
						.getAllMenuByParent(menuLvl2.getMenuId(),user.getUserRoleId());

				for (MenuBean menuLvl3 : tmpLvl3) {
					arrMenuLvl3.add(menuLvl3);
				}
			}
		}

		request.setAttribute("arrMenuLvl1", arrMenuLvl1);
		request.setAttribute("arrMenuLvl2", arrMenuLvl2);
		request.setAttribute("arrMenuLvl3", arrMenuLvl3);
		
		request.setAttribute("userName", user.getEmployeeName());
		
		request.setAttribute("pageTitle", menu.getMenuCaption());
		
		request.setAttribute("btnAddVisible", menu.getMenuCrud().contains("C"));
		request.setAttribute("btnBackVisible", menu.getMenuCrud().contains("B"));
		request.setAttribute("btnSaveVisible", menu.getMenuCrud().contains("U"));
		request.setAttribute("btnApproveVisible", menu.getMenuCrud().contains("A"));
		request.setAttribute("btnDeclineVisible", menu.getMenuCrud().contains("D"));
	}
}
