package project_log;

import java.util.ArrayList;
import java.util.List;

public class ProjectLogHandler {
	private ProjectLogManager prLogManager;

	public ProjectLogHandler() {
		// TODO Auto-generated constructor stub
		prLogManager = new ProjectLogManager();
	}

	public void backupProjectLog() {
		try {
			List<ProjectLogBean> listProjectLog = prLogManager
					.getListProjectLogBeanForBackup();

			for (ProjectLogBean projectLogBean : listProjectLog) {
				System.out
						.println(String
								.format("| %-5s | %-5s | %-5s | %-150s | %-100s | %-100s | %-20s | %-50s | %-10s | %-10s |",
										projectLogBean.getProjectLogId(),
										projectLogBean.getProjectId(),
										projectLogBean.getUpdateBy(),
										projectLogBean.getProjectField(),
										projectLogBean.getProjectOldValue(),
										projectLogBean.getProjectNewValue(),
										projectLogBean.getUpdateDateInString(),
										projectLogBean.getRemarks(),
										projectLogBean.getChangeType(),
										projectLogBean.getChangeTypeInString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
