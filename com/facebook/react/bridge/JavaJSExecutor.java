// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface JavaJSExecutor
{
    @DoNotStrip
    String executeJSCall(final String p0, final String p1);
    
    @DoNotStrip
    void loadApplicationScript(final String p0);
    
    @DoNotStrip
    void setGlobalVariable(final String p0, final String p1);
}
