package answer;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cmn.ConnectionMaker;
import cmn.DBUtil;
import cmn.DTO;
import cmn.PLog;
import cmn.SearchDTO;
import cmn.WorkDiv;

public class AnswerDAO implements WorkDiv<AnswerDTO>, PLog {

	// 1. doSave()
	// 2. doUpdate()
	// 3. doDelete()
	// 4. doSelectOne()

	private ConnectionMaker connectionMaker;

	public AnswerDAO() {
		connectionMaker = new ConnectionMaker();
	}

	@Override
	public int doSave(AnswerDTO param) {
		int flag = 0;

		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;

		StringBuilder sb = new StringBuilder();

		sb.append(" INSERT INTO answer ( \n");
		sb.append("     seq,             \n");
		sb.append("     contents,        \n");
		sb.append("     board_seq,       \n");
		sb.append("     reg_id,          \n");
		sb.append("     reg_dt,          \n");
		sb.append("     mod_id,          \n");
		sb.append("     mod_dt           \n");
		sb.append(" ) VALUES (           \n");
		sb.append("     ?,               \n");
		sb.append("     ?,          	 \n");
		sb.append("     ?,      	     \n");
		sb.append("     ?,  	         \n");
		sb.append("     SYSDATE,         \n");
		sb.append("     ?,	             \n");
		sb.append("     SYSDATE          \n");
		sb.append(" )                    \n");

		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);

		try {
			pstmt = conn.prepareStatement(sb.toString()); // 荑쇰━瑜� �꽆湲곗옄~.
			log.debug("4. pstmt : {}", pstmt);

			// param �꽕�젙
			pstmt.setInt(1, param.getSeq());
			pstmt.setString(2, param.getContents());
			pstmt.setInt(3, param.getBoardSeq());
			pstmt.setString(4, param.getRegId());
			pstmt.setString(5, param.getModId());

			// DML �닔�뻾
			flag = pstmt.executeUpdate(); // 諛섏쁺 嫄댁닔 由ы꽩

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		log.debug("6. flag : {}", flag);
		
		return flag;
	}
	
	@Override
	public int doUpdate(AnswerDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE answer 			\n");
		sb.append("SET                		\n");
		sb.append("     contents = ?,		\n");
		sb.append("     reg_id = ?,   		\n");
		sb.append("     mod_dt = SYSDATE	\n");
		sb.append("WHERE                	\n");
		sb.append("        seq = ?   		\n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setString(1, param.getContents());
			pstmt.setString(2, param.getRegId());
			pstmt.setInt(3, param.getSeq());
			
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		log.debug("6. flag : {}", flag);
		return flag;
	}


	@Override
	public int doDelete(AnswerDTO param) {
		int flag = 0;

		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;

		StringBuilder sb = new StringBuilder();
		
		sb.append(" DELETE FROM answer  \n");
		sb.append(" WHERE seq = ?		\n");

		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);

		try {
			pstmt = conn.prepareStatement(sb.toString()); // 荑쇰━瑜� �꽆湲곗옄~.
			log.debug("4. pstmt : {}", pstmt);

			// param �꽕�젙
			pstmt.setInt(1, param.getSeq());

			// DML �닔�뻾
			flag = pstmt.executeUpdate(); // 諛섏쁺 嫄댁닔 由ы꽩

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		} 
		log.debug("6. flag : {}", flag);

		return flag;
	}

