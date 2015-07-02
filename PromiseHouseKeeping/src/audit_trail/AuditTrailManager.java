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
	
	public List<AuditTrailBean> getAuditTrail(){
		List<AuditTrailBean> listAudit = new ArrayList<AuditTrailBean>();
		try {
			listAudit = this.ibatis.queryForList("auditTrail.getAllAuditTrail", null);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listAudit;
	}
	
	public void delAuditTrail(){
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("auditTrail.delAuditTrail", null);
			this.ibatis.commitTransaction();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				ibatis.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
