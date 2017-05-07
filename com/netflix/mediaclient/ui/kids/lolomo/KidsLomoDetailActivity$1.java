// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class KidsLomoDetailActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ KidsLomoDetailActivity this$0;
    
    KidsLomoDetailActivity$1(final KidsLomoDetailActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getPrimaryFrag().onManagerReady(serviceManager, status);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.this$0.getPrimaryFrag().onManagerUnavailable(serviceManager, status);
    }
}
