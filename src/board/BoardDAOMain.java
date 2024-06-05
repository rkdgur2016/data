package board;

import java.util.List;


import cmn.PLog;
import cmn.SearchDTO;

public class BoardDAOMain implements PLog {

	BoardDAO dao;
	BoardDTO board01;
	
	public BoardDAOMain() {
		dao = new BoardDAO();
		board01 = new BoardDTO(40, "임강혁_40","임강혁_40",0,"admin_40","pcwk","admin_40","pcwk");
	}
	
	/*
	 * ���옣
	 * doSave()
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public void doSave() {
		log.debug("doSave()");
		int flag = dao.doSave(board01);
		
		if(flag == 1) {
			
			log.debug("성공 : {}", flag);
		}else {
			log.debug("실패" + flag);
		}
	}
	
	/*
	 * �궘�젣
	 * doDelete()
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public void doDelete() {
		log.debug("doDelete()");
		int flag = dao.doDelete(board01);
		
		if(flag == 1) {
			log.debug("�꽦怨� : {}", flag);
		}else {
			log.debug("�떎�뙣" + flag);
		}
	}
	
	
	/*
	 * 議고쉶
	 * doSelectOne()
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public void doSelectOne() {
		log.debug("doDelete()");
		BoardDTO outVO = dao.doSelectOne(board01);
		
		if(outVO != null) {
			log.debug("�꽦怨� : {} ", outVO);
		}else {
			log.debug("�떎�뙣 : {} ", outVO);
		}
	}

	/*
	 * �뾽�뜲�씠�듃
	 * doUpdate()
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public void doUpdate() {
		log.debug("doUpdate()");
		String updateStr = "_U";
		board01.setTitle(board01.getTitle()+updateStr);
		board01.setContents(board01.getContents()+updateStr);
		board01.setModId(board01.getmodId()+updateStr);
		
		int flag = dao.doUpdate(board01);
		
		if(1 == flag) {
			log.debug("�꽦怨�");
		}else {
			log.debug("�떎�뙣");
		}
	}

	/*
	 * 議고쉶 移댁슫�듃 利앷�
	 * doUpdateReadCnt()
	 * �꽦怨� 1/ �떎�뙣 0 
	 * */
	public void doUpdateReadCnt() {
		log.debug("doUpdateReadCnt()");
		
	}
	
	public void doRetrieve() {
		log.debug("doRetrieve()");
		
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		//寃��깋 援щ텇
		searchVO.setSearchDiv("50");
		searchVO.setSearchWord("10");
		List<BoardDTO> list = dao.doRetrieve(searchVO);
		
		int i = 0;
		for(BoardDTO vo : list) {
			log.debug("i : {}, vo : {}", ++i,vo);
		}
		
	}
	
	public void addAndGet() {
		log.debug("addAndGet()");
		
		//1. �궘�젣 : �꽦怨�,�떎�뙣 �뿬遺� 諛섑솚 X
		dao.doDelete(board01);
	
		//2. �벑濡� : 
		int flag = dao.doSave(board01);
		
		if(1 ==flag) {
			log.debug("�벑濡� �꽦怨� : {} ", flag);
		}else {
			log.debug("�벑濡� �떎�뙣 : {} ", flag);
			return;
		}
		
		//3. 議고쉶 :
		BoardDTO outVO = dao.doSelectOne(board01);
		//outVO.setSeq(9999);
		boolean isSame = isSameData(outVO, board01);
		
		if(isSame == false) {
			log.debug("===================");
			log.debug("	�떎�뙣 ");
			log.debug("===================");
		}else{			
			log.debug("===================");
			log.debug("	�꽦怨� ");
			log.debug("===================");
		}
				
	}
	
	public boolean isSameData(BoardDTO outVO, BoardDTO board01) {
		
		//board01, outVO 鍮꾧탳
		if(outVO.getSeq() != board01.getSeq()) {
			log.debug("�떎�뙣 seq : {} ", outVO.getSeq());
			return false;
		}
		
		//�젣紐�
		if(!outVO.getTitle().equals(board01.getTitle())){
			log.debug("�떎�뙣 title : {},{} ",outVO.getTitle(),board01.getTitle());
			return false;
		}
	
		//�궡�슜
		if(!outVO.getContents().equals(board01.getContents())){
			log.debug("�떎�뙣 Contents : {},{} ",outVO.getContents(),board01.getContents());
			return false;
		}
		
		//�벑濡앹옄 ID
		if(!outVO.getregId().equals(board01.getregId())){
			log.debug("�떎�뙣 Contents : {},{} ",outVO.getregId(),board01.getregId());
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		BoardDAOMain main = new BoardDAOMain();
		main.addAndGet();
		//main.doUpdate();
		//main.doUpdateReadCnt();
		//main.doRetrieve();
	}

}
