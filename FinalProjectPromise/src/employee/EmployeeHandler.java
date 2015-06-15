package employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;

public class EmployeeHandler extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		EmployeeForm eForm = (EmployeeForm) form;
		request.setAttribute("pageTitle", "Employee List");
		CommonFunction.createAllowedMenu(null, request);
		
		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(15,5));
		
		request.setAttribute("pageCount", 5);
		request.setAttribute("currPage", 5);
		request.setAttribute("rowCount", 50);
		
		if ("add".equalsIgnoreCase(eForm.getTask())){
			return mapping.findForward("employeeAdd");
		}
//		return super.execute(mapping, form, request, response);
		return mapping.findForward("employeeList");
	}
}
