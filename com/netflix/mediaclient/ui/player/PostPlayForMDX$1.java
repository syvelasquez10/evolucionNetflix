// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.model.leafs.PostPlayAction;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.Log;

class PostPlayForMDX$1 implements Runnable
{
    final /* synthetic */ PostPlayForMDX this$0;
    
    PostPlayForMDX$1(final PostPlayForMDX this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mInPostPlay || this.this$0.mNetflixActivity.isFinishing()) {
            Log.d("nf_postplay", "post play timer exit or activity is destroyed");
            return;
        }
        this.this$0.mTimer--;
        this.this$0.refreshTimerText();
        if (this.this$0.mTimer <= 0) {
            Log.d("nf_postplay", "Timer counter to 0, play next episode");
            this.this$0.onTimerEnd();
            return;
        }
        this.this$0.mNetflixActivity.getHandler().postDelayed((Runnable)this, 1000L);
    }
}
