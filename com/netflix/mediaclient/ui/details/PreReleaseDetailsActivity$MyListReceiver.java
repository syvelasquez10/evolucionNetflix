// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Bundle;
import android.app.Fragment;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar$SnackbarLayout;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class PreReleaseDetailsActivity$MyListReceiver extends BroadcastReceiver
{
    public static final String MYLIST_ADD_INTENT = "com.netflix.mediaclient.mylist.intent.action.ADD";
    final /* synthetic */ PreReleaseDetailsActivity this$0;
    
    public PreReleaseDetailsActivity$MyListReceiver(final PreReleaseDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction() == "com.netflix.mediaclient.mylist.intent.action.ADD") {
            this.this$0.showSnackBar();
        }
    }
}
