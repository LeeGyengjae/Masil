package masil.res_table;

import java.sql.Date;

public class Res_tableVO {
	
	/*
	 * idx : Res_table ���̺� �ε��� ��ȣ
	 * id : User ���̺� ���̵�
	 * code : Product ���̺� ��ǰ �ڵ�
	 */
	private int idx;
	private String id;
	private String code;
	
	//�⺻ ������
	public Res_tableVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "Res_tableVO class [idx: "+idx+", id: "+id+", code: "+code+"]";
	}//toString() Override
	
	/* getter & setter */
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
