// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;

public class StartPlay extends OfflinePlaybackBaseLogblob
{
    public StartPlay(final LogArguments$LogLevel logArguments$LogLevel, final long n, final long n2, final String s, final String s2, final String s3, final long n3, final long n4, final String s4, final long n5, final String s5, final long n6, final long n7, final boolean b, final String s6, final String s7, final String s8) {
        super(s, s2, s3);
        this.mJson.put("level", logArguments$LogLevel.getLevel());
        this.updateSeverity(logArguments$LogLevel);
        this.mJson.put("trackid", n2);
        this.mJson.put("mid", n);
        this.mJson.put("soffms", n3);
        this.mJson.put("soff", n3 / 1000L);
        this.mJson.put("moff", n4);
        this.mJson.put("moffms", n4 / 1000L);
        if (StringUtils.isNotEmpty(s4)) {
            this.mJson.put("vdlid", (Object)s4);
            this.mJson.put("vBitrate", n5);
        }
        if (StringUtils.isNotEmpty(s5)) {
            this.mJson.put("adlid", (Object)s5);
            this.mJson.put("abitrate", n6);
        }
        this.mJson.put("playdelay", n7);
        this.mJson.put("offlinenetworkstatus", !b);
        if (StringUtils.isNotEmpty(s7)) {
            this.mJson.put("errormsg", (Object)s7);
        }
        if (StringUtils.isNotEmpty(s6)) {
            this.mJson.put("errorcode", (Object)s6);
        }
        if (StringUtils.isNotEmpty(s8)) {
            this.mJson.put("errorstring", (Object)s8);
        }
    }
    
    @Override
    public String getType() {
        return "startplay";
    }
}
