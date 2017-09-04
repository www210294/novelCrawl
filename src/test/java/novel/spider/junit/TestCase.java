package novel.spider.junit;

import java.util.List;

import org.junit.*;

import novel.spider.entity.Chapter;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public class TestCase {
	@Test
	public void test1() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		//List<Chapter> chapters = spider.getChapter("http://www.xs.la/0_5/");
		List<Chapter> chapters = spider.getChapter("http://2bgif.com/chapters/375");
		for(Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	
	@Test
	public void testGetSite() {
		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.xs.la")));
	}
	
	@Test
	public void testGetChapterDetail() {
		IChapterDetailSpider chapterDetailSpider = new DefaultChapterDetailSpider();
		System.out.println(chapterDetailSpider.getChapterDetail("http://www.xs.la/0_5/1408.html"));
	}


}
