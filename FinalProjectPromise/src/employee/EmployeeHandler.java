package employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;
import department.DepartmentManager;

public class EmployeeHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		EmployeeForm eForm = (EmployeeForm) form;
		DepartmentManager dMan = new DepartmentManager();
		EmployeeManager eMan = new EmployeeManager();

		HttpSession session = request.getSession(true);
		
		
		
		eForm.setTask("");
		eForm.setSearchField(eForm.getCurrSearchField());
		eForm.setSearchValue(eForm.getCurrSearchValue());

		int rowCount;

		eForm.setListOfEmployee(eMan.getAllEmployee(
				eForm.getCurrSearchField(), eForm.getCurrSearchValue(),
				eForm.getCurrPage(), Constant.pageSize));
		rowCount = eMan.getCountEmployee(eForm.getCurrSearchField(),
				eForm.getCurrSearchValue());
		//
		eForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.pageSize));

		request.setAttribute("pageTitle", "Employee List");

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(eForm.getPageCount(),
						eForm.getCurrPage()));

		request.setAttribute("pageCount", eForm.getPageCount());
		request.setAttribute("currPage", eForm.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}
}
