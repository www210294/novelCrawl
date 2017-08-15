package novel.spider.impl;

import java.util.*;

import javax.management.RuntimeErrorException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entity.*;
import novel.spider.interfaces.*;

public class AbstractChapterSpider implements IChapterSpider {
	protected String crawl(String url) throws Exception {
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet(url));	) {
			return EntityUtils.toString(closeableHttpResponse.getEntity());
		}catch (Exception e) {
			return "ץȡʧ�ܣ�";
		}
	}

	@Override
	public List<Chapter> getChapter(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			Elements as = doc.select("#list dd a");
			List<Chapter> chapters = new ArrayList<Chapter>();
			for(Element e : as) {
				//System.out.println(e);
				Chapter chapter = new Chapter();
				chapter.setTitle(e.text());
				chapter.setUrl("http://www.xs.la" + e.attr("href"));
				chapters.add(chapter);
			}
			return chapters;
			//System.out.println(result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	} 
}
