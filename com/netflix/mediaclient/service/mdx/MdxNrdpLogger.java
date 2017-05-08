// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.javabridge.ui.Nrdp;

public class MdxNrdpLogger
{
    private static final String MDX_LOG_TYPE = "mdx";
    private Nrdp mNrdp;
    
    MdxNrdpLogger(final Nrdp mNrdp) {
        this.mNrdp = mNrdp;
    }
    
    public void logDebug(final String s) {
        if (this.mNrdp != null) {
            this.mNrdp.getLog().log(new LogArguments(LogArguments$LogLevel.DEBUG, s, "mdx", null));
        }
    }
    
    public void logError(final String s) {
        if (this.mNrdp != null) {
            this.mNrdp.getLog().log(new LogArguments(LogArguments$LogLevel.ERROR, s, "mdx", null));
        }
    }
}
