package novel.spider.impl;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider{
	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result = super.crawl(url);
			//.replace("<br />", "\n").replace("<br/>", "\n")
			Document doc = Jsoup.parse(result.replace("&nbsp;", "  ").replace("<br />", "HUANHANG").replace("<br/>", "HUANHANG"));
			doc.setBaseUri(url);
			Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			
			String titleSelector = contexts.get("chapter_detail_title_selector");
			String[] splits = titleSelector.split("\\,");
			splits = parseSelector(splits);
			ChapterDetail detail = new ChapterDetail();
			detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			String contentSelector = contexts.get("chapter_datail_content_selector");
			detail.setContent(doc.select(contentSelector).first().text().replace("HUANHANG", "\n"));
			
			String preSelector = contexts.get("chapter_datail_prev_selector");
			splits = preSelector.split("\\,");
			splits = parseSelector(splits);
			detail.setPre(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			String nextSelector = contexts.get("chapter_datail_next_selector");
			splits = nextSelector.split("\\,");
			splits = parseSelector(splits);
			detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			return detail;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		if(splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		} else {
			return splits;
		}
	}
}
