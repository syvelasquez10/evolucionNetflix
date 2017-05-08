// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import java.lang.ref.WeakReference;
import com.facebook.systrace.TraceListener;

class CatalystInstanceImpl$JSProfilerTraceListener implements TraceListener
{
    private final WeakReference<CatalystInstanceImpl> mOuter;
    
    public CatalystInstanceImpl$JSProfilerTraceListener(final CatalystInstanceImpl catalystInstanceImpl) {
        this.mOuter = new WeakReference<CatalystInstanceImpl>(catalystInstanceImpl);
    }
}
