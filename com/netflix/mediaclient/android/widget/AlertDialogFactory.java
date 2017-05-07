// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.os.Handler;
import android.content.Context;

public final class AlertDialogFactory
{
    public static UpdateDialog.Builder createDialog(final Context context, final Handler handler, final AlertDialogDescriptor alertDialogDescriptor) {
        if (alertDialogDescriptor instanceof TwoButtonAlertDialogDescriptor) {
            final TwoButtonAlertDialogDescriptor twoButtonAlertDialogDescriptor = (TwoButtonAlertDialogDescriptor)alertDialogDescriptor;
            return createDialog(context, twoButtonAlertDialogDescriptor.title, twoButtonAlertDialogDescriptor.message, handler, twoButtonAlertDialogDescriptor.posButtonLabel, twoButtonAlertDialogDescriptor.posButtonHandler, twoButtonAlertDialogDescriptor.negButtonLabel, twoButtonAlertDialogDescriptor.negButtonHandler, false);
        }
        return createOneButtonDialog(context, alertDialogDescriptor.title, alertDialogDescriptor.message, handler, alertDialogDescriptor.posButtonLabel, alertDialogDescriptor.posButtonHandler);
    }
    
    public static UpdateDialog.Builder createDialog(final Context context, String string, final String s, final Handler handler, final String s2, final Runnable runnable, String string2, final Runnable runnable2, final boolean b) {
        final UpdateDialog.Builder builder = new UpdateDialog.Builder(context);
        builder.setTitle(noNull(string));
        builder.setMessage(noNull(s));
        if (s2 != null) {
            string = s2;
        }
        else {
            string = context.getString(2131492983);
        }
        if (string2 == null) {
            string2 = context.getString(2131493127);
        }
        if (handler != null) {
            builder.setPositiveButton(string, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    if (runnable != null) {
                        handler.post(runnable);
                    }
                }
            });
            if (!b) {
                builder.setNegativeButton(string2, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        if (runnable2 != null) {
                            handler.post(runnable2);
                        }
                    }
                });
            }
        }
        else {
            builder.setPositiveButton(string, null);
            if (!b) {
                builder.setNegativeButton(string2, null);
                return builder;
            }
        }
        return builder;
    }
    
    public static UpdateDialog.Builder createOneButtonDialog(final Context context, final String s, final String s2, final Handler handler, final String s3, final Runnable runnable) {
        return createDialog(context, s, s2, handler, s3, runnable, null, null, true);
    }
    
    private static String noNull(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    public static class AlertDialogDescriptor
    {
        String message;
        Runnable posButtonHandler;
        String posButtonLabel;
        String title;
        
        public AlertDialogDescriptor(final String title, final String message, final String posButtonLabel, final Runnable posButtonHandler) {
            this.title = title;
            this.message = message;
            this.posButtonLabel = posButtonLabel;
            this.posButtonHandler = posButtonHandler;
        }
        
        public String getMessage() {
            return this.message;
        }
    }
    
    public static class TwoButtonAlertDialogDescriptor extends AlertDialogDescriptor
    {
        Runnable negButtonHandler;
        String negButtonLabel;
        
        public TwoButtonAlertDialogDescriptor(final String s, final String s2, final String s3, final Runnable runnable, final String negButtonLabel, final Runnable negButtonHandler) {
            super(s, s2, s3, runnable);
            this.negButtonLabel = negButtonLabel;
            this.negButtonHandler = negButtonHandler;
        }
    }
}
