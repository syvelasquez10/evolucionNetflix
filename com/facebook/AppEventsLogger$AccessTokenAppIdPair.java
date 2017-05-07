// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.internal.Utility;
import java.io.Serializable;

class AppEventsLogger$AccessTokenAppIdPair implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String accessToken;
    private final String applicationId;
    
    AppEventsLogger$AccessTokenAppIdPair(final Session session) {
        this(session.getAccessToken(), session.getApplicationId());
    }
    
    AppEventsLogger$AccessTokenAppIdPair(final String s, final String applicationId) {
        String accessToken = s;
        if (Utility.isNullOrEmpty(s)) {
            accessToken = null;
        }
        this.accessToken = accessToken;
        this.applicationId = applicationId;
    }
    
    private Object writeReplace() {
        return new AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1(this.accessToken, this.applicationId, null);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AppEventsLogger$AccessTokenAppIdPair) {
            final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair = (AppEventsLogger$AccessTokenAppIdPair)o;
            if (Utility.areObjectsEqual(appEventsLogger$AccessTokenAppIdPair.accessToken, this.accessToken) && Utility.areObjectsEqual(appEventsLogger$AccessTokenAppIdPair.applicationId, this.applicationId)) {
                return true;
            }
        }
        return false;
    }
    
    String getAccessToken() {
        return this.accessToken;
    }
    
    String getApplicationId() {
        return this.applicationId;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.accessToken == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.accessToken.hashCode();
        }
        if (this.applicationId != null) {
            hashCode = this.applicationId.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
}
