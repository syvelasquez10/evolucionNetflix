// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.animated.factory;

import android.content.Context;

public interface AnimatedFactory
{
    AnimatedDrawableFactory getAnimatedDrawableFactory(final Context p0);
    
    AnimatedImageFactory getAnimatedImageFactory();
}
