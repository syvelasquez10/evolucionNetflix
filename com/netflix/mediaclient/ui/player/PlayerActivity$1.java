// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class PlayerActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$1(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        ((NetflixFrag)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("PlayerActivity", "NetflixService is NOT available!");
        ((NetflixFrag)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}
