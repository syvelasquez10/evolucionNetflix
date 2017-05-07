// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import org.json.JSONObject;

public interface SessionController
{
    void endSession(final int p0);
    
    long sendMessage(final int p0, final String p1, final JSONObject p2);
    
    void startSession(final String p0);
}
