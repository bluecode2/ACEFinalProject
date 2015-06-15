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

		DepartmentForm dForm = (DepartmentForm) form;
		DepartmentManager dMan = new DepartmentManager();

		HttpSession session = request.getSession(true);

		CommonFunction.createAllowedMenu(null, request);

		if (dForm.getTask().equals("add")) {
			dForm.setIsAdd(true);
			dForm.setSelectedId(0);
			request.setAttribute("pageTitle", "Department Entry");
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("edit")) {
			dForm.setIsAdd(false);
			request.setAttribute("pageTitle", "Department Entry");
			dForm.setSelectedDept(dMan.getDepartmentByDeptId(dForm
					.getSelectedId()));
			return mapping.findForward("entry");
		}

		else if (dForm.getTask().equals("delete")) {
			dMan.deleteDepartment(dForm.getSelectedId(), 1);
		}

		else if (dForm.getTask().equals("save")) {
			Boolean isAdd = dForm.getIsAdd();

			if (dForm.getSelectedDept().getDeptHeadId() == 0)
				dForm.getSelectedDept().setDeptHeadId(null);

			if (isAdd) {
				dForm.getSelectedDept().setCreatedBy(1);
				dMan.insertDepartment(dForm.getSelectedDept());
			} else {
				dForm.getSelectedDept().setUpdatedBy(1);
				dMan.updateDepartment(dForm.getSelectedDept());
			}

			response.sendRedirect("department.do");
			return null;
		}

		dForm.setTask("");
		dForm.setSearchField(dForm.getCurrSearchField());
		dForm.setSearchValue(dForm.getCurrSearchValue());

		int rowCount;

		dForm.setArrList(dMan.getAllDepartmentsFiltered(
				dForm.getCurrSearchField(), dForm.getCurrSearchValue(),
				dForm.getCurrPage(), Constant.pageSize));
		rowCount = dMan.getCountDepartment(dForm.getCurrSearchField(),
				dForm.getCurrSearchValue());
		//
		dForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Department List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(dForm.getPageCount(),
						dForm.getCurrPage()));

		request.setAttribute("pageCount", dForm.getPageCount());
		request.setAttribute("currPage", dForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}