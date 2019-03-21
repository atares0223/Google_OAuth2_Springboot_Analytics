package com.lff.google.api.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialService {
	@Autowired
	private GoogleCredentialDao googleCredentialDao;

	public void merge(StoredCredential storedCredential,String googleUserId) {
		googleCredentialDao.merge(storedCredential,googleUserId);
		log.info("merge");
		
	}
	
	public StoredCredential get(String googleUserId){
		return googleCredentialDao.getStoredCredential(googleUserId);
	}
}
