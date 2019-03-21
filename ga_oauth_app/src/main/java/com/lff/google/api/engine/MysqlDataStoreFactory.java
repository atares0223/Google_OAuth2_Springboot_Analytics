package com.lff.google.api.engine;

import java.io.IOException;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.DataStore;

/**
 * Thread-safe file implementation of a credential store.
 *
 * <p>
 * For security purposes, the file's permissions are set to be accessible only by the file's owner.
 * Note that Java 1.5 does not support manipulating file permissions, and must be done manually or
 * using the JNI.
 * </p>
 *
 * @since 1.16
 * @author Yaniv Inbar
 */
public class MysqlDataStoreFactory extends AbstractDataStoreFactory {
private CredentialService credentialService;

@SuppressWarnings("unchecked")
@Override
protected  DataStore<StoredCredential> createDataStore(String id) throws IOException {
	 return new MysqlDataStore(this, id,credentialService);
}


public MysqlDataStoreFactory(CredentialService credentialService) {
	super();
	this.credentialService = credentialService;
}




 
}
