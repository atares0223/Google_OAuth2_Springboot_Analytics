package com.lff.google.api.engine;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.client.auth.oauth2.StoredCredential;

@Repository
public class GoogleCredentialDao {
	@Autowired
	private SQLManager sqlManager;
	public void merge(StoredCredential storedCredential,String google_user_id){
		Query<GoogleCredential> query = sqlManager.query(GoogleCredential.class).andEq("google_user_id", google_user_id);
		GoogleCredential googleCredential = new GoogleCredential(google_user_id, storedCredential);
		if(query.count()>0){
			sqlManager.query(GoogleCredential.class).andEq("google_user_id", google_user_id).update(googleCredential);
		}else{
			sqlManager.insert(googleCredential,false);
		}
	}
	
	public StoredCredential getStoredCredential(String googleUserId){
		GoogleCredential googleCredential = sqlManager.query(GoogleCredential.class).andEq("google_user_id", googleUserId).single();
		if(googleCredential!=null)
			return googleCredential.getStoredCredential();
		return null;
	}
	
}
