package novel.spider.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import novel.spider.configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
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
		Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			int fromIndex = i * size;
			int toIndex = i == maxThreadSize - 1 ? chapters.size()
					: i*size + size - 1;
			downloadTaskAlloc.put(fromIndex + "-" + toIndex, 
					chapters.subList(fromIndex, toIndex));
		}
		ExecutorService service=  Executors.newFixedThreadPool(maxThreadSize);
		
		Set<String> keySet = downloadTaskAlloc.keySet();
		List<Future<String>> tasks = new ArrayList<>();
		for(String key : keySet) {
			tasks.add(service.submit(new DownloadCallable(downloadTaskAlloc.get(key), configuration.getPath()+"/" + key +".txt")));
		}
		
		for(Future<String> future : tasks) {
			try {
				System.out.println(future.get() + " success!");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		service.shutdown();
		return null;
	}
}
class DownloadCallable implements Callable<String> {
	private List<Chapter> chapters;
	private String path;
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public DownloadCallable(List<Chapter> chapters, String path) {
		this.chapters = chapters;
		this.path = path;
	}
	public String call() throws Exception {
		try( 
				PrintWriter out = new PrintWriter(new File(path));
				) {
			for(Chapter chapter : chapters) {
				IChapterDetailSpider spider = new DefaultChapterDetailSpider();
				ChapterDetail detail = spider.getChapterDetail(chapter.getUrl());
				out.println(detail.getTitle());
				out.println(detail.getContent());
			}
			
		}catch(IOException e) {
			throw new RuntimeException();
		}
		return path;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}