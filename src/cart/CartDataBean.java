package cart;

import java.sql.Timestamp;

public class CartDataBean {
	private int cart_number;
	private int goods_number;
	private String id;
	private int goods_count;
	private Timestamp get_date;
	private String goods_images;
	private String goods_name;
	private String goods_info;
	private int price;
	
	public String getGoods_images() {
		return goods_images;
	}
	public void setGoods_images(String goods_images) {
		this.goods_images = goods_images;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_info() {
		return goods_info;
	}
	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCart_number() {
		return cart_number;
	}
	public void setCart_number(int cart_number) {
		this.cart_number = cart_number;
	}
	public int getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}
	public Timestamp getGet_date() {
		return get_date;
	}
	public void setGet_date(Timestamp get_date) {
		this.get_date = get_date;
	}
	
}
