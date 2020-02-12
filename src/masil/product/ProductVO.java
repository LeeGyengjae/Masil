package masil.product;

import java.sql.Date;

public class ProductVO {
	
	/*
	 * code : ���� ��ǰ(��Ű��) �ڵ�
	 * region : ���� ���� 
	 * start_date : ��Ű�� ���� ��¥
	 * end_date : ��Ű�� ���� ��¥
	 * max_num : ��Ű�� ���� �� �ο���
	 * curr_num : ��Ű�� ���� ���� �ο���
	 */
	private String code;
	private String region;
	private Date start_date;
	private Date end_date;
	private int max_num;
	private int curr_num;
	
	//�⺻ ������
	public ProductVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "ProductVO class [code: "+code+", region: "+region
				+", start_date: "+start_date+", end_date: "+end_date
				+", max_num: "+max_num+", curr_num: "+curr_num+"]";
	}//toString() Override
	
	/* getter & setter */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

	public int getCurr_num() {
		return curr_num;
	}

	public void setCurr_num(int curr_num) {
		this.curr_num = curr_num;
	}
	
	
}
