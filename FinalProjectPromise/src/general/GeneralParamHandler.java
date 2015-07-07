package general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import user.UserBean;
import common.CommonFunction;
import common.Constant;

public class GeneralParamHandler extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		GeneralParamManager gpm = new GeneralParamManager();
		GeneralParamForm gpf = (GeneralParamForm) form;

		HttpSession session = request.getSession();
		UserBean us = (UserBean) session.getAttribute("currUser");

		if ("add".equals(gpf.getTask())) {
			gpf.setIsAdd(true);
			request.setAttribute("pageTitle", "General Parameter Entry");
			CommonFunction.initializeHeader(
					Constant.MenuCode.GENERAL_PARAMETER_ENTRY, us, request);

			return mapping.findForward("entry");
		} else if ("save".equals(gpf.getTask())) {
			gpf.getBean().setUpdatedBy(us.getUserId());
			if (gpm.updateGeneralParam(gpf.getBean())) {
				session.setAttribute("validationMessage",
						"Succeed to Edit General Parameter "
								+ gpf.getBean().getGenParamId() + "!");
				session.setAttribute("validationType", "success");
			} else {
				session.setAttribute("validationMessage",
						"Failed to Edit General Parameter "
								+ gpf.getBean().getGenParamId() + "!");
				session.setAttribute("validationType", "danger");
			}

			response.sendRedirect("generalParam.do");
			return null;
		} else if ("edit".equals(gpf.getTask())) {
			request.setAttribute("pageTitle", "General Parameter Edit");
			gpf.setBean(gpm
					.getGenParamByParamId(gpf.getSelectedId().toString()));
			CommonFunction.initializeHeader(
					Constant.MenuCode.GENERAL_PARAMETER_ENTRY, us, request);

			return mapping.findForward("entry");
		} else if ("delete".equals(gpf.getTask())) {
			gpf.getBean().setUpdatedBy(us.getUserId());
			if (gpm.deleteGeneralParam(gpf.getBean())) {
				session.setAttribute("validationMessage",
						"Succeed to Delete General Parameter!");
				session.setAttribute("validationType", "success");
			} else {
				session.setAttribute("validationMessage",
						"Failed to Delete General Parameter "
								+ gpf.getBean().getGenParamId() + "!");
				session.setAttribute("validationType", "danger");
			}
		}

		gpf.setTask("");
		gpf.setSearchField(gpf.getCurrSearchField());
		gpf.setSearchValue(gpf.getCurrSearchValue());

		int rowCount;
		gpf.setArrList(gpm.getAllGeneralParam(gpf.getCurrSearchField(),
				gpf.getCurrSearchValue(), gpf.getCurrPage(), Constant.PAGE_SIZE));

		rowCount = gpm.getCountGeneralParam(gpf.getCurrSearchField(),
				gpf.getCurrSearchValue());

		gpf.setPageCount((int) Math.ceil((double) rowCount
				/ (double) Constant.PAGE_SIZE));

		CommonFunction.initializeHeader(Constant.MenuCode.GENERAL_PARAMETER,
				us, request);

		if (session.getAttribute("validationMessage") != null) {
			request.setAttribute("validationMessage",
					session.getAttribute("validationMessage").toString());
			request.setAttribute("validationType",
					session.getAttribute("validationType").toString());
			session.removeAttribute("validationMessage");
			session.removeAttribute("validationType");
		}

		request.setAttribute("pageNavigator", CommonFunction
				.createPagingNavigatorList(gpf.getPageCount(),
						gpf.getCurrPage()));

		request.setAttribute("pageCount", gpf.getPageCount());
		request.setAttribute("currPage", gpf.getCurrPage());
		request.setAttribute("rowCount", rowCount);

		return mapping.findForward("list");
	}

}
