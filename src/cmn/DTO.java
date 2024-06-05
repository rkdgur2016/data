package	cmn;

public class DTO {
	
	private int flag; //DML�옉�꽦 �긽�깭 媛�
	private int num ; //湲�踰덊샇
	private int totalCnt; //珥앷��닔
	
	//湲곕낯 �깮�꽦�옄
	public DTO() {
		
	}
	
	/*
	 * @Getter
	 * @Setter
	 * */
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	//toString
	@Override
	public String toString() {
		return "DTO [flag=" + flag + ", num=" + num + ", totalCnt=" + totalCnt + "]";
	}

}
