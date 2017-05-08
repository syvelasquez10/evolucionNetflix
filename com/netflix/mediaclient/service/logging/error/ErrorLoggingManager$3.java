// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import java.util.TimerTask;
import java.util.Timer;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import android.os.Handler;
import android.app.AlertDialog;

final class ErrorLoggingManager$3 implements UncaughtExceptionHandler
{
    AlertDialog alertDialog;
    int counter;
    Handler handler;
    String message;
    final /* synthetic */ UncaughtExceptionHandler val$defaultHandler;
    
    ErrorLoggingManager$3(final UncaughtExceptionHandler val$defaultHandler) {
        this.val$defaultHandler = val$defaultHandler;
        this.handler = null;
        this.alertDialog = null;
        this.counter = 15;
        this.message = "Do you want to report it? You have %s seconds to do so.";
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        final String dumpThreads = ThreadUtils.dumpThreads(thread, t);
        Log.e("AndroidRuntime", t.getMessage(), t);
        new ErrorLoggingManager$3$1(this, t, dumpThreads).start();
        new Timer().scheduleAtFixedRate(new ErrorLoggingManager$3$2(this), 1000L, 1000L);
        while (true) {
            try {
                Thread.sleep(this.counter * 1000);
                this.val$defaultHandler.uncaughtException(thread, t);
            }
            catch (InterruptedException ex) {
                Log.e("AndroidRuntime", ex.getMessage());
                continue;
            }
            break;
        }
    }
}
