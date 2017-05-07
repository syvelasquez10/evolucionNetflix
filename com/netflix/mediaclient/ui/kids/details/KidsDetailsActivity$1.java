// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class KidsDetailsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ KidsDetailsActivity this$0;
    
    KidsDetailsActivity$1(final KidsDetailsActivity this$0) {
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
