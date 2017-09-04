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
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	

	@Override
	public List<Chapter> getChapter(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapterList_selector"));
			List<Chapter> chapters = new ArrayList<Chapter>();
			for(Element e : as) {
				//System.out.println(e);
				Chapter chapter = new Chapter();
				chapter.setTitle(e.text());
				//chapter.setUrl("http://www.xs.la" + e.attr("href"));
				chapter.setUrl(e.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
			//System.out.println(result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	} 
}
