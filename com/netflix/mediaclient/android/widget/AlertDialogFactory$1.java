// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface;
import android.os.Handler;
import android.content.DialogInterface$OnClickListener;

final class AlertDialogFactory$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Runnable val$cleanupHandler;
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ Runnable val$positiveButtonHandler;
    
    AlertDialogFactory$1(final Runnable val$positiveButtonHandler, final Handler val$handler, final Runnable val$cleanupHandler) {
        this.val$positiveButtonHandler = val$positiveButtonHandler;
        this.val$handler = val$handler;
        this.val$cleanupHandler = val$cleanupHandler;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.val$positiveButtonHandler != null) {
            this.val$handler.post(this.val$positiveButtonHandler);
        }
        if (this.val$cleanupHandler != null) {
            this.val$handler.post(this.val$cleanupHandler);
        }
    }
}
