// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

import com.netflix.mediaclient.util.StringUtils;

public class ResumePlay extends OfflinePlaybackBaseLogblob
{
    public ResumePlay(final long n, final long n2, final long n3, final String s, final String s2, final String s3, final String s4, final String s5, final long n4, final String s6, final long n5) {
        super(s, s2, s3);
        this.mJson.put("mid", n);
        this.mJson.put("soffms", n3);
        this.mJson.put("soff", n3 / 1000L);
        this.mJson.put("moff", n2);
        this.mJson.put("moffms", n2 / 1000L);
        if (StringUtils.isNotEmpty(s5)) {
            this.mJson.put("vdlid", (Object)s5);
            this.mJson.put("vBitrate", n4);
        }
        if (StringUtils.isNotEmpty(s6)) {
            this.mJson.put("adlid", (Object)s6);
            this.mJson.put("abitrate", n5);
        }
        this.mJson.put("reason", (Object)s4);
    }
    
    @Override
    public String getType() {
        return "resumeplay";
    }
}
