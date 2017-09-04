package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownload {
	/*
	 * 将url的内容下载，并返回下载的文件路径
	 */
	public String download(String url, Configuration conf);
}
