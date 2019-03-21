package com.lff.google.api.engine;

import java.io.Serializable;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.google.api.client.auth.oauth2.StoredCredential;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "google_credential")
public class GoogleCredential implements Serializable {
	@AssignID
	private String googleUserId;
	/** Access token or {@code null} for none. */
	private String accessToken;

	/** Expected expiration time in milliseconds or {@code null} for none. */
	private Long expirationTimeMilliseconds;

	/** Refresh token or {@code null} for none. */
	private String refreshToken;

	@UpdateIgnore
	private Date createAt;
	private Date updateAt;

	public GoogleCredential(String googleUserId, StoredCredential storedCredential) {
		super();
		this.googleUserId = googleUserId;
		this.accessToken = storedCredential.getAccessToken();
		this.refreshToken = storedCredential.getRefreshToken();
		this.expirationTimeMilliseconds = storedCredential.getExpirationTimeMilliseconds();
	}
	
	public StoredCredential getStoredCredential(){
		StoredCredential storedCredential = new StoredCredential();
		storedCredential.setAccessToken(this.accessToken);
		storedCredential.setRefreshToken(this.refreshToken);
		storedCredential.setExpirationTimeMilliseconds(this.expirationTimeMilliseconds);
		return storedCredential;
	}

}