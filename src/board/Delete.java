package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
	
    public static void main(String[] args) {
    	
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner input = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.54:1521:xe", "scott", "pcwk");
            
            if (conn == null) {
                System.out.println("DB�젒�냽�떎�뙣");
            } else {
                System.out.println("DB�젒�냽 �꽦怨�");

                System.out.println("踰덊샇瑜� �엯�젰�빐 �궘�젣�븯�꽭�슂.");
                int seq = input.nextInt();

                
                String sql = String.format("DELETE FROM board WHERE seq = %d", seq); 
                pstmt = conn.prepareStatement(sql);
                
                int result = pstmt.executeUpdate(sql);

                System.out.println(result + "踰덉씠 �궘�젣�릺�뿀�뼱�슂.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            input.close();
        }
    }
}