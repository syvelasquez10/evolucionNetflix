// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ServiceErrorsHandler$3(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.val$activity.finish();
    }
}
