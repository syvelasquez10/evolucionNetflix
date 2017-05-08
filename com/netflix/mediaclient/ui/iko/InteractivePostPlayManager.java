// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

public interface InteractivePostPlayManager
{
    void onDestroy();
    
    void onPause();
    
    void onResume();
    
    void onStart();
    
    void onStop();
    
    void startPostPlay(final boolean p0);
    
    void startPreCachingResources();
    
    boolean waitUntilEndOfPlay();
}
