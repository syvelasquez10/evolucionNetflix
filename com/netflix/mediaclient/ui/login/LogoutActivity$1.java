// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class LogoutActivity$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    LogoutActivity$1(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.val$activity.startActivity(LogoutActivity.create((Context)this.val$activity));
        this.val$activity.overridePendingTransition(0, 0);
        this.val$activity.finish();
    }
}
