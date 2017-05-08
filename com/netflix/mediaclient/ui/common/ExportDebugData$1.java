// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class ExportDebugData$1 implements Runnable
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ Toast val$toast;
    
    ExportDebugData$1(final Toast val$toast, final NetflixActivity val$activity) {
        this.val$toast = val$toast;
        this.val$activity = val$activity;
    }
    
    @Override
    public void run() {
        this.val$toast.cancel();
        Toast.makeText((Context)this.val$activity, (CharSequence)"Enter your JIRA summary and description in the email", 0).show();
        ExportDebugData.createBugReport((Context)this.val$activity, null, null);
    }
}
