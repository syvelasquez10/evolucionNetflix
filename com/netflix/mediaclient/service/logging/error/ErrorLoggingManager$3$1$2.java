// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.os.Process;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class ErrorLoggingManager$3$1$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ ErrorLoggingManager$3$1 this$1;
    
    ErrorLoggingManager$3$1$2(final ErrorLoggingManager$3$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Process.killProcess(Process.myPid());
    }
}
