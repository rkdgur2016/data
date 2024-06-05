package board;

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

public class BoardDAO implements WorkDiv<BoardDTO>,PLog {
	
	private ConnectionMaker connectionMaker;

	public BoardDAO() {
		connectionMaker = new ConnectionMaker();
	}
	
	public List<BoardDTO> doRetrieve(DTO search) {
		
		SearchDTO searchVO = (SearchDTO) search;
		
		StringBuilder sbWhere = new StringBuilder();
//		 --WHERE title    LIKE :searchWord||'%'	"10"
//	     --WHERE contents LIKE :searchWord||'%' "20"
//	     --WHERE mod_id   = :searchWord			"30"
//	     --WHERE title   LIKE :searchWord||'%'	"40"
//	     --   OR contents LIKE :searchWord||'%'	
//	     --WHERE seq      = :searchWord			"50"
		
		if(null != searchVO.getSearchDiv() &&searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("WHERE title LIKE ? || '%' \n");
		}else if(null != searchVO.getSearchDiv() &&searchVO.getSearchDiv().equals("20")) {
			sbWhere.append("WHERE contents LIKE ? || '%' \n");
		}else if(null != searchVO.getSearchDiv() &&searchVO.getSearchDiv().equals("30")) {
			sbWhere.append("WHERE mod_id = ? \n");
		}else if(null != searchVO.getSearchDiv() &&searchVO.getSearchDiv().equals("40")) {
			sbWhere.append("WHERE title LIKE ? || '%' \n");
			sbWhere.append("	OR contents LIKE ? || '%'");
		}else if(null != searchVO.getSearchDiv() &&searchVO.getSearchDiv().equals("50")) {
			sbWhere.append("WHERE seq = ? \n");
		}
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("	SELECT A.*, B.*																					\n");
		sb.append("	FROM (                                                                                          \n");
		sb.append("		SELECT TT1.rnum AS num,                                                                     \n");
		sb.append("				tt1.seq,                                                                            \n");
		sb.append("				tt1.title,                                                                          \n");
		sb.append("				tt1.read_cnt,                                                                       \n");
		sb.append("				tt1.mod_id,                                                                         \n");
		sb.append("				DECODE(TO_CHAR(tt1.mod_dt, 'YYYY/MM/DD'),to_char(SYSDATE, 'YYYY/MM/DD')             \n");
		sb.append("														,to_char(TT1.mod_dt, 'HH24:MI')             \n");
		sb.append("														,to_char(TT1.mod_dt, 'YYYY/MM/DD')) mod_dt  \n");
		sb.append("				                                                                                    \n");
		sb.append("		FROM (                                                                                      \n");
		sb.append("				SELECT ROWNUM AS rnum, T1.*                                                         \n");
		sb.append("					FROM (                                                                          \n");
		sb.append("						SELECT *                                                                    \n");
		sb.append("						FROM board                                                                  \n");
		sb.append("						--WHERE 議곌굔                                                                 \n");
		//----where-----------------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		
		//----where-----------------------------------------------------------------------------------------------------
		sb.append("						ORDER BY mod_dt DESC                                                        \n");
		sb.append("						                                                                            \n");
		sb.append("				)T1                                                                                 \n");
//		sb.append("				WHERE ROWNUM <= ( :pageSize * (:pageNo -1)+:pageSize) 			                    \n");
		sb.append("				WHERE ROWNUM <= ( ? * (? -1) + ?)							                        \n");
		sb.append("		 )TT1                                                                                       \n");
//		sb.append("		WHERE rnum >= ( :pageSize * (:pageNo -1)+1)                                   			    \n");
		sb.append("		WHERE rnum >= ( ? * ( ? -1) +1)              			   								    \n");
		sb.append("	)A,(                                                                                            \n");
		sb.append("		SELECT COUNT (*) totalCount                                                                 \n");
		sb.append("	    FROM board                                                                                  \n");
		//----where-----------------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		
		//----where-----------------------------------------------------------------------------------------------------
		sb.append("	)B                                                                                              \n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", search);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			//param �꽕�젙
			//paging 泥섎━
			
			if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				//寃��깋�뼱
				pstmt.setString(1,  searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				//寃��깋�뼱
				pstmt.setString(7,  searchVO.getSearchWord());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
				log.debug("4.2 searchDiv : {}", searchVO.getSearchDiv());
				//寃��깋�뼱
				pstmt.setString(1,  searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				//寃��깋�뼱
				pstmt.setString(7,  searchVO.getSearchWord());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
				log.debug("4.3 searchDiv : {}", searchVO.getSearchDiv());
				//寃��깋�뼱
				pstmt.setString(1,  searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				//寃��깋�뼱
				pstmt.setString(7,  searchVO.getSearchWord());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
				log.debug("4.4 searchDiv : {}", searchVO.getSearchDiv());
				//寃��깋�뼱
				pstmt.setString(1,  searchVO.getSearchWord());
				pstmt.setString(2,  searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				
				//寃��깋�뼱
				pstmt.setString(8,  searchVO.getSearchWord());
				pstmt.setString(9,  searchVO.getSearchWord());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
				log.debug("4.3 searchDiv : {}", searchVO.getSearchDiv());
				//寃��깋�뼱
				pstmt.setString(1,  searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
				
				//寃��깋�뼱
				pstmt.setString(7,  searchVO.getSearchWord());
			}else {
				//ROWNUM
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());				
			}
			
			//select �떎�뻾
			rs = pstmt.executeQuery(); 
			log.debug("5. rs : {}", rs);
			
			while(rs.next()) {
				BoardDTO outVO = new BoardDTO();
				
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDate(rs.getString("mod_dt"));
				
				outVO.setTotalCnt(rs.getInt("totalCount"));
				
				list.add(outVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/*
	 * ���옣
	 * doSave()
	 * @return int
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	@Override
	public int doSave(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; //SQL�뿉 �뙆�씪硫뷀꽣 �떞�뒗 �뿭�븷
		
		StringBuilder sb = new StringBuilder(); //荑쇰━ �떞�쓣 �뒪�듃留곷퉴�뜑
		sb.append("INSERT INTO board (	\n");		
		sb.append("    seq,			    \n");
		sb.append("    title,			\n");
		sb.append("    contents,		\n");	
		sb.append("    read_cnt,		\n");	
		sb.append("    reg_id,			\n");
		sb.append("    reg_dt,		\n");	
		sb.append("    mod_id,			\n");
		sb.append("    mod_dt			\n");
		sb.append(") VALUES (			\n");
		sb.append("    ?,			    \n");
		sb.append("    ?,			    \n");
		sb.append("    ?,			    \n");
		sb.append("    0,			    \n");
		sb.append("    ?,			    \n");
		sb.append("    SYSDATE,			\n");
		sb.append("    ?,			    \n");
		sb.append("    SYSDATE			\n");
		sb.append(")			        \n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString()); //荑쇰━瑜� �꽆寃⑥빞 �맂�떎.
			//prepareStatement = SQL inject �씠�뒋濡� �궗�슜�븯吏� �븡�뒗�떎.
			//prepareCall�쓣 ���떊 �궗�슜�븳�떎.
			log.debug("4. pstmt : {}", pstmt);
			
			//param �꽕�젙
			pstmt.setInt(1, param.getSeq());
			pstmt.setString(2, param.getTitle());
			pstmt.setString(3, param.getContents());
			pstmt.setString(4, param.getregId());
			pstmt.setString(5, param.getmodId());
			
			//DML �닔�뻾
			flag = pstmt.executeUpdate(); //諛섏쁺 嫄댁닔 由ы꽩
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		log.debug("6. flag : {}", flag);
		
		return flag;
	}
	
	/*
	 * 議고쉶 移댁슫�듃 利앷�
	 * doUpdateReadCnt()
	 * @return int
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public int doUpdateReadCnt(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append(" UPDATE board							\n");
		sb.append("     SET read_cnt = NVL(read_cnt, 0) +1  \n");
		sb.append(" WHERE seq = ?                           \n");
		sb.append("     AND NOT EXISTS                      \n");
		sb.append("     (                                   \n");
		sb.append("         SELECT 1 FROM board             \n");
		sb.append("         WHERE seq = ?                   \n");
		sb.append("         AND reg_id = ?                  \n");
		sb.append("      )                                  \n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString()); //荑쇰━ �궇由ш린
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			pstmt.setInt(2, param.getSeq());
			pstmt.setString(3, param.getregId());
			
			flag = pstmt.executeUpdate(); //諛섏쁺 嫄댁닔 由ы꽩
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		log.debug("6. flag : {}", flag);
		return flag;
	}
	/*
	 * �뾽�뜲�씠�듃
	 * doUpdate()
	 * @return int
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	@Override
	public int doUpdate(BoardDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE board				\n");
		sb.append("SET                      \n");
		sb.append("    title = ?,		    \n");
		sb.append("    contents = ?, 		\n");
		sb.append("    mod_id = ?,			\n");
		sb.append("    mod_dt = sysdate   	\n");
		sb.append("WHERE                    \n");
		sb.append("        seq = ?	        \n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContents());
			pstmt.setString(3, param.getmodId());
			pstmt.setInt(4, param.getSeq());
			
			//DML �닔�뻾
			flag = pstmt.executeUpdate(); //諛섏쁺 嫄댁닔 由ы꽩
			
		}catch(SQLException e){
			e.printStackTrace();			
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		log.debug("6. flag : {}", flag);
		return flag;
	}
	
	/*
	 * �궘�젣
	 * doDelete()
	 * @return int
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	@Override
	public int doDelete(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM board 	\n");
		sb.append("WHERE seq = ?     	\n");
		
		log.debug("1. SQL : {}", sb.toString());
		log.debug("2. Conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			
			flag = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5. finally : conn : {} pstmt : {}", conn, pstmt);
		}
		
		log.debug("6. flag : {}", flag);
		return flag;
	}

	/*
	 * �떒嫄� 議고쉶
	 * doSelectOne()
	 * @return BoardDTO
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	@Override
	public BoardDTO doSelectOne(BoardDTO param) {
		BoardDTO outVO = null;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet�� ���냼臾몄옄瑜� 援щ텇�븯吏� �븡�뒗�떎.
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT												\n");
		sb.append("     seq,                                            \n");
		sb.append("     title,                                          \n");
		sb.append("     contents,                                       \n");
		sb.append("     read_cnt,                                       \n");
		sb.append("     reg_id,                                         \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt, \n");
		sb.append("     mod_id,                                         \n");
		sb.append("     TO_CHAR(mod_dt,'YYYY/MM/DD HH24:MI:SS') mod_dt  \n");
		sb.append(" FROM                                                \n");
		sb.append("     board                                           \n");
		sb.append(" WHERE  seq = ?                                      \n");
		
		log.debug("1.sql : {} \n " + sb.toString());
		log.debug("2.conn : {} ", conn);
		log.debug("3.param : {}", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt : {} ", pstmt);
			
			pstmt.setInt(1,param.getSeq());
			rs = pstmt.executeQuery();
			log.debug("5. rs : " + rs);
			
			if(rs.next()) {
				outVO = new BoardDTO();
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDate(rs.getString("reg_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDate(rs.getString("mod_dt"));
				log.debug("6.outVO:"+outVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		return outVO;
	}
	

}
