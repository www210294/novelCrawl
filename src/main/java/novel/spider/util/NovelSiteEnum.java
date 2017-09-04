package novel.spider.util;
import javax.management.RuntimeErrorException;
import javax.print.attribute.standard.RequestingUserName;

public enum NovelSiteEnum {
	DingDiaoXiaoShuo(1, "http://www.23wx.com"),
	BiQuGe(2, "http://www.xs.la"),
	DouBan(3, "http://2bgif.com");
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
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
	public static NovelSiteEnum getEnumById(int id) {
		switch(id) {
		case 1:
			return DingDiaoXiaoShuo;
		case 2:
			return BiQuGe;
		case 3:
			return DouBan;
		default:
			throw new IllegalArgumentException("id： " + id + "尚未支持");
		}
	}
	public static NovelSiteEnum getEnumByUrl(String url) {
		for(NovelSiteEnum nse : values()) {
			if(url.contains(nse.getUrl())) {
				return nse;
			}
		}
		throw new RuntimeException("url： " + url + "尚未支持");
	}
}
