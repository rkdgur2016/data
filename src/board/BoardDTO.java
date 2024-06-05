package board;

import cmn.DTO;

public class BoardDTO extends DTO{
	
	//硫ㅻ쾭 蹂��닔
	private int	seq;
	private String title;
	private String contents;
	private int readCnt;
	private String regId;
	private String regDate;
	private String modId;
	private String modDate;
	
	//湲곕낯 �깮�꽦�옄
	public BoardDTO() {
		super();
	}

	//�뙆�씪硫뷀꽣 �깮�꽦�옄
	public BoardDTO(int seq, String title, String contents, int readCnt, String regId, String regDate, String modId,
			String modDate) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.readCnt = readCnt;
		this.regId = regId;
		this.regDate = regDate;
		this.modId = modId;
		this.modDate = modDate;
	}
	
	// Seq @Getter, @Setter
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	// title @Getter, @Setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// contents @Getter, @Setter
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	// read_cnt @Getter, @Setter
	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	// regId @Getter, @Setter
	public String getregId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	// reg_date @Getter, @Setter
	public String getregDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	// mod_id @Getter, @Setter
	public String getmodId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	// mod_date @Getter, @Setter
	public String getmodDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	//toString
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", readCnt=" + readCnt
				+ ", regId=" + regId + ", regDate=" + regDate + ", modId=" + modId + ", modDate=" + modDate
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
