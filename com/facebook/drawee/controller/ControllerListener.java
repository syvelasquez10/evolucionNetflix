// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;

public interface ControllerListener<INFO>
{
    void onFailure(final String p0, final Throwable p1);
    
    void onFinalImageSet(final String p0, final INFO p1, final Animatable p2);
    
    void onIntermediateImageFailed(final String p0, final Throwable p1);
    
    void onIntermediateImageSet(final String p0, final INFO p1);
    
    void onRelease(final String p0);
    
    void onSubmit(final String p0, final Object p1);
}
