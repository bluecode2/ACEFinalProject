package audit_trail;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ibatis.IbatisHelper;

import com.ibatis.sqlmap.client.SqlMapClient;
import common.Constant;

public class AuditTrailManager {

	private SqlMapClient ibatis;
	SimpleDateFormat sdf = new SimpleDateFormat(Constant.StringFormat.dateFormat);
	
	public AuditTrailManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<AuditTrailBean> getAuditTrailByDate() throws SQLException{
		List<AuditTrailBean> listAudit = new ArrayList<AuditTrailBean>();
		listAudit = this.ibatis.queryForList("auditTrail.getAllAuditTrail", null);
		
		return listAudit;
	}
	
	
}
