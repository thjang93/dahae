package goods;
import java.sql.Timestamp;

public class GoodsDataBean {
	private int goods_number;
	private String goods_name;
	private String category_name;
	private String goods_info;
	private String goods_image;
	private String goods_images;
	private int price;
	private String goods_specimg1;
	private String goods_specimg2;
	private String goods_specimg3;
	private String goods_specimg1s;
	private String goods_specimg2s;
	private String goods_specimg3s;
	private Timestamp goods_reg_date;
	private int sell_count;
	public int getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
	public Timestamp getGoods_reg_date() {
		return goods_reg_date;
	}
	public void setGoods_reg_date(Timestamp goods_reg_date) {
		this.goods_reg_date = goods_reg_date;
	}
	public int getSell_count() {
		return sell_count;
	}
	public void setSell_count(int sell_count) {
		this.sell_count = sell_count;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public String getGoods_images() {
		return goods_images;
	}
	public void setGoods_images(String goods_images) {
		this.goods_images = goods_images;
	}
	public String getGoods_specimg1() {
		return goods_specimg1;
	}
	public void setGoods_specimg1(String goods_specimg1) {
		this.goods_specimg1 = goods_specimg1;
	}
	public String getGoods_specimg2() {
		return goods_specimg2;
	}
	public void setGoods_specimg2(String goods_specimg2) {
		this.goods_specimg2 = goods_specimg2;
	}
	public String getGoods_specimg3() {
		return goods_specimg3;
	}
	public void setGoods_specimg3(String goods_specimg3) {
		this.goods_specimg3 = goods_specimg3;
	}
	public String getGoods_specimg1s() {
		return goods_specimg1s;
	}
	public void setGoods_specimg1s(String goods_specimg1s) {
		this.goods_specimg1s = goods_specimg1s;
	}
	public String getGoods_specimg2s() {
		return goods_specimg2s;
	}
	public void setGoods_specimg2s(String goods_specimg2s) {
		this.goods_specimg2s = goods_specimg2s;
	}
	public String getGoods_specimg3s() {
		return goods_specimg3s;
	}
	public void setGoods_specimg3s(String goods_specimg3s) {
		this.goods_specimg3s = goods_specimg3s;
	}
	
}
