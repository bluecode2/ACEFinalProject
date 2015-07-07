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

import common.Constant;

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
			List<ActivityBean> arrList = aMan.getActivityByTaskId(aForm
					.getTaskId());
			if (arrList.size() > 0) {
				for (ActivityBean actBean : arrList) {
					out.println("<tr>");
					out.println("<td>" + actBean.getActivityDesc() + "</td>");
					if (actBean.getIsCompleted() == 1) {
						out.println("<td align=\"center\"> <input type=\"checkbox\" checked disabled> </td>");
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
			List<ActivityBean> arrList = aMan.getActivityByTaskId(aForm
					.getTaskId());
			
			
			if (arrList.size() > 0) {
				for (ActivityBean actBean : arrList) {
					out.println("<tr>");
					out.println("<input type=\"hidden\" class=\"hdnActivityId\" value=\""
							+ actBean.getActivityId() + "\" />");
					out.println("<td>" + actBean.getActivityDesc() + "</td>");
					if (actBean.getIsCompleted() == 1) {
						out.println("<td align=\"center\"> <input type=\"checkbox\" checked disabled> </td>");
						if(actBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING))
							out.println("<td align=\"center\"><a class=\"text-warning btnUndoComplete\" href=\"#\" title=\"Undo Complete\"><span class=\"glyphicon glyphicon-repeat\" aria-hidden=\"true\"></span></a></td>");
						else
							out.println("<td align=\"center\"></td>");
					} else {
						out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
						if(actBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING))
							out.println("<td align=\"center\"><a class=\"text-success btnComplete\" href=\"#\" title=\"Complete\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></a> &nbsp; <a class=\"text-danger btnActivityDelete\" href=\"#\" title=\"Delete\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
						else if(actBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_NOT_STARTED))
							out.println("<td align=\"center\"><a class=\"text-danger btnActivityDelete\" href=\"#\" title=\"Delete\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
						else
							out.println("<td align=\"center\"></td>");
					}

					out.println("</tr>");
				}
			} else {
				out.println("<tr class=\"emptyRow\">");
				out.println("<td colspan=\"3\" align=\"center\">No Data Found</td>");
				out.println("</tr>");
			}
		}
		else if ("addActivity".equals(aForm.getTask())) {
			ActivityBean newBean = new ActivityBean();
			newBean.setActivityDesc(aForm.getActDesc());
			newBean.setTaskId(aForm.getTaskId());
			newBean.setEmployeeId(us.getEmployeeId());
			newBean.setCreatedBy(us.getUserId());
			Integer id = aMan.insertActivity(newBean);
			ActivityBean actBean = aMan.getActivityById(id);
			
			
			out.println("<tr>");
			out.println("<input type=\"hidden\" class=\"hdnActivityId\" value=\""
					+ actBean.getActivityId() + "\" />");
			out.println("<td>" + actBean.getActivityDesc() + "</td>");
			
			out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
			if(actBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_ONGOING))
				out.println("<td align=\"center\"><a class=\"text-success btnComplete\" href=\"#\" title=\"Complete\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></a> &nbsp; <a class=\"text-danger btnActivityDelete\" href=\"#\" title=\"Delete\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
			else if(actBean.getTaskStatus().equals(Constant.GeneralCode.TASK_STATUS_NOT_STARTED))
				out.println("<td align=\"center\"><a class=\"text-danger btnActivityDelete\" href=\"#\" title=\"Delete\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
			else
				out.println("<td align=\"center\"></td>");
			
			out.println("</tr>");

		}
		
		else if ("updateActivity".equals(aForm.getTask())) {
			ActivityBean bean = aMan.getActivityById(aForm.getSelectedId());
			bean.setIsCompleted(aForm.getIsCompleted());
			bean.setUpdatedBy(us.getUserId());
			aMan.updateActivity(bean);

			if (bean.getIsCompleted() == 1) {
				out.println("<td align=\"center\"> <input type=\"checkbox\" checked disabled> </td>");
				out.println("<td align=\"center\"><a class=\"text-warning btnUndoComplete\" href=\"#\" title=\"Undo Complete\"><span class=\"glyphicon glyphicon-repeat\" aria-hidden=\"true\"></span></a></td>");
			} else {
				out.println("<td align=\"center\"> <input type=\"checkbox\" disabled> </td>");
				out.println("<td align=\"center\"><a class=\"text-success btnComplete\" href=\"#\" title=\"Complete\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></a> &nbsp; <a class=\"text-danger btnActivityDelete\" href=\"#\" title=\"Delete\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>");
			}
		}
		
		else if ("deleteActivity".equals(aForm.getTask())) {
			aMan.deleteActivity(aForm.getSelectedId());
		}

		return null;
	}
}
