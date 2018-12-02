package cn.itcast.vo;

/**
 * 图书的JavaBean
 * @author Administrator
 */
public class Book {
	
	// 图书的主键
	private String bid;
	// 图书的名称
	private String bname;
	// 图书的价格
	private double price;
	// 图书的作者
	private String author;
	// 图书的图片的地址
	private String image;
	// 分类对象的主键
	private String cid;
	// 逻辑删除（0代表未删除，1代表的删除）
	private int isdel;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
}












