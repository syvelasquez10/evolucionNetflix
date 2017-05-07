// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public class UserCredentialRegistryWrapper implements UserCredentialRegistry
{
    private String mNetflixId;
    private String mSecureNetflixId;
    private UserCredentialRegistry mUserCredentialRegistry;
    
    public UserCredentialRegistryWrapper(final UserCredentialRegistry mUserCredentialRegistry) {
        this.mUserCredentialRegistry = mUserCredentialRegistry;
        if (mUserCredentialRegistry != null) {
            this.mNetflixId = mUserCredentialRegistry.getNetflixID();
            this.mSecureNetflixId = mUserCredentialRegistry.getSecureNetflixID();
        }
    }
    
    @Override
    public String getNetflixID() {
        return this.mNetflixId;
    }
    
    @Override
    public String getNetflixIdName() {
        return this.mUserCredentialRegistry.getNetflixIdName();
    }
    
    @Override
    public String getSecureNetflixID() {
        return this.mSecureNetflixId;
    }
    
    @Override
    public String getSecureNetflixIdName() {
        return this.mUserCredentialRegistry.getSecureNetflixIdName();
    }
    
    @Override
    public boolean updateUserCredentials(final String s, final String s2) {
        return this.mUserCredentialRegistry.updateUserCredentials(s, s2);
    }
}
