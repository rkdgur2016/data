package answer;

import cmn.DTO;

public class AnswerDTO extends DTO {
	
	private int seq;
	private String contents;
	private int boardSeq;
	private String regId;
	private String regDt;
	private String modId;
	private String modDt;
	
	/**
	 * 湲곕낯 �깮�꽦�옄
	 */
	public AnswerDTO() {
		super();
	}
	
	/**
	 * �뙆�씪誘명꽣 �깮�꽦�옄
	 */
	public AnswerDTO(int seq, String contents, int boardSeq, String regId, String regDt, String modId, String modDt) {
		super();
		this.seq = seq;
		this.contents = contents;
		this.boardSeq = boardSeq;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	/**
	 * @Getter
	 * @Setter
	 */
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "AnswerDTO [seq=" + seq + ", contents=" + contents + ", boardSeq=" + boardSeq + ", regId=" + regId
				+ ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString()
				+ "]";
	}
	
}
