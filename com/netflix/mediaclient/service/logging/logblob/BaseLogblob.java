// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.Logblob;

public abstract class BaseLogblob implements Logblob
{
    public static final String APP_ID = "appid";
    public static final String CLIENT_VER = "clver";
    public static final String ESN = "esn";
    public static final String SESSION_ID = "sessionid";
    public static final String SEV = "sev";
    public static final String TYPE = "type";
    protected final long mClientEpoch;
    protected JSONObject mJson;
    protected Logblob$Severity mSeverity;
    
    protected BaseLogblob() {
        this.mClientEpoch = System.currentTimeMillis();
        this.mJson = new JSONObject();
        this.mSeverity = Logblob$Severity.info;
    }
    
    private void setAppId(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mJson.put("appid", (Object)s);
        }
    }
    
    private void setUserSessionId(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mJson.put("sessionid", (Object)s);
        }
    }
    
    @Override
    public long getClientEpoch() {
        return this.mClientEpoch;
    }
    
    @Override
    public Logblob$Severity getSeverity() {
        return this.mSeverity;
    }
    
    public void init(final Context context, final String appId, final String userSessionId) {
        this.mJson.put("clver", (Object)AndroidManifestUtils.getClientVersion(context));
        if (this.getSeverity() != null) {
            this.mJson.put("sev", (Object)this.getSeverity().name());
        }
        final String type = this.getType();
        if (StringUtils.isNotEmpty(type)) {
            this.mJson.put("type", (Object)type);
        }
        this.setAppId(appId);
        this.setUserSessionId(userSessionId);
    }
    
    @Override
    public boolean isMandatory() {
        return false;
    }
    
    @Override
    public boolean shouldSendNow() {
        return false;
    }
    
    @Override
    public JSONObject toJson() {
        return this.mJson;
    }
    
    @Override
    public String toJsonString() {
        return this.mJson.toString();
    }
}
