// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class KubrickKidsDetailsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ KubrickKidsDetailsActivity this$0;
    
    KubrickKidsDetailsActivity$1(final KubrickKidsDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}
