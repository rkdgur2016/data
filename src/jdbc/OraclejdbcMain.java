package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraclejdbcMain {
	final	static	String	DB_DRIVER	= "oracle.jdbc.driver.OracleDriver";
	// jdbc:oracle:thin:@IP:PORT:�쟾�뿭DB紐낆묶(SID)
	final	static	String	DB_URL 		= "jdbc:oracle:thin:@192.168.0.61:1521:xe";
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
	
	public static void main(String[] args) {
//		1. DriverManager濡� �뜲�씠�꽣 踰좎씠�뒪�� �뿰寃� : id/password
//		2. Connection : �뜲�씠�꽣 踰좎씠�뒪�� �뿰寃� id / password
//		3. Statement/**PreparedStatement : SQL臾몄쓣 �떎�뻾** �씤�꽣�럹�씠�뒪
//		4. ResultSet : SQL臾몄쓽 寃곌낵瑜� ���옣�븯怨� 議고쉶�븯�뒗 �씤�꽣�럹�씠�뒪
//		5. �뿰寃곗쥌猷�
		OraclejdbcMain m = new OraclejdbcMain();
		m.getConnection();			
	} // main

} // class