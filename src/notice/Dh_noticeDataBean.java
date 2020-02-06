package notice;

import java.sql.Timestamp;

public class Dh_noticeDataBean {
	private int n_number;				
	private String n_subject;						
	private String n_content;			
	private int n_readcount;			
	private Timestamp n_reg_date;		
	private int n_imp_level;
	private String id;
	public int getN_number() {
		return n_number;
	}
	public void setN_number(int n_number) {
		this.n_number = n_number;
	}
	public String getN_subject() {
		return n_subject;
	}
	public void setN_subject(String n_subject) {
		this.n_subject = n_subject;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public int getN_readcount() {
		return n_readcount;
	}
	public void setN_readcount(int n_readcount) {
		this.n_readcount = n_readcount;
	}
	public Timestamp getN_reg_date() {
		return n_reg_date;
	}
	public void setN_reg_date(Timestamp n_reg_date) {
		this.n_reg_date = n_reg_date;
	}
	public int getN_imp_level() {
		return n_imp_level;
	}
	public void setN_imp_level(int n_imp_level) {
		this.n_imp_level = n_imp_level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
