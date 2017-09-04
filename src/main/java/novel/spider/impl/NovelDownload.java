package novel.spider.impl;

import java.util.*;

import novel.spider.configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.NovelSiteEnum;

public class NovelDownload implements INovelDownload {
	@Override
	public String download(String url, Configuration configuration) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = new DefaultChapterSpider();
		List<Chapter> chapters = chapterSpider.getChapter(url);
		int size = configuration.getSize();
		int maxThreadSize = (int)Math.ceil(chapters.size()*1.0 / size);
		Map<String, List<Chapter>> downloadTaskAlloc = new HasMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			int fromIndex
		}
		return null;
	}
}
