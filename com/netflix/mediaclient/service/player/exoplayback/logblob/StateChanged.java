// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

public class StateChanged extends OfflinePlaybackBaseLogblob
{
    public StateChanged(final String s, final String s2, final String s3, final long n, final long n2, final String s4, final String s5) {
        super(s, s2, s3);
        this.mJson.put("soffms", n);
        this.mJson.put("moff", n2 / 1000L);
        this.mJson.put("moffms", n2);
        this.mJson.put("oldstate", (Object)s4);
        this.mJson.put("newstate", (Object)s5);
    }
    
    @Override
    public String getType() {
        return "statechanged";
    }
}
