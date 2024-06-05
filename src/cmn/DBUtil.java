package cmn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	public static void close(Connection conn, PreparedStatement pstmt ) {
	
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			}catch(SQLException e) {
				
			}
		}
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
	}
}
