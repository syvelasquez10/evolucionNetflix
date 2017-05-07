// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

@Deprecated
public interface OnStateLoadedListener
{
    void onStateConflict(final int p0, final String p1, final byte[] p2, final byte[] p3);
    
    void onStateLoaded(final int p0, final int p1, final byte[] p2);
}
