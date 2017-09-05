package novel.spider.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

public class NovelSpiderHttpGet extends HttpGet {

	public NovelSpiderHttpGet() {
	}

	public NovelSpiderHttpGet(URI uri) {
		super(uri);
	}

	public NovelSpiderHttpGet(String uri) {
		super(uri);
	}
	private void setDefaulConfig() {
		this.setConfig(RequestConfig.custom()
				.setConnectTimeout(10000)
				.setConnectionRequestTimeout(10_000)
				.build());
	}

}
