// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.os.Process;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;

class ErrorLoggingManager$3$1$1 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ ErrorLoggingManager$3$1 this$1;
    
    ErrorLoggingManager$3$1$1(final ErrorLoggingManager$3$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        Process.killProcess(Process.myPid());
    }
}
