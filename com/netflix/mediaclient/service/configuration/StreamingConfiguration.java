// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class StreamingConfiguration
{
    Context mContext;
    
    public StreamingConfiguration(final Context mContext) {
        this.mContext = mContext;
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "streamingqoe", null);
    }
    
    public String getStreamingQoe() {
        return PreferenceUtils.getStringPref(this.mContext, "streamingqoe", null);
    }
    
    public boolean isStreamingConfigInCache() {
        return !StringUtils.isEmpty(this.getStreamingQoe());
    }
    
    public void persistStreamingOverride(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            PreferenceUtils.putStringPref(this.mContext, "streamingqoe", s);
        }
    }
}
