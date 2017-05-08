// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;

public abstract class OfflinePlaybackBaseLogblob extends OfflineBaseLogblob
{
    public OfflinePlaybackBaseLogblob(final String s, final String s2, final String s3) {
        super(s2, s3);
        this.mJson.put("xid", (Object)s);
    }
    
    public void updateSeverity(final LogArguments$LogLevel logArguments$LogLevel) {
        if (logArguments$LogLevel.getLevel() > LogArguments$LogLevel.INFO.getLevel()) {
            this.mSeverity = Logblob$Severity.error;
        }
    }
}
