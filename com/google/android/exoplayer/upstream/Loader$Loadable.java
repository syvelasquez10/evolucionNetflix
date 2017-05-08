// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public interface Loader$Loadable
{
    void cancelLoad();
    
    boolean isLoadCanceled();
    
    void load();
}
