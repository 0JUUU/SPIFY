package com.ssafy.house.model.dto;

public class MemberDto {

	private String email;
	private String username;
	private String userpwd;
	private String phoneno;
	private String joindate;

	public MemberDto() {
		super();
	}

	public MemberDto(String email, String username, String userpwd, String phoneno, String joindate) {
		super();
		this.email = email;
		this.username = username;
		this.userpwd = userpwd;
		this.phoneno = phoneno;
		this.joindate = joindate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "MemberDto [email=" + email + ", username=" + username + ", userpwd=" + userpwd + ", phoneno=" + phoneno
				+ ", joindate=" + joindate + "]";
	}

}
