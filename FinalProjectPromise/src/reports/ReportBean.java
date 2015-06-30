package reports;

public class ReportBean {

	private Integer reportId;
	private String	reportCode;
	private String	reportCaption;
	private Integer	parentId;
	private String	filterField;
	private String	filterPanel;
	private String	reportFile;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportCaption() {
		return reportCaption;
	}
	public void setReportCaption(String reportCaption) {
		this.reportCaption = reportCaption;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getFilterField() {
		return filterField;
	}
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	public String getFilterPanel() {
		return filterPanel;
	}
	public void setFilterPanel(String filterPanel) {
		this.filterPanel = filterPanel;
	}
	public String getReportFile() {
		return reportFile;
	}
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}
}
