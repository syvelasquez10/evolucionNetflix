// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.error.ErrorDescriptor;

class NetflixActivity$19 implements Runnable
{
    final /* synthetic */ NetflixActivity this$0;
    final /* synthetic */ ErrorDescriptor val$error;
    final /* synthetic */ ServiceManager val$sm;
    
    NetflixActivity$19(final NetflixActivity this$0, final ServiceManager val$sm, final ErrorDescriptor val$error) {
        this.this$0 = this$0;
        this.val$sm = val$sm;
        this.val$error = val$error;
    }
    
    @Override
    public void run() {
        this.val$sm.getErrorHandler().setErrorAccepted(this.val$error);
    }
}
