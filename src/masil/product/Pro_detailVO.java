package masil.product;

public class Pro_detailVO {
	
	/*
	 * pro_code : ���� ��ǰ(��Ű��) �ڵ�
	 * day : n����
	 * day_content : n���� ���� ����
	 * image : �̹��� ���� �̸�
	 * img_content : �̹��� ����
	*/
	private String pro_code;
	private String day;
	private String day_content;
	private String image;
	private String img_content;
	
	//�⺻ ������
	public Pro_detailVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "Pro_detailVO class [pro_code: "+pro_code+", day: "+day
				+", day_content: "+day_content+", image: "+image
				+", img_content: "+img_content+"]";
	}//toString() Override

	/* getter & setter */
	public String getPro_code() {
		return pro_code;
	}

	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDay_content() {
		return day_content;
	}

	public void setDay_content(String day_content) {
		this.day_content = day_content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImg_content() {
		return img_content;
	}

	public void setImg_content(String img_content) {
		this.img_content = img_content;
	}
	
}
