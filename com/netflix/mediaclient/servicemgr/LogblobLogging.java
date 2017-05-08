// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.ui.LogArguments;

public interface LogblobLogging
{
    void sendLogblob(final LogArguments p0);
    
    void sendLogblob(final Logblob p0);
}
