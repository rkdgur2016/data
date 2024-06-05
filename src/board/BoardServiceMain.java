package board;

import cmn.PLog;

public class BoardServiceMain implements PLog{
	
	BoardService service;
	BoardDTO board01;
	
	public BoardServiceMain() {
		service = new BoardService();
		
		board01 = new BoardDTO(40, "�젣紐�_40","�궡�슜_40",0,"admin_40","�궗�슜�븞�븿","admin_40","�궗�슜�븞�븿");
	}
	
	public void selectOneReadCnt() {
		log.debug("selectOneReadCnt");
		
		//蹂몄씤 湲��쓣 蹂몄씤�씠 �씫�뿀�쓣 �븣�뒗 議고쉶 移댁슫�듃媛� 諛붾�뚯� �븡�뒗�떎.
		board01.setRegId(board01.getregId() + "00000");
		
		BoardDTO dto = service.selectOneReadCnt(board01);
		if(null != dto && dto.getFlag() == 1) {
			log.debug("議고쉶 諛� readCount 泥섎━ �꽦怨�");
		}else {
			log.debug("議고쉶 諛� readCount 泥섎━ �떎�뙣");
		}
	}
	
	public static void main(String[] args) {
		BoardServiceMain SM = new BoardServiceMain();
		SM.selectOneReadCnt();
	}
}
