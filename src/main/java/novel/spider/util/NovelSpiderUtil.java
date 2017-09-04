package novel.spider.util;

import java.util.HashMap;
import java.io.File;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public final class NovelSpiderUtil {
	
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();
	static {
		init();
	}
	private NovelSpiderUtil() {}
	
	private static void init() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File("config/Spider_Rule.xml"));
			Element root = doc.getRootElement();
			List<Element> sites = root.elements("site");
			for(Element site : sites) {
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for(Element sub : subs) {
					String name = sub.getName();
					String text = sub.getTextTrim();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
		return CONTEXT_MAP.get(novelSiteEnum);
	} 
}
