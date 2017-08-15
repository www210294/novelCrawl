package novel.spider.entity;

import java.io.Serializable;

public class Chapter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String title;
	
	@Override
	public String toString() {
		
		return "title: " + title + "	url: " + url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
