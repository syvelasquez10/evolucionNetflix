// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

import android.view.Window;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.app.Activity;
import com.android.volley.toolbox.ControlledInputStream;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Handler;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.view.View$OnClickListener;

class NetworkSpeedControl$2 implements View$OnClickListener
{
    final /* synthetic */ NetworkSpeedControl this$0;
    final /* synthetic */ AlertDialog val$chartHolder;
    
    NetworkSpeedControl$2(final NetworkSpeedControl this$0, final AlertDialog val$chartHolder) {
        this.this$0 = this$0;
        this.val$chartHolder = val$chartHolder;
    }
    
    public void onClick(final View view) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.this$0.mNetflixActivity);
        alertDialog$Builder.setTitle(2131297102);
        alertDialog$Builder.setPositiveButton(2131297008, (DialogInterface$OnClickListener)new NetworkSpeedControl$2$1(this));
        alertDialog$Builder.setNegativeButton(2131297007, (DialogInterface$OnClickListener)new NetworkSpeedControl$2$2(this));
        alertDialog$Builder.create().show();
    }
}
