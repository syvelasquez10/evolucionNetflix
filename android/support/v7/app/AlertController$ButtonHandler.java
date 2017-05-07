// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.os.Message;
import android.content.DialogInterface;
import java.lang.ref.WeakReference;
import android.os.Handler;

final class AlertController$ButtonHandler extends Handler
{
    private WeakReference<DialogInterface> mDialog;
    
    public AlertController$ButtonHandler(final DialogInterface dialogInterface) {
        this.mDialog = new WeakReference<DialogInterface>(dialogInterface);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {}
            case -3:
            case -2:
            case -1: {
                ((DialogInterface$OnClickListener)message.obj).onClick((DialogInterface)this.mDialog.get(), message.what);
            }
            case 1: {
                ((DialogInterface)message.obj).dismiss();
            }
        }
    }
}
