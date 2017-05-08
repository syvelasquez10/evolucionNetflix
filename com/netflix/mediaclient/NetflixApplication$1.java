// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.common.NetflixCommon$Host;

class NetflixApplication$1 implements NetflixCommon$Host
{
    final /* synthetic */ NetflixApplication this$0;
    
    NetflixApplication$1(final NetflixApplication this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void leaveBreadcrumb(final String s) {
        ErrorLoggingManager.leaveBreadcrumb(s);
    }
    
    @Override
    public void reportException(final Throwable t) {
        ErrorLoggingManager.logHandledException(t);
    }
}
