package notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CommonFunction;
import common.Constant;
import user.UserBean;

public class NotificationHandler extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		NotificationForm nForm = (NotificationForm) form;
		NotificationManager nMan = new NotificationManager();
		HttpSession session = request.getSession();	
		UserBean us = (UserBean) session.getAttribute("currUser");
		
		if("select".equals(nForm.getTask())){
			NotificationBean bean = nMan.getNotificationById(nForm.getSelectedId());
			nMan.updateNotificationAsRead(nForm.getSelectedId());
			if(bean.getSessionParameter() != null && !bean.getSessionParameter().equals("")){
				String[] sessionStr = bean.getSessionParameter().split("#");
				session.setAttribute(sessionStr[0], Integer.valueOf(sessionStr[1]));
			}
			response.sendRedirect(bean.getNotificationUrl());
			return null;
		}
		
		CommonFunction.initializeHeader(Constant.MenuCode.NOTIFICATIONS, us, request);
		
		nForm.setLstBean(nMan.getListAllNotificationByEmployee(us.getEmployeeId(),nForm.getCurrPage(),Constant.PAGE_SIZE));
		int rowCount = nMan.getCountNotificationByEmployee(us.getEmployeeId());
		nForm.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));
		
		request.setAttribute("pageCount", nForm.getPageCount());
		request.setAttribute("currPage", nForm.getCurrPage());
		return mapping.findForward("list");
	}
}
