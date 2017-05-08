// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

public final class SoLoader$WrongAbiError extends UnsatisfiedLinkError
{
    SoLoader$WrongAbiError(final Throwable t) {
        super("APK was built for a different platform");
        this.initCause(t);
    }
}
