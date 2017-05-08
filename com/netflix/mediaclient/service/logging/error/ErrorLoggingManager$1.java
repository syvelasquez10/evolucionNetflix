// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import com.netflix.mediaclient.Log;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

final class ErrorLoggingManager$1 implements UncaughtExceptionHandler
{
    final /* synthetic */ UncaughtExceptionHandler val$critDefaultHandler;
    final /* synthetic */ Context val$globalContext;
    
    ErrorLoggingManager$1(final Context val$globalContext, final UncaughtExceptionHandler val$critDefaultHandler) {
        this.val$globalContext = val$globalContext;
        this.val$critDefaultHandler = val$critDefaultHandler;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        while (true) {
            try {
                final int n = PersistentConfig.getCoppola1ABTestCell(this.val$globalContext).ordinal() + 1;
                Throwable t2 = t;
                if (n > 1) {
                    final ArrayList<StackTraceElement> list = new ArrayList<StackTraceElement>(Arrays.asList(t.getStackTrace()));
                    final String format = String.format("Coppola_%d ", n);
                    list.add(new StackTraceElement(format, "version", "n/a", 0));
                    t.setStackTrace(list.toArray(new StackTraceElement[list.size()]));
                    t2 = new IOException(format + t.getMessage(), t);
                }
                this.val$critDefaultHandler.uncaughtException(thread, t2);
            }
            catch (Throwable t3) {
                final String string = "SPY-9027 - got throwable while wrapping stack trace: " + t3;
                ErrorLoggingManager.logHandledException(string);
                Log.e("nf_log_crit", string);
                final Throwable t2 = t;
                continue;
            }
            break;
        }
    }
}
