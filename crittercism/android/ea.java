// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.ThreadFactory;

public final class ea implements ThreadFactory
{
    @Override
    public final Thread newThread(final Runnable runnable) {
        return new dz(runnable);
    }
}
