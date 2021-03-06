package novel.spider.configuration;

import java.io.Serializable;

public class Configuration implements Serializable {
	private static final int DEFAULT_SIZE = 100;
	private static final int DEFAULT_TRYTIMES = 3;
	private String path;
	private int tryTimes;
	public int getTryTimes() {
		return tryTimes;
	}
	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
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
		this.tryTimes = DEFAULT_TRYTIMES;
	}
}
