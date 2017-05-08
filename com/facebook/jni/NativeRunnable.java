// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class NativeRunnable implements Runnable
{
    @Override
    public native void run();
}
