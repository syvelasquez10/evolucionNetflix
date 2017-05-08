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
import android.app.Activity;
import com.android.volley.toolbox.ControlledInputStream;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Handler;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class NetworkSpeedControl$2$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ NetworkSpeedControl$2 this$1;
    
    NetworkSpeedControl$2$1(final NetworkSpeedControl$2 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.this$1.this$0.killChart(this.this$1.val$chartHolder);
        dialogInterface.dismiss();
    }
}
