// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.app.AlertDialog;

class ErrorLoggingManager$3$2$1 implements Runnable
{
    final /* synthetic */ ErrorLoggingManager$3$2 this$1;
    
    ErrorLoggingManager$3$2$1(final ErrorLoggingManager$3$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.this$0.alertDialog != null) {
            final AlertDialog alertDialog = this.this$1.this$0.alertDialog;
            final String message = this.this$1.this$0.message;
            final ErrorLoggingManager$3 this$0 = this.this$1.this$0;
            final int counter = this$0.counter - 1;
            this$0.counter = counter;
            alertDialog.setMessage((CharSequence)String.format(message, counter));
        }
    }
}
