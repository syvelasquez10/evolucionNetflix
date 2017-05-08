// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.Log;

class EogLandingPage$1$1 implements Runnable
{
    final /* synthetic */ EogLandingPage$1 this$0;
    
    EogLandingPage$1$1(final EogLandingPage$1 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("eog_landing", "Get autologin token...");
        final AccountHandler accountHandler = new AccountHandler(this.this$0.val$context);
        final EogLandingPage$1$1$1 eogLandingPage$1$1$1 = new EogLandingPage$1$1$1(this, accountHandler, new NetworkErrorStatus(VolleyUtils.TIMEOUT_ERROR));
        this.this$0.val$context.getHandler().postDelayed((Runnable)eogLandingPage$1$1$1, 10000L);
        this.this$0.val$context.getServiceManager().createAutoLoginToken(3600000L, new EogLandingPage$1$1$2(this, eogLandingPage$1$1$1, accountHandler));
    }
}
