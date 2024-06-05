package answer;

import java.util.List;


import cmn.DTO;

public class AnswerService {
	
	private AnswerDAO dao;
	
	public AnswerService() {
		dao = new AnswerDAO();
	}
	
	public List<AnswerDTO> doRetrieve(DTO search) {
		return null;
	}

	public int doSave(AnswerDTO param) {
		return 0;
	}

	public int doUpdate(AnswerDTO param) {
		return 0;
	}

	public int doDelete(AnswerDTO param) {
		return 0;
	}

	public AnswerDTO doSelectOne(AnswerDTO param) {
		return null;
	}

}
