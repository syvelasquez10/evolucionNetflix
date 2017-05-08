// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;

public final class ee
{
    public ee() {
        if (Build$VERSION.SDK_INT < 14 || Build$VERSION.SDK_INT > 23) {
            throw new cj("API Level " + Build$VERSION.SDK_INT + " does not supportWebView monitoring. Skipping instrumentation.");
        }
    }
}
