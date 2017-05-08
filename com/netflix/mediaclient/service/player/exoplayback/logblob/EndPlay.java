// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;

public class EndPlay extends OfflinePlaybackBaseLogblob
{
    public EndPlay(final LogArguments$LogLevel logArguments$LogLevel, final long n, final long n2, final String s, final String s2, final String s3, final long n3, final long n4, final long n5, final String s4, final boolean b, final JSONObject jsonObject, final JSONObject jsonObject2, final String s5, final String s6, final String s7, final String s8) {
        super(s, s2, s3);
        this.updateSeverity(logArguments$LogLevel);
        this.mJson.put("level", logArguments$LogLevel.getLevel());
        this.mJson.put("playbackoffline", true);
        this.mJson.put("mid", n);
        this.mJson.put("trackid", n2);
        this.mJson.put("soffms", n3);
        this.mJson.put("moffms", n4);
        this.mJson.put("endreason", (Object)s4);
        this.mJson.put("totaltime", n5);
        if (jsonObject != null) {
            this.mJson.put("playstat", (Object)jsonObject);
        }
        if (jsonObject2 != null) {
            this.mJson.put("batterystat", (Object)jsonObject2);
        }
        this.mJson.put("offlinenetworkstatus", !b);
        if (StringUtils.isNotEmpty(s6)) {
            this.mJson.put("errormsg", (Object)s6);
        }
        if (StringUtils.isNotEmpty(s5)) {
            this.mJson.put("errorcode", (Object)s5);
        }
        if (StringUtils.isNotEmpty(s7)) {
            this.mJson.put("errorstring", (Object)s7);
        }
        if (StringUtils.isNotEmpty(s8)) {
            this.mJson.put("activitydata", (Object)s8);
        }
    }
    
    @Override
    public String getType() {
        return "endplay";
    }
    
    @Override
    public boolean shouldSendNow() {
        return true;
    }
}
