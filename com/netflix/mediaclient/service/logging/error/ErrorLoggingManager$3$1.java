// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.content.Context;
import android.os.Handler;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.NetflixApplication;
import android.os.Looper;

class ErrorLoggingManager$3$1 extends Thread
{
    final /* synthetic */ ErrorLoggingManager$3 this$0;
    final /* synthetic */ Throwable val$ex;
    final /* synthetic */ String val$threadDump;
    
    ErrorLoggingManager$3$1(final ErrorLoggingManager$3 this$0, final Throwable val$ex, final String val$threadDump) {
        this.this$0 = this$0;
        this.val$ex = val$ex;
        this.val$threadDump = val$threadDump;
    }
    
    @Override
    public void run() {
        Looper.prepare();
        final Context currentActivityForDebug = NetflixApplication.getCurrentActivityForDebug();
        if (currentActivityForDebug != null) {
            (this.this$0.alertDialog = new AlertDialog$Builder(currentActivityForDebug).setTitle((CharSequence)"Netflix app has crashed! :/").setMessage((CharSequence)String.format(this.this$0.message, this.this$0.counter)).setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new ErrorLoggingManager$3$1$3(this, currentActivityForDebug)).setNegativeButton((CharSequence)"No", (DialogInterface$OnClickListener)new ErrorLoggingManager$3$1$2(this)).setOnCancelListener((DialogInterface$OnCancelListener)new ErrorLoggingManager$3$1$1(this)).setIcon(2130837756).create()).show();
        }
        this.this$0.handler = new Handler();
        Looper.loop();
    }
}
