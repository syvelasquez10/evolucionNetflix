// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslException extends Exception
{
    private static final long serialVersionUID = -2444322310603180494L;
    private EntityAuthenticationData entityAuthData;
    private final MslError error;
    private MasterToken masterToken;
    private Long messageId;
    private UserAuthenticationData userAuthData;
    private UserIdToken userIdToken;
    
    public MslException(final MslError error) {
        super(error.getMessage());
        this.masterToken = null;
        this.entityAuthData = null;
        this.userIdToken = null;
        this.userAuthData = null;
        this.messageId = null;
        this.error = error;
    }
    
    public MslException(final MslError error, final String s) {
        super(error.getMessage() + " [" + s + "]");
        this.masterToken = null;
        this.entityAuthData = null;
        this.userIdToken = null;
        this.userAuthData = null;
        this.messageId = null;
        this.error = error;
    }
    
    public MslException(final MslError error, final String s, final Throwable t) {
        super(error.getMessage() + " [" + s + "]", t);
        this.masterToken = null;
        this.entityAuthData = null;
        this.userIdToken = null;
        this.userAuthData = null;
        this.messageId = null;
        this.error = error;
    }
    
    public MslException(final MslError error, final Throwable t) {
        super(error.getMessage(), t);
        this.masterToken = null;
        this.entityAuthData = null;
        this.userIdToken = null;
        this.userAuthData = null;
        this.messageId = null;
        this.error = error;
    }
    
    public EntityAuthenticationData getEntityAuthenticationData() {
        if (this.entityAuthData != null) {
            return this.entityAuthData;
        }
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MslException) {
            return ((MslException)cause).getEntityAuthenticationData();
        }
        return null;
    }
    
    public MslError getError() {
        return this.error;
    }
    
    public MasterToken getMasterToken() {
        if (this.masterToken != null) {
            return this.masterToken;
        }
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MslException) {
            return ((MslException)cause).getMasterToken();
        }
        return null;
    }
    
    public Long getMessageId() {
        if (this.messageId != null) {
            return this.messageId;
        }
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MslException) {
            return ((MslException)cause).getMessageId();
        }
        return null;
    }
    
    public UserAuthenticationData getUserAuthenticationData() {
        if (this.userAuthData != null) {
            return this.userAuthData;
        }
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MslException) {
            return ((MslException)cause).getUserAuthenticationData();
        }
        return null;
    }
    
    public UserIdToken getUserIdToken() {
        if (this.userIdToken != null) {
            return this.userIdToken;
        }
        final Throwable cause = this.getCause();
        if (cause != null && cause instanceof MslException) {
            return ((MslException)cause).getUserIdToken();
        }
        return null;
    }
    
    public MslException setEntityAuthenticationData(final EntityAuthenticationData entityAuthData) {
        if (this.getMasterToken() == null && this.getEntityAuthenticationData() == null) {
            this.entityAuthData = entityAuthData;
        }
        return this;
    }
    
    public MslException setMasterToken(final MasterToken masterToken) {
        if (this.getMasterToken() == null && this.getEntityAuthenticationData() == null) {
            this.masterToken = masterToken;
        }
        return this;
    }
    
    public MslException setMessageId(final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new IllegalArgumentException("Message ID " + n + " is outside the valid range.");
        }
        if (this.getMessageId() == null) {
            this.messageId = n;
        }
        return this;
    }
    
    public MslException setUserAuthenticationData(final UserAuthenticationData userAuthData) {
        if (this.getUserIdToken() == null && this.getUserAuthenticationData() == null) {
            this.userAuthData = userAuthData;
        }
        return this;
    }
    
    public MslException setUserIdToken(final UserIdToken userIdToken) {
        if (this.getUserIdToken() == null && this.getUserAuthenticationData() == null) {
            this.userIdToken = userIdToken;
        }
        return this;
    }
}
