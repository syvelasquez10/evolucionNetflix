// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.util.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class OfflineActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ OfflineActivity this$0;
    
    OfflineActivity$1(final OfflineActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0)) {
            this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
            ((OfflineFragment)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0)) {
            Log.e("nf_offline", "NetflixService is NOT available!");
            ((OfflineFragment)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
        }
    }
}
