// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;

class AccountKeyMap$KeyIds
{
    private String mKceKeyId;
    private String mKchKeyId;
    private String mKeySetId;
    final /* synthetic */ AccountKeyMap this$0;
    
    AccountKeyMap$KeyIds(final AccountKeyMap this$0, final String s) {
        this.this$0 = this$0;
        if (Log.isLoggable()) {
            Log.d(AccountKeyMap.TAG, "KeyIds from JSON " + s);
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            this.mKeySetId = jsonObject.optString("keySetId");
            this.mKceKeyId = jsonObject.optString("kceKeyId");
            this.mKchKeyId = jsonObject.optString("kchKeyId");
        }
        catch (JSONException ex) {
            Log.w(AccountKeyMap.TAG, "can't turn JSON to KeyIds " + ex);
        }
    }
    
    AccountKeyMap$KeyIds(final AccountKeyMap this$0, final String mKeySetId, final String mKceKeyId, final String mKchKeyId) {
        this.this$0 = this$0;
        this.mKeySetId = mKeySetId;
        this.mKceKeyId = mKceKeyId;
        this.mKchKeyId = mKchKeyId;
    }
    
    public String getKceKeyId() {
        return this.mKceKeyId;
    }
    
    public String getKchKeyId() {
        return this.mKchKeyId;
    }
    
    public String getKeySetId() {
        return this.mKeySetId;
    }
    
    JSONObject toJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("keySetId", (Object)this.mKeySetId).putOpt("kceKeyId", (Object)this.mKceKeyId).putOpt("kchKeyId", (Object)this.mKchKeyId);
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.w(AccountKeyMap.TAG, "can't turn KeyIds toJSON" + ex);
            return jsonObject;
        }
    }
}
