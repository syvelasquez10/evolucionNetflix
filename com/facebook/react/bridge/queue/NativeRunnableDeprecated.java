// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.jni.Countable;

@DoNotStrip
public class NativeRunnableDeprecated extends Countable implements Runnable
{
    @Override
    public native void run();
}
