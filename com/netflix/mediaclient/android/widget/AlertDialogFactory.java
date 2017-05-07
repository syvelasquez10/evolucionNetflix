// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface$OnClickListener;
import android.os.Handler;
import android.content.Context;

public final class AlertDialogFactory
{
    public static UpdateDialog$Builder createDialog(final Context context, final Handler handler, final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        if (alertDialogFactory$AlertDialogDescriptor instanceof AlertDialogFactory$TwoButtonAlertDialogDescriptor) {
            final AlertDialogFactory$TwoButtonAlertDialogDescriptor alertDialogFactory$TwoButtonAlertDialogDescriptor = (AlertDialogFactory$TwoButtonAlertDialogDescriptor)alertDialogFactory$AlertDialogDescriptor;
            return createDialog(context, alertDialogFactory$TwoButtonAlertDialogDescriptor.title, alertDialogFactory$TwoButtonAlertDialogDescriptor.message, handler, alertDialogFactory$TwoButtonAlertDialogDescriptor.posButtonLabel, alertDialogFactory$TwoButtonAlertDialogDescriptor.posButtonHandler, alertDialogFactory$TwoButtonAlertDialogDescriptor.negButtonLabel, alertDialogFactory$TwoButtonAlertDialogDescriptor.negButtonHandler, false);
        }
        return createOneButtonDialog(context, alertDialogFactory$AlertDialogDescriptor.title, alertDialogFactory$AlertDialogDescriptor.message, handler, alertDialogFactory$AlertDialogDescriptor.posButtonLabel, alertDialogFactory$AlertDialogDescriptor.posButtonHandler);
    }
    
    public static UpdateDialog$Builder createDialog(final Context context, final String s, final String s2, final Handler handler, String string, final Runnable runnable, String string2, final Runnable runnable2, final boolean b) {
        final UpdateDialog$Builder updateDialog$Builder = new UpdateDialog$Builder(context);
        updateDialog$Builder.setTitle(noNull(s));
        updateDialog$Builder.setMessage(noNull(s2));
        if (string == null) {
            string = context.getString(2131492988);
        }
        if (string2 == null) {
            string2 = context.getString(2131493108);
        }
        if (handler != null) {
            updateDialog$Builder.setPositiveButton(string, (DialogInterface$OnClickListener)new AlertDialogFactory$1(runnable, handler));
            if (!b) {
                updateDialog$Builder.setNegativeButton(string2, (DialogInterface$OnClickListener)new AlertDialogFactory$2(runnable2, handler));
            }
        }
        else {
            updateDialog$Builder.setPositiveButton(string, null);
            if (!b) {
                updateDialog$Builder.setNegativeButton(string2, null);
                return updateDialog$Builder;
            }
        }
        return updateDialog$Builder;
    }
    
    public static UpdateDialog$Builder createOneButtonDialog(final Context context, final String s, final String s2, final Handler handler, final String s3, final Runnable runnable) {
        return createDialog(context, s, s2, handler, s3, runnable, null, null, true);
    }
    
    private static String noNull(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
}
