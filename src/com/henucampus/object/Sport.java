package com.henucampus.object;

import cn.bmob.v3.BmobObject;

public class Sport extends BmobObject{
	private String title;
	private Object instruction;
	private String from;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Object getInstruction() {
		return instruction;
	}
	public void setInstruction(Object instruction) {
		this.instruction = instruction;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
}
