package emp;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cmn.DTO;
import cmn.WorkDiv;
public class DeptDao implements WorkDiv<DeptDTO> {
	
	
	final	static	String	DB_DRIVER	= "oracle.jdbc.driver.OracleDriver";
	// jdbc:oracle:thin:@IP:PORT:�쟾�뿭DB紐낆묶(SID)
	final	static	String	DB_URL 		= "jdbc:oracle:thin:@192.168.0.54:1521:xe";
	final	static	String	DB_USER 	= "scott";
	final	static	String	DB_PASSWORD = "pcwk";
	
	public Connection getConnection() {
		Connection conn = null;
		
		System.out.println("1");
		try {
			System.out.println("2");
			Class.forName(DB_DRIVER);
			System.out.println("3");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("4 conn : " + conn);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;		
	}
	

	@Override
	public List<DeptDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}
	  
	@Override
	public DeptDTO doSelectOne(DeptDTO param) { 
		
		DeptDTO outVO = null;
		//getConnection();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(300);
		sb.append ("select deptno, dname,loc from dept where deptno = ? \n"); 
										
		log.debug("1.sql:\n"+sb.toString());
		log.debug("2.conn:"+conn);
		log.debug("3.param:"+param);
		
		try {
			//pstmt = conn.prepareCall(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:"+pstmt);
			
			pstmt.setInt(1,param.getDeptno());
			//pstmt.setInt(0,0);
			
			
			rs = pstmt.executeQuery();
			log.debug("5.rs:" +rs);
			if(rs.next()) {
				outVO = new DeptDTO();
				outVO.setDeptno(rs.getInt("deptno"));
				outVO.setDname(rs.getString("dname"));
				outVO.setLoc(rs.getString("loc"));
				
				log.debug("6.outVO:"+outVO);
			}
			
			
		}catch (SQLException e) {
			log.debug("____________________________");
			log.debug("SQLException"+e.getMessage());
			log.debug("____________________________");
		}finally {
		//DBUtil.close(conn, pstmt);
			if(null !=rs) {
				try {
					rs.close();
				} catch(SQLException e) { 
				}
			}
			if(null !=pstmt) {
				try {
					pstmt.close();
				} catch(SQLException e) { 
				}
			}
			if(null !=conn) {
				try {
					conn.close();
				} catch(SQLException e) { 
				}
			}
			
			return outVO;
		}
}

 
}
