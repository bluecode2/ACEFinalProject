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

				  
//				  if (eForm.getTask().equals("add")) {
//				   eForm.setIsAdd(true);
//				   eForm.setSelectedId(0);
//				   request.setAttribute("listOfDepartment",
//				     dMan.getAllDepartmentsForField());
//				   DepartmentBean reg = new DepartmentBean();
//				   eForm.setSelectedDepartment(reg);
//				   return mapping.findForward("entry");
//				  }
//
//				  else if (eForm.getTask().equals("edit")) {
//				   eForm.setIsAdd(false);
//				    request.setAttribute("listOfDepartment",
//				     dMan.getAllDepartmentsForField());
//				   eForm.setSelectedDepartment(eMan.getDepartment(eForm.getSelectedId()));
//				   return mapping.findForward("entry");
//				  }
//
//				  else if (eForm.getTask().equals("delete")) {
//				   eMan.deleteDepartment(eForm.getSelectedId());
//				  }
//
//				  else if (eForm.getTask().equals("save")) {
//				   boolean isAdd = eForm.getIsAdd();
//
//				   if (eForm.getSelectedDepartment().getDepartmentId() == 0)
//				    eForm.getSelectedDepartment().setDepartmentId(null);
//
//				   if (isAdd) {
//				    eForm.getSelectedDepartment().setDepartmentId(
//				      eMan.getDepartmentMaxId() + 1);
//				    eMan.insertDepartment(eForm.getSelectedDepartment());
//				   } else {
//				    eMan.updateDepartment(eForm.getSelectedDepartment());
//				   }
//				   
//				   response.sendRedirect("departmentMaster.do");
//				   return null;
//				  }
//
//				  else if (eForm.getTask().equals("lang")) {
//				   Locale locale;
//				   if (eForm.getLanguage().equals("ind")) {
//				    locale = new Locale("in");
//
//				   } else {
//				    locale = new Locale("en");
//				   }
//				   session.setAttribute(org.apache.struts.Globals.LOCALE_KEY, locale);
//				   
//				  }
//				  else if(eForm.getTask().equals("print")){
//				   Map<String,String> map = new HashMap<String,String>();
//				   map.put("searchField", eForm.getCurrSearchField());
//				   map.put("searchValue", eForm.getCurrSearchValue());
//				   map.put("startDate", eForm.getCurrStartDateRange());
//				   map.put("endDate", eForm.getCurrEndDateRange());
//				   session.setAttribute("param", map);
//				   return mapping.findForward("reportDepartmentList");
//				  }
//				  
//				  eForm.setTask("");
//				  eForm.setSearchField(eForm.getCurrSearchField());
//				  eForm.setSearchValue(eForm.getCurrSearchValue());
//				  eForm.setStartDateRange(eForm.getCurrStartDateRange());
//				  eForm.setEndDateRange(eForm.getCurrEndDateRange());
//				  eForm.setCurrSearchDepartment(eForm.getSearchDepartment());
//				  
//				  int rowCount;
//				  
//				  if(eForm.getCurrSearchField().equals("department")){
//				   eForm.setCurrSearchValue(eForm.getCurrSearchDepartment());
//				  }
//				  				  
//				  request.setAttribute("listOfDepartment",
//				    dMan.getAllDepartmentsForField());
//				  eForm.setPageCount((int) Math.ceil((double) rowCount
//				    / (double) Constant.pageSize));
//				  request.setAttribute("pageNavigator", CommonFunction
//				    .createPagingNavigatorList(eForm.getPageCount(),
//				      eForm.getCurrPage()));
//				  
				//  request.setAttribute("rowCount", rowCount);
				 // return mapping.findForward("index");
				
				request.setAttribute("pageTitle", "Department List");
				
				request.setAttribute("pageNavigator", CommonFunction
						.createPagingNavigatorList(1,1));
				
				request.setAttribute("pageCount", 1);
				request.setAttribute("currPage", 1);
				request.setAttribute("rowCount", 1);
			
				
				return mapping.findForward("list");
			}
		}