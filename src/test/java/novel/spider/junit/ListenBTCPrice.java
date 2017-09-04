package novel.spider.junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import novel.spider.entity.Chapter;

public class ListenBTCPrice {
	protected String crawl(String url) throws Exception {
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet(url));	) {
			return EntityUtils.toString(closeableHttpResponse.getEntity());
		}catch (Exception e) {
			return "ץȡʧ�ܣ�";
		}
	}
	@Test
	public void listen() throws Exception{
		int i = 0;
		while(true) {
			String result = crawl("https://www.okcoin.cn/");
			System.out.println(result);
			Document doc = Jsoup.parse(result);
			Elements as = doc.select("#btcLastPrice");
			++i;
			System.out.println(as.text()+" ++++++" +i);
			Thread.sleep(1000);
		}
	}
}
