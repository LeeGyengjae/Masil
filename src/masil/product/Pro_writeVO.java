package masil.product;

import java.sql.Date;

public class Pro_writeVO {
	
	/* 
	 * pro_code : ���� ��ǰ(��Ű��) �ڵ�
	 * sub_code : ���� ��ǰ(��Ű��) �����ڵ�
	 * title : ��ǰ ����
	 * start_date : �������
	 * end_date : ��������
	 * max_num : �ִ� ���� �ο�
	 * curr_num : ���� ���� �ο�
	 * price : ����
	*/
	private String pro_code;
	private String sub_code;
	private String title;
	private Date start_date;
	private Date end_date;
	private int max_num;
	private int curr_num;
	private int price;
	
	//�⺻ ������
	public Pro_writeVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "Pro_detailVO class [pro_code: "+pro_code+", sub_code: "+sub_code
				+", title: "+title+", start_date: "+start_date
				+", end_date: "+end_date+", max_num: "+max_num
				+", curr_num: "+curr_num+", price: "+price+"]";
	}//toString() Override

	/* getter & setter */
	public String getPro_code() {
		return pro_code;
	}

	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}

	public String getSub_code() {
		return sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
