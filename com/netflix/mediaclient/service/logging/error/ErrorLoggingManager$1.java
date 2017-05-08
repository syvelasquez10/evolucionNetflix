// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import com.netflix.mediaclient.ui.common.ExportDebugData;
import android.content.Context;

final class ErrorLoggingManager$1 implements UncaughtExceptionHandler
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ UncaughtExceptionHandler val$defaultUncaughtExceptionHandler;
    
    ErrorLoggingManager$1(final Context val$context, final UncaughtExceptionHandler val$defaultUncaughtExceptionHandler) {
        this.val$context = val$context;
        this.val$defaultUncaughtExceptionHandler = val$defaultUncaughtExceptionHandler;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        ExportDebugData.markCrashed(this.val$context);
        this.val$defaultUncaughtExceptionHandler.uncaughtException(thread, t);
    }
}
