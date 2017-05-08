// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Inspector$LocalConnection
{
    public native void disconnect();
    
    public native void sendMessage(final String p0);
}
