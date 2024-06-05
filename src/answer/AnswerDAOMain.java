package answer;

import java.util.List;


import cmn.PLog;
import cmn.SearchDTO;

public class AnswerDAOMain implements PLog{
	
	AnswerDAO dao;
	AnswerDTO sample01;
	
	public AnswerDAOMain() {
		dao = new AnswerDAO();
		sample01 = new AnswerDTO(40,"�엫媛뺥쁺",40,"admin_40","�궗�슜�븞�븿","admin_40","�궗�슜�븞�븿");
	}
	
	public void doSave() {
		log.debug("doSave()");
		int flag = dao.doSave(sample01);
		
		if(flag == 1) {
			
			log.debug("�꽦怨� : {}", flag);
		}else {
			log.debug("�떎�뙣" + flag);
		}
	}
	
	public void doDelete() {
		log.debug("doDelete()");
		int flag = dao.doDelete(sample01);
		
		if(flag == 1) {
			log.debug("�꽦怨� : {}", flag);
		}else {
			log.debug("�떎�뙣" + flag);
		}
	}
	
	public void doUpdate() {
		log.debug("doUpdate()");
		String updateStr = "_Ultra";
		sample01.setContents(sample01.getContents()+updateStr);
		sample01.setModId(sample01.getModId()+updateStr);
		int flag = dao.doUpdate(sample01);
		
		if(1 == flag) {
			log.debug("�꽦怨�");
		}else {
			log.debug("�떎�뙣");
		}
	}
	
	public void doSelectOne() {
		log.debug("doDelete()");
		AnswerDTO outVO = dao.doSelectOne(sample01);
		
		if(outVO != null) {
			log.debug("�꽦怨� : {} ", outVO);
		}else {
			log.debug("�떎�뙣 : {} ", outVO);
		}
	}
	
	public void doRetrieve() {
		log.debug("doRetrieve()");
		
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		//寃��깋 援щ텇
		searchVO.setSearchDiv("10");
		searchVO.setSearchWord("�떟蹂�");
		List<AnswerDTO> list = dao.doRetrieve(searchVO);
		
		int i = 0;
		for(AnswerDTO vo : list) {
			log.debug("i : {}, vo : {}", ++i,vo);
		}
		
	}
	public static void main(String[] args) {
		AnswerDAOMain m = new AnswerDAOMain();
		
		//m.doSave();
		//m.doDelete();
		//m.doUpdate();
		//m.doSelectOne();
		m.doRetrieve();
	}
}
