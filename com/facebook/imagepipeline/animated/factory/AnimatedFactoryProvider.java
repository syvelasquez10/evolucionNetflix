// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.animated.factory;

import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;

public class AnimatedFactoryProvider
{
    private static AnimatedFactory sImpl;
    private static boolean sImplLoaded;
    
    static {
        AnimatedFactoryProvider.sImpl = null;
    }
    
    public static AnimatedFactory getAnimatedFactory(final PlatformBitmapFactory t, final ExecutorSupplier executorSupplier) {
        Label_0110: {
            if (AnimatedFactoryProvider.sImplLoaded) {
                break Label_0110;
            }
            while (true) {
                try {
                    AnimatedFactoryProvider.sImpl = (AnimatedFactory)Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImplSupport").getConstructor(PlatformBitmapFactory.class, ExecutorSupplier.class).newInstance(t, executorSupplier);
                    if (AnimatedFactoryProvider.sImpl != null) {
                        AnimatedFactoryProvider.sImplLoaded = true;
                        return AnimatedFactoryProvider.sImpl;
                    }
                    try {
                        AnimatedFactoryProvider.sImpl = (AnimatedFactory)Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl").getConstructor(PlatformBitmapFactory.class, ExecutorSupplier.class).newInstance(t, executorSupplier);
                        AnimatedFactoryProvider.sImplLoaded = true;
                        return AnimatedFactoryProvider.sImpl;
                    }
                    catch (Throwable t) {}
                }
                catch (Throwable t2) {
                    continue;
                }
                break;
            }
        }
    }
}
