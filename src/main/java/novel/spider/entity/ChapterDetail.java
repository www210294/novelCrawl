package novel.spider.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class ChapterDetail implements Serializable{
	private String title;
	private String content;
	private String pre;
	private String next;
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
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "ChapterDetail[title: "+title+" content: "+StringUtils.abbreviate(content, 30)+" previous: "+pre+" next: ]" + next;
	}
	
}
