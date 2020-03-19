package masil.customer;

import java.sql.Date;

public class CustomerVO {
	
	private int idx;
	private int level;
	private int parentNO;
	private String id;
	private Date write_date;
	private String title;
	private String content;
	private String img;
	
	public CustomerVO() {}
	
	public CustomerVO(int idx, int level, int parentNO, String id, Date write_date, String title,
			String content, String img) {
		super();
		this.idx = idx;
		this.level = level;
		this.parentNO = parentNO;
		this.id = id;
		this.write_date = write_date;
		this.title = title;
		this.content = content;
		this.img = img;
	}
	
	//toString() Override
	@Override
	public String toString() {
		return "CustomerVO class [idx: "+idx+", id: "+id
				+", write_date: "+write_date+", title: "+title
				+", content: "+content+", img: "+img+"]";
	}//toString() Override
	
	/* getter & setter */
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
	
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

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
