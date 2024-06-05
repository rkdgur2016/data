package cmn;
import java.util.List;

public interface WorkDiv<T> extends PLog {
/*
 *   +-+-+-+-+ +-+-+-+-+ +-+-+-+-+-+-+-+
     |M|i|n|i| |J|a|v|a| |P|r|o|j|e|c|t|
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |2|0|2|4|.|0|4|.|2|4|              
     +-+-+-+-+-+-+-+-+-+-+
 */
/*
 * *** �쉶�썝 愿�由� �봽濡쒓렇�옩 ***
 * 1. �쉶�썝 紐⑸줉 議고쉶
 * 2. �쉶�썝 �떒嫄� 議고쉶
 * 3. �쉶�썝 �떒嫄� ���옣
 * 4. �쉶�썝 �떒嫄� �닔�젙
 * 5. �쉶�썝  �궘�젣
 * 6. �봽濡쒓렇�옩 醫낅즺
 */
	/**
	 * 紐⑸줉 議고쉶
	 * 
	 * @param search
	 * @return List<T>
	 */
	public List<T> doRetrieve(DTO search);

	/**
	 * �떒嫄� ���옣
	 * 
	 * @param param
	 * @return �꽦怨�(1)/�떎�뙣(0)
	 */
	public int doSave(T param);

	/**
	 * �떒嫄� �닔�젙
	 * 
	 * @param param
	 * @return �꽦怨�(1)/�떎�뙣(0)
	 */
	public int doUpdate(T param);

	/**
	 * �떒嫄� �궘�젣
	 * 
	 * @param param
	 * @return �꽦怨�(1)/�떎�뙣(0)
	 */
	public int doDelete(T param);

	/**
	 * �떒嫄� 議고쉶
	 * 
	 * @param param
	 * @return T
	 */
	public T doSelectOne(T param);



}