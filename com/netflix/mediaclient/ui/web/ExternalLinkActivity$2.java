// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.web;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class ExternalLinkActivity$2 implements ManagerStatusListener
{
    final /* synthetic */ ExternalLinkActivity this$0;
    
    ExternalLinkActivity$2(final ExternalLinkActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
}
