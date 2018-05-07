package com.kepu.pojo.news;

public class NewsTemp {
	private String title;
	private String newsImages;
	private String newsAuthor;
	private String newsStyle;
	private String updateTime;
	private String view;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsImages() {
		return newsImages;
	}
	public void setNewsImages(String newsImages) {
		this.newsImages = newsImages;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getNewsStyle() {
		return newsStyle;
	}
	public void setNewsStyle(String newsStyle) {
		this.newsStyle = newsStyle;
	}
	public NewsTemp(String title, String newsImages, String newsAuthor,
			String newsStyle, String updateTime, String view) {
		super();
		this.title = title;
		this.newsImages = newsImages;
		this.newsAuthor = newsAuthor;
		this.newsStyle = newsStyle;
		this.updateTime = updateTime;
		this.view = view;
	}
	public NewsTemp() {
		super();
	}
	
}
