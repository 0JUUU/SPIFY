package com.ssafy.house.model.dto;

public class QnaDto {
	private int no;
	private String title;
	private String writer;
	private String writerName;
	private String content;
	private String regDate;

	public QnaDto() {
		super();
	}

	public QnaDto(int no, String title, String writer, String writerName, String content, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.writerName = writerName;
		this.content = content;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "QnaDto [no=" + no + ", title=" + title + ", writer=" + writer + ", writerName=" + writerName
				+ ", content=" + content + ", regDate=" + regDate + "]";
	}

}
