// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.util.Log;

public class BaseImplementation
{
    static void a(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("GoogleApi", "Unable to release " + result, (Throwable)ex);
        }
    }
}
