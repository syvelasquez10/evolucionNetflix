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
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsActivity;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class BarkerPreReleaseDetailsActivity$MyListReceiver extends BroadcastReceiver
{
    public static final String MYLIST_ADD_INTENT = "com.netflix.mediaclient.mylist.intent.action.ADD";
    final /* synthetic */ BarkerPreReleaseDetailsActivity this$0;
    
    public BarkerPreReleaseDetailsActivity$MyListReceiver(final BarkerPreReleaseDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction() == "com.netflix.mediaclient.mylist.intent.action.ADD") {
            this.this$0.showSnackBar();
        }
    }
}
