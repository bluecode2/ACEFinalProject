package department;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;

public class DepartmentHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DepartmentForm eForm = (DepartmentForm) form;
		DepartmentManager eMan = new DepartmentManager();
		DepartmentManager dMan = new DepartmentManager();

		HttpSession session = request.getSession(true);

		CommonFunction.createAllowedMenu(null, request);

		if (eForm.getTask().equals("add")) {
			eForm.setIsAdd(true);
			eForm.setSelectedId(0);
			return mapping.findForward("entry");
		}

		else if (eForm.getTask().equals("edit")) {
			eForm.setIsAdd(false);
			return mapping.findForward("entry");
		}

		else if (eForm.getTask().equals("delete")) {
			eMan.deleteDepartment(eForm.getSelectedId());
		}
		//
		// else if (eForm.getTask().equals("save")) {
		// boolean isAdd = eForm.getIsAdd();
		//
		// if (eForm.getSelectedDepartment().getDepartmentId() == 0)
		// eForm.getSelectedDepartment().setDepartmentId(null);
		//
		// if (isAdd) {
		// eForm.getSelectedDepartment().setDepartmentId(
		// eMan.getDepartmentMaxId() + 1);
		// eMan.insertDepartment(eForm.getSelectedDepartment());
		// } else {
		// eMan.updateDepartment(eForm.getSelectedDepartment());
		// }
		//
		// response.sendRedirect("departmentMaster.do");
		// return null;
		// }
		//
		//
		eForm.setTask("");
		eForm.setSearchField(eForm.getCurrSearchField());
		eForm.setSearchValue(eForm.getCurrSearchValue());

		int rowCount;

		eForm.setArrList(eMan.getAllDepartmentsFiltered(eForm.getCurrSearchField(), eForm.getCurrSearchValue(),eForm.getCurrPage(),Constant.pageSize));
		rowCount = eMan.getCountDepartment(
				eForm.getCurrSearchField(), eForm.getCurrSearchValue());
		//
		eForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Department List");

		request.setAttribute("pageNavigator",
				CommonFunction.createPagingNavigatorList(eForm.getPageCount(),
						eForm.getCurrPage()));

		request.setAttribute("pageCount", eForm.getPageCount());
		request.setAttribute("currPage", eForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}