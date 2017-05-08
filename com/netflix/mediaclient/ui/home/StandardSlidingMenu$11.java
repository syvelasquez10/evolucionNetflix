// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class StandardSlidingMenu$11 implements Runnable
{
    final /* synthetic */ NetflixActivity val$context;
    
    StandardSlidingMenu$11(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        Log.d("StandardSlidingMenu", "Get autologin token...");
        if (this.val$context.getServiceManager().getService() == null) {
            Log.e("StandardSlidingMenu", "Service is not available!");
            return;
        }
        final AccountHandler accountHandler = new AccountHandler(this.val$context);
        final StandardSlidingMenu$11$1 standardSlidingMenu$11$1 = new StandardSlidingMenu$11$1(this, accountHandler, new NetworkErrorStatus(VolleyUtils.TIMEOUT_ERROR));
        this.val$context.getHandler().postDelayed((Runnable)standardSlidingMenu$11$1, 10000L);
        this.val$context.getServiceManager().createAutoLoginToken(3600000L, new StandardSlidingMenu$11$2(this, standardSlidingMenu$11$1, accountHandler));
    }
}
