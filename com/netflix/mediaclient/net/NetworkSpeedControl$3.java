// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

import android.view.Window;
import android.view.View$OnClickListener;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Activity;
import com.android.volley.toolbox.ControlledInputStream;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.support.v7.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class NetworkSpeedControl$3 implements Runnable
{
    final /* synthetic */ NetworkSpeedControl this$0;
    
    NetworkSpeedControl$3(final NetworkSpeedControl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.mNetflixActivity)) {
            return;
        }
        this.this$0.showSpeedChart();
    }
}
