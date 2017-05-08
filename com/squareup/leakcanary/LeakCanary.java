// 
// Decompiled by Procyon v0.5.30
// 

package com.squareup.leakcanary;

import android.content.Context;
import android.app.Application;

public final class LeakCanary
{
    public static RefWatcher install(final Application application) {
        return RefWatcher.DISABLED;
    }
    
    public static boolean isInAnalyzerProcess(final Context context) {
        return false;
    }
}
