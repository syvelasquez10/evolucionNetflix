// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.concurrent.ThreadFactory;

public final class dy implements ThreadFactory
{
    @Override
    public final Thread newThread(final Runnable runnable) {
        return new dx(runnable);
    }
}
