package emp;

import cmn.PLog;

public class DeptDaoMain implements PLog{
	
	DeptDao dao;
	DeptDTO search;  
	
	public DeptDaoMain() {
		dao = new DeptDao();
		search = new DeptDTO();
		
	}
	public void deSelectOne() {
		log.debug("doSelectOne()");
		search.setDeptno(20);
		
		DeptDTO outVO = dao.doSelectOne(search);
		
		if(null !=outVO) {
			log.debug("�떒嫄� 議고쉶 �꽦怨�"+outVO);
		}else {
			log.debug("�떒嫄� 議고쉶 �떎�뙣"+outVO);
		}
		
	}

	public static void main(String[] args) { 
		
		DeptDaoMain deptMain = new DeptDaoMain();
		deptMain.deSelectOne();

	}

}
