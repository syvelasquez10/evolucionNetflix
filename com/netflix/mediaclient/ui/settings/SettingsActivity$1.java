// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class SettingsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ SettingsActivity this$0;
    
    SettingsActivity$1(final SettingsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
        ((SettingsFragment)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("nf_settings", "NetflixService is NOT available!");
        ((SettingsFragment)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}
