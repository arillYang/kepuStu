package com.kepu.pojo;

public class PictrueResult {
	private int error;
	private String url;
	private String message;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private PictrueResult(int error, String url, String message) {
		super();
		this.error = error;
		this.url = url;
		this.message = message;
	}
	public static PictrueResult ok(String url){
		return new PictrueResult(0, url, null);
	}
	
	public static PictrueResult error(String message){
		return new PictrueResult(1, null, message);
	}
}
