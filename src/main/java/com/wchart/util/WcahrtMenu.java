package com.wchart.util;

import java.util.List;

public class WcahrtMenu {
	int id;
	String name;
	String type;
	String key;
	String url;
	String pid;
	List<WcahrtMenu> sub_button;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<WcahrtMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WcahrtMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
}
