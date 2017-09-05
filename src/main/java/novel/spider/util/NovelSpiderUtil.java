package novel.spider.util;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	public static void mergeFiles(String path, String mergeToFile, boolean delete) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files, new Comparator<File>(){
					@Override
					public int compare(File o1, File o2) {
						int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
						int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
						return o1Index - o2Index;
					}
				});
		PrintWriter out = null;
		BufferedReader bufr = null;
		try {
			out = new PrintWriter(new File(mergeToFile));
			for(File file : files) {
				bufr = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));
				String line = null;
				while((line = bufr.readLine()) != null) {
					out.println(line);
				}
				bufr.close();
				if(delete) {
					file.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bufr != null) {
				try {
					bufr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(out != null) {
				out.close();
			}
		}
	}
}
