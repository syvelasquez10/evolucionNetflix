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
import android.support.v7.app.AlertDialog$Builder;
import com.android.volley.toolbox.ControlledInputStream;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.support.v7.app.AlertDialog;
import android.os.Handler;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.DialogInterface$OnClickListener;

final class NetworkSpeedControl$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    
    NetworkSpeedControl$5(final NetflixActivity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        setSelectedSpeedIndex(n, (Context)this.val$activity);
        dialogInterface.dismiss();
        AndroidUtils.restartApplication(this.val$activity);
    }
}
