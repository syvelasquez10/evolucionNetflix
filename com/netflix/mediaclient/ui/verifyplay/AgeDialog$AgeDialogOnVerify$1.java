// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class AgeDialog$AgeDialogOnVerify$1 implements Runnable
{
    final /* synthetic */ AgeDialog$AgeDialogOnVerify this$1;
    final /* synthetic */ NetflixActivity val$context;
    
    AgeDialog$AgeDialogOnVerify$1(final AgeDialog$AgeDialogOnVerify this$1, final NetflixActivity val$context) {
        this.this$1 = this$1;
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        Log.d("nf_age", "Get autologin token...");
        final AccountHandler accountHandler = new AccountHandler(this.val$context);
        final AgeDialog$AgeDialogOnVerify$1$1 ageDialog$AgeDialogOnVerify$1$1 = new AgeDialog$AgeDialogOnVerify$1$1(this, accountHandler, new NetworkErrorStatus(VolleyUtils.TIMEOUT_ERROR));
        this.val$context.getHandler().postDelayed((Runnable)ageDialog$AgeDialogOnVerify$1$1, 10000L);
        this.val$context.getServiceManager().createAutoLoginToken(3600000L, new AgeDialog$AgeDialogOnVerify$1$2(this, ageDialog$AgeDialogOnVerify$1$1, accountHandler));
    }
}
