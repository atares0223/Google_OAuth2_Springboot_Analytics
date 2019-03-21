package com.lff.google.api.engine;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

/**
 * Thread-safe file implementation of a credential store.
 *
 * <p>
 * For security purposes, the file's permissions are set to be accessible only
 * by the file's owner. Note that Java 1.5 does not support manipulating file
 * permissions, and must be done manually or using the JNI.
 * </p>
 *
 * @since 1.16
 * @author Yaniv Inbar
 */
public class MysqlDataStore extends AbstractDataStore<StoredCredential> {
	private CredentialService credentialService;
	
	private MysqlDataStore(DataStoreFactory dataStoreFactory, String id) {
		super(dataStoreFactory, id);
		
	}
	
	MysqlDataStore(DataStoreFactory dataStoreFactory, String id ,CredentialService credentialService) {
		super(dataStoreFactory, id);
		this.credentialService = credentialService;
		
	}

	@Override
	public Set<String> keySet() throws IOException {
		return null;
	}

	@Override
	public Collection<StoredCredential> values() throws IOException {
		return null;
	}

	@Override
	public StoredCredential get(String key) throws IOException {
		return credentialService.get(key);
	}

	@Override
	public DataStore<StoredCredential> set(String key, StoredCredential value) throws IOException {
		 	credentialService.merge(value,key); 	
		 return this;
	}

	@Override
	public DataStore<StoredCredential> clear() throws IOException {
		return this;
	}

	@Override
	public DataStore<StoredCredential> delete(String key) throws IOException {
		return this;
	}

	

}
