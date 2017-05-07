// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

public class AlertDialogFactory$AlertDialogDescriptor
{
    String message;
    Runnable posButtonHandler;
    String posButtonLabel;
    String title;
    
    public AlertDialogFactory$AlertDialogDescriptor(final String title, final String message, final String posButtonLabel, final Runnable posButtonHandler) {
        this.title = title;
        this.message = message;
        this.posButtonLabel = posButtonLabel;
        this.posButtonHandler = posButtonHandler;
    }
    
    public String getMessage() {
        return this.message;
    }
}
