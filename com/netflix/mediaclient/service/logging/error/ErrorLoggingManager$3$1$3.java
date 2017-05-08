// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.os.Process;
import com.netflix.mediaclient.ui.common.ExportDebugData;
import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

class ErrorLoggingManager$3$1$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ ErrorLoggingManager$3$1 this$1;
    final /* synthetic */ Context val$currentActivityForDebug;
    
    ErrorLoggingManager$3$1$3(final ErrorLoggingManager$3$1 this$1, final Context val$currentActivityForDebug) {
        this.this$1 = this$1;
        this.val$currentActivityForDebug = val$currentActivityForDebug;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        ExportDebugData.createBugReportFromCrash(this.val$currentActivityForDebug, this.this$1.val$ex, this.this$1.val$threadDump);
        Process.killProcess(Process.myPid());
    }
}
