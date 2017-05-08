// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;

final class AbstractDraweeControllerBuilder$1 extends BaseControllerListener<Object>
{
    @Override
    public void onFinalImageSet(final String s, final Object o, final Animatable animatable) {
        if (animatable != null) {
            animatable.start();
        }
    }
}
