package employee;

import ibatis.IbatisHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RankEmpManager {
	
	private SqlMapClient ibatis;
	
	public RankEmpManager(){
		this.ibatis = IbatisHelper.getSqlMapInstance();
	}
	
	public List<EmployeeBean> getAllEmployeeRank() throws ClassNotFoundException, SQLException {
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		list = this.ibatis.queryForList("employee.getAllEmployeeRank", null);
		return list;
	}
	
	public void insertEmployeeRank(EmployeeBean eb) throws ClassNotFoundException, SQLException{
		Integer autoRankId = (Integer) this.ibatis.queryForObject("employee.getCountEmployeeRankId", null);
		if (autoRankId == null){
			autoRankId = 1;
		}
		eb.setEmployeeId(autoRankId);
		try {
			this.ibatis.startTransaction();
			this.ibatis.insert("employee.insertEmployeeRank", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
	public void updateEmployeeRank(EmployeeBean eb) throws ClassNotFoundException, SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.update("employee.updateEmployeeRank", eb);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
	public void deleteEmployeeRank(Integer EmployeeId) throws ClassNotFoundException, SQLException{
		try {
			this.ibatis.startTransaction();
			this.ibatis.delete("employee.deleteEmployeeRank", EmployeeId);
			this.ibatis.commitTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			this.ibatis.endTransaction();
		}
	}
	
}
