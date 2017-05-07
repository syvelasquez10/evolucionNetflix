// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

public class AlertDialogFactory$TwoButtonAlertDialogDescriptor extends AlertDialogFactory$AlertDialogDescriptor
{
    Runnable negButtonHandler;
    String negButtonLabel;
    
    public AlertDialogFactory$TwoButtonAlertDialogDescriptor(final String s, final String s2, final String s3, final Runnable runnable, final String negButtonLabel, final Runnable negButtonHandler) {
        super(s, s2, s3, runnable);
        this.negButtonLabel = negButtonLabel;
        this.negButtonHandler = negButtonHandler;
    }
}
