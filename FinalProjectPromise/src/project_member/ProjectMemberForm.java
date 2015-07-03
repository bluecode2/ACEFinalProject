package project_member;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class ProjectMemberForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private String task ="";
	private Integer selectedId = 0;
	private boolean isAdd = true;
	private Integer currPage = 1;
	private Integer pageCount = 1;

	private List<ProjectMemberBean> listOfProjMember = new ArrayList<ProjectMemberBean>();
	private ProjectMemberBean pMemberbean = new ProjectMemberBean();
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
	public boolean isAdd() {
		return isAdd;
	}
	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageCount() {
		if(this.pageCount.intValue()==0)
			return 1;
		else
			return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<ProjectMemberBean> getListOfProjMember() {
		return listOfProjMember;
	}
	public void setListOfProjMember(List<ProjectMemberBean> listOfProjMember) {
		this.listOfProjMember = listOfProjMember;
	}
	public ProjectMemberBean getpMemberbean() {
		return pMemberbean;
	}
	public void setpMemberbean(ProjectMemberBean pMemberbean) {
		this.pMemberbean = pMemberbean;
	}
}
