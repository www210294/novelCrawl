package novel.spider.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractSpider {
	protected String crawl(String url) throws Exception {
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse closeableHttpResponse = httpClient.execute(new NovelSpiderHttpGet(url));	) {
			return EntityUtils.toString(closeableHttpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
		}catch (Exception e) {
			return "ץȡʧ�ܣ�";
		}
	}
}
