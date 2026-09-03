package com.team3.entities;

import java.time.LocalDateTime;

public class UserSession {
	private String sessionId;
    private LocalDateTime loginTime;
    private LocalDateTime expiryTime;
    private long userId;
    
    
    public UserSession() {
    }
    
	
//    constructors
    public UserSession(String sessionId, LocalDateTime loginTime, LocalDateTime expiryTime, long userId) {
		super();
		this.sessionId = sessionId;
		this.loginTime = loginTime;
		this.expiryTime = expiryTime;
		this.userId = userId;
	}

//	getters and setters
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
    
    
    
}
