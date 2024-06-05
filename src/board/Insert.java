package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Insert {
	
	public static void main(String[] args) {
		
	
	  Connection conn = null;
      PreparedStatement pstmt = null;
      Scanner input = new Scanner(System.in);

      try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.54:1521:xe", "scott", "pcwk");
          
          if (conn == null) {
              System.out.println("DB�젒�냽�떎�뙣");
          } else {
              System.out.println("DB�젒�냽 �꽦怨�");

              System.out.println("踰덊샇瑜� �엯�젰�빐 �궘�젣�븯�꽭�슂.");
              
              int seq = input.nextInt();
              input.nextLine();
              String title = input.nextLine();
              String contents = input.nextLine();
              
              int read_cnt = input.nextInt();
              input.nextLine();
              String reg_id = input.nextLine();
              String reg_dt = input.nextLine();
              String mod_id = input.nextLine();
              String mod_dt = input.nextLine();
              
              String sql = String.format("INSERT INTO board (seq,title,contents,read_cnt,reg_id,reg_dt,mod_id,mod_dt"
              		+ ") VALUES (?,?,?,?,?,?,?,?)"); 
              //
              pstmt = conn.prepareStatement(sql);
              
	            pstmt.setInt(1, seq);
	  			pstmt.setString(2, title);
	  			pstmt.setString(3, contents);
	  			pstmt.setInt(4, read_cnt);
	  			pstmt.setString(5, reg_id);
	  			pstmt.setString(6, reg_dt);
	  			pstmt.setString(7, mod_id);
	  			pstmt.setString(8, mod_dt);
	  			
              int result = pstmt.executeUpdate(sql);

              System.out.println(result + "踰덉씠 �궘�젣�릺�뿀�뼱�슂.");
          } 
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (SQLException e) {
          e.printStackTrace();
      } catch(InputMismatchException e) {
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