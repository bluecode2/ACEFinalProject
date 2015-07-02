package audit_trail;

import java.util.List;

public class AuditTrailHandler {
	
	public void exportToTxtAuditTrail(){
		
		AuditTrailManager aTrailMan = new AuditTrailManager();
		List<AuditTrailBean> listOfAuditTrail = aTrailMan.getAuditTrail();
		
	}
}
