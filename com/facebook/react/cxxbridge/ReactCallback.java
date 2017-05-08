// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
interface ReactCallback
{
    @DoNotStrip
    void decrementPendingJSCalls();
    
    @DoNotStrip
    void incrementPendingJSCalls();
    
    @DoNotStrip
    void onBatchComplete();
    
    @DoNotStrip
    void onNativeException(final Exception p0);
}
