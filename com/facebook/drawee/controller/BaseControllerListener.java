// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;

public class BaseControllerListener<INFO> implements ControllerListener<INFO>
{
    private static final ControllerListener<Object> NO_OP_LISTENER;
    
    static {
        NO_OP_LISTENER = new BaseControllerListener<Object>();
    }
    
    public static <INFO> ControllerListener<INFO> getNoOpListener() {
        return (ControllerListener<INFO>)BaseControllerListener.NO_OP_LISTENER;
    }
    
    @Override
    public void onFailure(final String s, final Throwable t) {
    }
    
    @Override
    public void onFinalImageSet(final String s, final INFO info, final Animatable animatable) {
    }
    
    @Override
    public void onIntermediateImageFailed(final String s, final Throwable t) {
    }
    
    @Override
    public void onIntermediateImageSet(final String s, final INFO info) {
    }
    
    @Override
    public void onRelease(final String s) {
    }
    
    @Override
    public void onSubmit(final String s, final Object o) {
    }
}
