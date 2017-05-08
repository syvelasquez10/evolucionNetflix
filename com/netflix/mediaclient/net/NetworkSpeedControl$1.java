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
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Handler;
import com.android.volley.toolbox.ControlledInputStream;

class NetworkSpeedControl$1 implements Runnable
{
    final /* synthetic */ NetworkSpeedControl this$0;
    final /* synthetic */ NetworkSpeedChart val$lineChart;
    
    NetworkSpeedControl$1(final NetworkSpeedControl this$0, final NetworkSpeedChart val$lineChart) {
        this.this$0 = this$0;
        this.val$lineChart = val$lineChart;
    }
    
    @Override
    public void run() {
        long network_SPEED_BYTES_PER_SECOND = ControlledInputStream.NETWORK_SPEED_BYTES_PER_SECOND;
        if (System.currentTimeMillis() - ControlledInputStream.TIME_SPEED_MEASURED > 2000L) {
            network_SPEED_BYTES_PER_SECOND = 0L;
        }
        leftShitByOne(NetworkSpeedControl.gNetworkSpeedHistory);
        NetworkSpeedControl.gNetworkSpeedHistory[59] = network_SPEED_BYTES_PER_SECOND;
        this.val$lineChart.invalidate();
        this.this$0.mMainHandler.postDelayed((Runnable)this, 1000L);
    }
}
