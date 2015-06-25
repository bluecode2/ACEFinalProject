package activity;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;

public class ActivityHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		ActivityForm aForm = (ActivityForm) form;
		ActivityManager aMan = new ActivityManager();
		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");
		PrintWriter out = response.getWriter();

		if ("viewActivity".equals(aForm.getTask())) {
			List<ActivityBean> arrList = aMan.getActivityWithTaskId(aForm
					.getTaskId());
			if (arrList.size() > 0) {
				for (ActivityBean actBean : arrList) {
					out.println("<tr>");
					out.println("<td>" + actBean.getActivityDesc() + "</td>");
					if (actBean.getIsCompleted() == 1) {
						out.println("<td> <input type=\"checkbox\" checked disabled> </td>");
					} else {
						out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
					}

					out.println("</tr>");
				}
			} else {
				out.println("<tr>");
				out.println("<td colspan=\"2\" align=\"center\">No Data Found</td>");
				out.println("</tr>");
			}
		}
		else if ("manageActivity".equals(aForm.getTask())) {
			List<ActivityBean> arrList = aMan.getActivityWithTaskId(aForm
					.getTaskId());
			if (arrList.size() > 0) {
				for (ActivityBean actBean : arrList) {
					out.println("<tr>");
					out.println("<td>" + actBean.getActivityDesc() + "</td>");
					if (actBean.getIsCompleted() == 1) {
						out.println("<td> <input type=\"checkbox\" checked disabled> </td>");
					} else {
						out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
					}

					out.println("</tr>");
				}
			} else {
				out.println("<tr>");
				out.println("<td colspan=\"2\" align=\"center\">No Data Found</td>");
				out.println("</tr>");
			}
		}

		return null;
	}
}
