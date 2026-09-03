package com.team3.entities;

import java.time.LocalDateTime;

public class RememberToken {
	
	  	private long tokenId;
	    private String tokenValue;
	    private LocalDateTime expiresAt;
	    private long userId;
	    
	    public RememberToken() {
	    }
		
//	    constructors
	    public RememberToken(long tokenId, String tokenValue, LocalDateTime expiresAt, long userId) {
			super();
			this.tokenId = tokenId;
			this.tokenValue = tokenValue;
			this.expiresAt = expiresAt;
			this.userId = userId;
		}
//		getters and setters
		public long getTokenId() {
			return tokenId;
		}

		public void setTokenId(long tokenId) {
			this.tokenId = tokenId;
		}

		public String getTokenValue() {
			return tokenValue;
		}

		public void setTokenValue(String tokenValue) {
			this.tokenValue = tokenValue;
		}

		public LocalDateTime getExpiresAt() {
			return expiresAt;
		}

		public void setExpiresAt(LocalDateTime expiresAt) {
			this.expiresAt = expiresAt;
		}

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}
	    
	    
}