	@Override
	public AnswerDTO doSelectOne(AnswerDTO param) {
		AnswerDTO outVO = null;

		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // ResultSet�� ���냼臾몄옄瑜� 援щ텇�븯吏� �븡�뒗�떎.

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT                                              \n");
		sb.append("     seq,                                            \n");
		sb.append("     contents,                                       \n");
		sb.append("     board_seq,                                      \n");
		sb.append("     reg_id,                                         \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt, \n");
		sb.append("     mod_id,                                         \n");
		sb.append("     TO_CHAR(mod_dt,'YYYY/MM/DD HH24:MI:SS') mod_dt  \n");
		sb.append(" FROM                                                \n");
		sb.append("     answer                                          \n");
		sb.append(" WHERE  seq = ?                                      \n");

		log.debug("1.sql : {} \n " + sb.toString());
		log.debug("2.conn : {} ", conn);
		log.debug("3.param : {}", param);

		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt : {} ", pstmt);

			pstmt.setInt(1, param.getSeq());
			rs = pstmt.executeQuery();
			log.debug("5. rs : " + rs);

			if (rs.next()) {
				outVO = new AnswerDTO();
				outVO.setSeq(rs.getInt("seq"));
				outVO.setContents(rs.getString("contents"));
				outVO.setBoardSeq(rs.getInt("board_seq"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				log.debug("6.outVO:" + outVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		return outVO;
	}
	
	public List<AnswerDTO> doRetrieve(DTO search) {
		
		SearchDTO searchVO = (SearchDTO) search;
		
		StringBuilder sbWhere = new StringBuilder();
//	     --WHERE contents LIKE :searchWord||'%' "10"
//	     --WHERE mod_id   = :searchWord			"20"
//	     --   OR contents LIKE :searchWord||'%'	
//	     --WHERE seq      = :searchWord			"30"
			
		List<AnswerDTO> list = new ArrayList<AnswerDTO>();
		
		Connection conn = connectionMaker.getConnection();  //DB �뿰寃�
		PreparedStatement pstmt = null;						//SQL + param
		ResultSet rs = null;								//SQL 寃곌낵
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*,B.*																		\n");				
		sb.append(" FROM (                                                                              \n");
		sb.append("     SELECT tt2.rnum no,                                                             \n");
		sb.append("            tt2.seq,                                                                 \n");
		sb.append("            tt2.contents,                                        					\n");
		sb.append("            tt2.board_seq,                                                           \n");
		sb.append("            tt2.mod_id,                                                              \n");
		sb.append("            DECODE(TO_CHAR(tt2.mod_dt,'YYYY/MM/DD'),TO_CHAR(SYSDATE,'YYYY/MM/DD')    \n");
		sb.append("                                                   ,TO_CHAR(tt2.mod_dt,'HH24:MI')    \n");
		sb.append("                                                   ,TO_CHAR(tt2.mod_dt,'YYYY/MM/DD'))\n");
		sb.append("            mod_dt                                                                   \n");
		sb.append("     FROM (                                                                          \n");
		sb.append("         SELECT ROWNUM rnum,t1.*                                                     \n");
		sb.append("         FROM (                                                                      \n");
		sb.append("                 SELECT t1.*                                                         \n");
		sb.append("                   FROM answer t1                                                    \n");
		sb.append("                  WHERE board_seq = 900                                              \n");
		sb.append("                  ORDER BY mod_dt DESC                                               \n");
		sb.append("         )t1                                                                         \n");
		sb.append("         WHERE ROWNUM <=10                                                           \n");
		sb.append("         --WHERE ROWNUM <=( :page_size * (:page_no -1  ) + :page_size)               \n");
		sb.append("         --WHERE ROWNUM <=( ? * (? -1  ) + ?)                                        \n");
		sb.append("     )tt2                                                                            \n");
		sb.append("     WHERE rnum>=1                                                                   \n");
		sb.append(" )A CROSS JOIN (                                                                     \n");
		sb.append("                 SELECT count(*) totalCnt                                            \n");
		sb.append("                   FROM answer t1                                                    \n");
		sb.append("                  WHERE board_seq = 900                                              \n");
		sb.append(" )B                                                                                  \n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", search);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			//select �떎�뻾
			rs = pstmt.executeQuery(); 
			log.debug("5. rs : {}", rs);
			
			while(rs.next()) {
				AnswerDTO outVO = new AnswerDTO();
				outVO.setSeq(rs.getInt("seq"));
				outVO.setContents(rs.getString("Contents"));
				outVO.setBoardSeq(rs.getInt("board_seq"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				
				list.add(outVO);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}

}
