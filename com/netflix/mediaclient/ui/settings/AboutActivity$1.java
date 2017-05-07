// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.util.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class AboutActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ AboutActivity this$0;
    
    AboutActivity$1(final AboutActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("nf_AboutActivity", "NetflixService is NOT available!");
    }
}
