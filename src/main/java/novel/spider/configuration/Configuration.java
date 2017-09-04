package novel.spider.configuration;

import java.io.Serializable;
/*
 * 配置下载线程数量
 */
public class Configuration implements Serializable {
	private static final int DEFAULT_SIZE = 100;
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	private int size;
	public Configuration() {
		this.size = DEFAULT_SIZE;
	}
}
