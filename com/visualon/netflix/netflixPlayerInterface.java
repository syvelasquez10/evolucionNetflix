// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.netflix;

import android.view.SurfaceView;
import android.content.Context;

public interface netflixPlayerInterface
{
    int voCreate(final Context p0) throws IllegalStateException;
    
    int voDestroy();
    
    long voGetAPI();
    
    int voGetSourceContext();
    
    int voGetVideoHeight();
    
    int voGetVideoWidth();
    
    void voSetDisplay(final SurfaceView p0);
    
    int voSetVolume(final float p0, final float p1) throws IllegalStateException;
}
