package cmn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker implements PLog {

	final	static	String	DB_DRIVER	= "oracle.jdbc.driver.OracleDriver";
	final	static	String	DB_URL 		= "jdbc:oracle:thin:@192.168.0.30:1521:xe";
	final	static	String	DB_USER 	= "scott";
	final	static	String	DB_PASSWORD = "pcwk";
	
	public ConnectionMaker() {
		log.debug("ConnectionMaker()");
	}
	
	public Connection getConnection() {
		Connection conn = null;
		log.debug("1");
		try {
			Class.forName(DB_DRIVER);
			log.debug("2");
			
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			log.debug("3 connection : {}", conn);
			//�뙆�씪硫뷀꽣濡� �뿰寃고븷 �닔 �엳�떎.
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
