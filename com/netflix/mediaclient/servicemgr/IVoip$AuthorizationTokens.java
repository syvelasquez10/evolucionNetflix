// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;

public class IVoip$AuthorizationTokens
{
    private static final String ENC_TOKEN = "encToken";
    private static final String EXP_TIME = "expTime";
    private static final String USER_TOKEN = "userToken";
    private static final String USER_TYPE = "userType";
    private String encToken;
    public long expirationTimeInMs;
    private String userToken;
    private IVoip$UserType userType;
    
    public IVoip$AuthorizationTokens(final String userToken, final String encToken, final IVoip$UserType userType, final long expirationTimeInMs) {
        if (userType == null) {
            throw new IllegalStateException("User type is null");
        }
        if (StringUtils.isEmpty(userToken)) {
            throw new IllegalStateException("userToken is null!");
        }
        if (StringUtils.isEmpty(encToken)) {
            throw new IllegalStateException("encToken is null!");
        }
        this.userToken = userToken;
        this.encToken = encToken;
        this.userType = userType;
        this.expirationTimeInMs = expirationTimeInMs;
    }
    
    public IVoip$AuthorizationTokens(final JSONObject jsonObject) {
        this.userToken = jsonObject.getString("userToken");
        this.encToken = jsonObject.getString("encToken");
        this.userType = IVoip$UserType.valueOf(jsonObject.getString("userType"));
        this.expirationTimeInMs = jsonObject.getLong("expTime");
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final IVoip$AuthorizationTokens voip$AuthorizationTokens = (IVoip$AuthorizationTokens)o;
                    if (this.userToken != null) {
                        b3 = b2;
                        if (!this.userToken.equals(voip$AuthorizationTokens.userToken)) {
                            return b3;
                        }
                    }
                    else if (voip$AuthorizationTokens.userToken != null) {
                        return false;
                    }
                    if (this.encToken != null) {
                        b3 = b2;
                        if (!this.encToken.equals(voip$AuthorizationTokens.encToken)) {
                            return b3;
                        }
                    }
                    else if (voip$AuthorizationTokens.encToken != null) {
                        return false;
                    }
                    return this.userType == voip$AuthorizationTokens.userType && b;
                }
            }
        }
        return b3;
    }
    
    public boolean equalsWithoutUserType(final IVoip$AuthorizationTokens voip$AuthorizationTokens) {
        if (this != voip$AuthorizationTokens) {
            if (voip$AuthorizationTokens == null) {
                return false;
            }
            Label_0043: {
                if (this.userToken != null) {
                    if (this.userToken.equals(voip$AuthorizationTokens.userToken)) {
                        break Label_0043;
                    }
                }
                else if (voip$AuthorizationTokens.userToken == null) {
                    break Label_0043;
                }
                return false;
            }
            if (this.encToken != null) {
                if (this.encToken.equals(voip$AuthorizationTokens.encToken)) {
                    return true;
                }
            }
            else if (voip$AuthorizationTokens.encToken == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getEncToken() {
        return this.encToken;
    }
    
    public long getExpirationTimeInMs() {
        return this.expirationTimeInMs;
    }
    
    public String getUserToken() {
        return this.userToken;
    }
    
    public IVoip$UserType getUserType() {
        return this.userType;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.userToken != null) {
            hashCode2 = this.userToken.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.encToken != null) {
            hashCode3 = this.encToken.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        if (this.userType != null) {
            hashCode = this.userType.hashCode();
        }
        return ((hashCode3 + hashCode2 * 31) * 31 + hashCode) * 31 + (int)this.expirationTimeInMs;
    }
    
    public boolean isExpired() {
        return System.currentTimeMillis() > this.expirationTimeInMs;
    }
    
    public JSONObject toJSon() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("userToken", (Object)this.userToken);
        jsonObject.put("encToken", (Object)this.encToken);
        jsonObject.put("userType", (Object)this.userType.name());
        jsonObject.put("expTime", this.expirationTimeInMs);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "AuthorizationTokens{userToken='" + this.userToken + '\'' + ", encToken='" + this.encToken + '\'' + ", userType=" + this.userType + ", expirationTimeInMs=" + this.expirationTimeInMs + '}';
    }
}
