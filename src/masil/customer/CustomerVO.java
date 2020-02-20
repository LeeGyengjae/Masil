package masil.customer;

import java.sql.Date;

public class CustomerVO {
	
	private int idx;
	private String id;
	private Date write_date;
	private String title;
	private String content;
	private String img;
	
	//�⺻ ������
	public CustomerVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "CustomerVO class [idx: "+idx+", id: "+id
				+", write_date: "+write_date+", title: "+title
				+", content: "+content+", img: "+img+"]";
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
