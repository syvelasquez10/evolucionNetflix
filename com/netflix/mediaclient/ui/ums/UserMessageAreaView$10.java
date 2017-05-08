// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaCta;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class UserMessageAreaView$10 extends SimpleManagerCallback
{
    final /* synthetic */ UserMessageAreaView this$0;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Runnable val$timeout;
    final /* synthetic */ UmaCta val$umaCta;
    
    UserMessageAreaView$10(final UserMessageAreaView this$0, final Runnable val$timeout, final AccountHandler val$handler, final UmaCta val$umaCta) {
        this.this$0 = this$0;
        this.val$timeout = val$timeout;
        this.val$handler = val$handler;
        this.val$umaCta = val$umaCta;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.this$0.getContext(), NetflixActivity.class);
        if (netflixActivity != null && !AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            netflixActivity.getHandler().removeCallbacks(this.val$timeout);
            this.val$handler.handle(s, status, this.val$umaCta.action());
        }
    }
}
