package search_dialog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import rank_employee.RankEmpBean;
import rank_employee.RankEmpManager;

public class SearchRankHandler extends Action {
@Override
public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	response.setContentType("text/text;charset=utf-8");
	response.setHeader("cache-control", "no-cache");
	PrintWriter out = response.getWriter();
	
	SearchRankForm srForm = (SearchRankForm) form;
	RankEmpManager reMan = new RankEmpManager();
	
	String searchField = srForm.getSearchField();
	String searchValue = srForm.getSearchValue();
	System.out.println(searchField  + " "+ searchValue);
	List<RankEmpBean> arrRank = reMan.getListRankForSearch(searchField, searchValue);

	for (RankEmpBean reBean : arrRank) {
		out.println("<tr data-dismiss=\"modal\" class=\"rowSearchRank\">");
		out.println("<td style=\"display: none\">" + reBean.getRankId() + "</td>");
		out.println("<td>" + reBean.getRankCode() + "</td>");
		out.println("<td>" + reBean.getRankName() + "</td>");
		out.println("<td>" + reBean.getRankLevel() + "</td>");
		out.println("</tr>");
	}
	out.flush();

	return null;
}
}
