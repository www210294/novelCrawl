package novel.spider.interfaces;

import java.util.*;
import novel.spider.entity.*;

public interface IChapterSpider {
	public List<Chapter> getChapter(String url);
}
