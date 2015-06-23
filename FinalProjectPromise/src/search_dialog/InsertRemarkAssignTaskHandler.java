package search_dialog;

import independent_task.AssignTaskManager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InsertRemarkAssignTaskHandler extends Action {
@Override
public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	response.setContentType("text/text;charset=utf-8");
	response.setHeader("cache-control", "no-cache");
	

	
	/*atMan.editStatusAssignTask(taskId, updatedBy, status, remarks);*/

	return null;
}
}
