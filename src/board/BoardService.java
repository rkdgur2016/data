package board;

import java.util.List;


import cmn.DTO;
import cmn.PLog;

public class BoardService implements PLog{
	
	private BoardDAO dao;

	public BoardService() {
		dao = new BoardDAO();
	}
	
	/*
	 * 紐⑸줉 議고쉶
	 * @param search
	 * @return List<BoardDTO>
	 * */
	public List<BoardDTO> doRetrieve(DTO search) {
		
		return dao.doRetrieve(search);
	}
	
	/**
	 * ���옣
	 * @param param
	 * @return BoardDTO
	 */
	public int doSave(BoardDTO param) {
		
		return dao.doSave(param);
	}
	
	/**
	 * �뾽�뜲�씠�듃
	 * @param param
	 * @return BoardDTO
	 */
	public int doUpdate(BoardDTO param) {
		
		return dao.doUpdate(param);
	}
	
	/**
	 * �궘�젣
	 * @param param
	 * @return BoardDTO
	 */
	public int doDelete(BoardDTO param) {
		
		return dao.doDelete(param);
	}
	/**
	 * �떒嫄� 議고쉶
	 * @param param
	 * @return BoardDTO
	 */
	public BoardDTO selectOneReadCnt(BoardDTO param) {
		BoardDTO outVO = null;
		
		//doSelectOne
		outVO = dao.doSelectOne(param);
		
		//議고쉶 �꽦怨듭떆 updateReadCnt �샇異�
		if(null != outVO) {
			int flag = dao.doUpdateReadCnt(param);
			log.debug("flag : {}", flag);
			
			outVO.setFlag(flag);
		}
		return outVO;
	}
}
