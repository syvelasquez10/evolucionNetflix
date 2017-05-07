// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface;
import android.os.Handler;
import android.content.DialogInterface$OnClickListener;

final class AlertDialogFactory$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ Runnable val$negativeButtonHandler;
    
    AlertDialogFactory$2(final Runnable val$negativeButtonHandler, final Handler val$handler) {
        this.val$negativeButtonHandler = val$negativeButtonHandler;
        this.val$handler = val$handler;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.val$negativeButtonHandler != null) {
            this.val$handler.post(this.val$negativeButtonHandler);
        }
    }
}
