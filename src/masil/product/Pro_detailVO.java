package masil.product;

public class Pro_detailVO {
	
	/* 
	 * code : 여행 상품(패키지) 코드
	 * day : n일차
	 * day_title : n일차 일정 소제목
	 * day_course : n일차 방문 지역(도시)
	 * stay : n일차 숙박
	 * meal : n일차 식사
	 * day_content : n일차 일정 설명
	 * image : 이미지 파일 이름
	 * img_content : 이미지 설명
	 * img_boss : 대표 이미지 설정 확인용 (default 'N')
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
	
	//기본 생성자
	public Pro_detailVO() {}

	//생성자 오버로딩
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
	
	//생성자 오버로딩
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
