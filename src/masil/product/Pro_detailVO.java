package masil.product;

public class Pro_detailVO {
	
	/* 
	 * code : ���� ��ǰ(��Ű��) �ڵ�
	 * day : n����
	 * day_title : n���� ���� ������
	 * day_course : n���� �湮 ����(����)
	 * stay : n���� ����
	 * meal : n���� �Ļ�
	 * day_content : n���� ���� ����
	 * image : �̹��� ���� �̸�
	 * img_content : �̹��� ����
	 * img_boss : ��ǥ �̹��� ���� Ȯ�ο� (default 'N')
	*/
	private String code;
	private String day;
	private String day_title;
	private String day_course;
	private String stay;
	private String meal;
	private String day_content;
	private String[] image;
	private String img_content;
	private String img_boss;
	
	//�⺻ ������
	public Pro_detailVO() {}

	//������ �����ε�
	public Pro_detailVO(String day, String day_title, String day_course, String stay, String meal,
			String day_content, String[] image, String img_content, String img_boss) {
		super();
		this.day = day;
		this.day_title = day_title;
		this.day_course = day_course;
		this.stay = stay;
		this.meal = meal;
		this.day_content = day_content;
		this.image = image;
		this.img_content = img_content;
		this.img_boss = img_boss;
	}
	
	//������ �����ε�
	public Pro_detailVO(String day, String day_title, String day_course, String stay, String meal,
			String day_content, String[] image, String img_content) {
		super();
		this.day = day;
		this.day_title = day_title;
		this.day_course = day_course;
		this.stay = stay;
		this.meal = meal;
		this.day_content = day_content;
		this.image = image;
		this.img_content = img_content;
	}

	//toString() Override
	@Override
	public String toString() {
		return "Pro_detailVO class [code: "+code+", day: "+day
				+", day_title: "+day_title+", day_course: "+day_course
				+", stay: "+stay+", meal: "+meal
				+", day_content: "+day_content+", image: "+image
				+", img_content: "+img_content+", img_boss: "+img_boss+"]";
	}//toString() Override

	/* getter & setter */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getImg_content() {
		return img_content;
	}

	public void setImg_content(String img_content) {
		this.img_content = img_content;
	}

	public String getDay_course() {
		return day_course;
	}

	public void setDay_course(String day_course) {
		this.day_course = day_course;
	}

	public String getDay_title() {
		return day_title;
	}

	public void setDay_title(String day_title) {
		this.day_title = day_title;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getImg_boss() {
		return img_boss;
	}

	public void setImg_boss(String img_boss) {
		this.img_boss = img_boss;
	}

	public String[] getImage() {
		return image;
	}

	public void setImage(String[] image) {
		this.image = image;
	}
	
}
