// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.Context;
import android.app.ProgressDialog;

public class UpdateProgressDialog extends ProgressDialog
{
    public UpdateProgressDialog(final Context context) {
        super(context);
        this.setCancelable(false);
    }
    
    public UpdateProgressDialog(final Context context, final int n) {
        super(context, n);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }
    
    public boolean onSearchRequested() {
        return false;
    }
}
