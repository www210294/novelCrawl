package novel.spider.junit;

import java.util.List;

import org.junit.*;

import novel.spider.entity.Chapter;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public class TestCase {
	@Test
	public void test1() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getChapter("http://www.xs.la/0_5/");
		for(Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}

}
