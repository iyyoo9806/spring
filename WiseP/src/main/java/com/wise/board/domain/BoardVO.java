package com.wise.board.domain;

import java.util.Date;

public class BoardVO {
	private String idx;
	private String board_code;
	private String id;
	private String name;
	private String title;
	private String contents;
	private Date crdTime;
	private Date updTime;
	private Date delTime;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCrdTime() {
		return crdTime;
	}
	public void setCrdTime(Date crdTime) {
		this.crdTime = crdTime;
	}
	public Date getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
}
