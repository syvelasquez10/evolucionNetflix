// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.text.util.Linkify;
import android.text.SpannableString;
import com.netflix.mediaclient.util.StringUtils;
import android.text.Spannable;
import android.content.DialogInterface$OnClickListener;
import android.os.Handler;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.app.Dialog;

public final class AlertDialogFactory
{
    public static void activateLinkIfExist(final Dialog dialog) {
        final TextView textView = (TextView)dialog.findViewById(16908299);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    
    public static UpdateDialog$Builder createDialog(final Context context, final Handler handler, final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        return createDialog(context, handler, alertDialogFactory$AlertDialogDescriptor, null);
    }
    
    public static UpdateDialog$Builder createDialog(final Context context, final Handler handler, final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor, final Runnable runnable) {
        if (alertDialogFactory$AlertDialogDescriptor instanceof AlertDialogFactory$TwoButtonAlertDialogDescriptor) {
            final AlertDialogFactory$TwoButtonAlertDialogDescriptor alertDialogFactory$TwoButtonAlertDialogDescriptor = (AlertDialogFactory$TwoButtonAlertDialogDescriptor)alertDialogFactory$AlertDialogDescriptor;
            return createDialog(context, alertDialogFactory$TwoButtonAlertDialogDescriptor.title, alertDialogFactory$TwoButtonAlertDialogDescriptor.message, handler, alertDialogFactory$TwoButtonAlertDialogDescriptor.posButtonLabel, alertDialogFactory$TwoButtonAlertDialogDescriptor.posButtonHandler, alertDialogFactory$TwoButtonAlertDialogDescriptor.negButtonLabel, alertDialogFactory$TwoButtonAlertDialogDescriptor.negButtonHandler, false, runnable);
        }
        return createOneButtonDialog(context, alertDialogFactory$AlertDialogDescriptor.title, alertDialogFactory$AlertDialogDescriptor.message, handler, alertDialogFactory$AlertDialogDescriptor.posButtonLabel, alertDialogFactory$AlertDialogDescriptor.posButtonHandler, runnable);
    }
    
    public static UpdateDialog$Builder createDialog(final Context context, final String s, final String s2, final Handler handler, String string, final Runnable runnable, String string2, final Runnable runnable2, final boolean b, final Runnable runnable3) {
        final UpdateDialog$Builder updateDialog$Builder = new UpdateDialog$Builder(context);
        updateDialog$Builder.setTitle(noNull(s));
        updateDialog$Builder.setMessage((CharSequence)processMessage(s2));
        if (string == null) {
            string = context.getString(2131165588);
        }
        if (string2 == null) {
            string2 = context.getString(2131165445);
        }
        if (handler != null) {
            updateDialog$Builder.setPositiveButton(string, (DialogInterface$OnClickListener)new AlertDialogFactory$1(runnable, handler, runnable3));
            if (!b) {
                updateDialog$Builder.setNegativeButton(string2, (DialogInterface$OnClickListener)new AlertDialogFactory$2(runnable2, handler, runnable3));
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
    
    public static UpdateDialog$Builder createOneButtonDialog(final Context context, final String s, final String s2, final Handler handler, final String s3, final Runnable runnable, final Runnable runnable2) {
        return createDialog(context, s, s2, handler, s3, runnable, null, null, true, runnable2);
    }
    
    private static String noNull(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    private static Spannable processMessage(final String s) {
        if (StringUtils.isEmpty(s)) {
            return (Spannable)new SpannableString((CharSequence)"");
        }
        final SpannableString spannableString = new SpannableString((CharSequence)s);
        Linkify.addLinks((Spannable)spannableString, 15);
        return (Spannable)spannableString;
    }
}
