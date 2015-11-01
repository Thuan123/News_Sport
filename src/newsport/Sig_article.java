package newsport;

import java.sql.Date;

public class Sig_article {
     private int id;
     private String url;
     private String title;
     private String abstract_content;
     private String text_content;
     private String extracted_content;
     private String html_content;
     private String cover_url;
     private Date published; 
     private Date crawled;
     
     /*
      * 
      * Phuong thuc khoi tao khong tham so
      * 
      */
    public Sig_article() {
		
	}
    /*
     * 
     * Phuong thuc khoi tao kem tham so
     * 
     */
	public Sig_article(int id, String url, String title,
			String abstract_content, String text_content,
			String extracted_content, String html_content, String cover_url,
			Date published2,Date crawled2, int wid, int cid, String meta) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.abstract_content = abstract_content;
		this.text_content = text_content;
		this.extracted_content = extracted_content;
		this.html_content = html_content;
		this.cover_url = cover_url;
		this.published = published2;
		this.crawled = crawled2;
		this.wid = wid;
		this.cid = cid;
		this.meta = meta;
	}
	private int wid;
     private int cid;
     private String meta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstract_content() {
		return abstract_content;
	}
	public void setAbstract_content(String abstract_content) {
		this.abstract_content = abstract_content;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public String getExtracted_content() {
		return extracted_content;
	}
	public void setExtracted_content(String extracted_content) {
		this.extracted_content = extracted_content;
	}
	public String getHtml_content() {
		return html_content;
	}
	public void setHtml_content(String html_content) {
		this.html_content = html_content;
	}
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public Date getCrawled() {
		return crawled;
	}
	public void setCrawled(Date crawled) {
		this.crawled = crawled;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
     
}
