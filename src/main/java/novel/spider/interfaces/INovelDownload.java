package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownload {
	/*
	 * ��url���������أ����������ص��ļ�·��
	 */
	public String download(String url, Configuration conf);
}
