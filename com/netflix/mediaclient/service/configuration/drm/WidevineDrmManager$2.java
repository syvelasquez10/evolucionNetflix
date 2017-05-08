// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.Log;

class WidevineDrmManager$2 implements Runnable
{
    final /* synthetic */ WidevineDrmManager this$0;
    
    WidevineDrmManager$2(final WidevineDrmManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mUser.logoutUser();
        Log.d(WidevineDrmManager.TAG, "Redo CDM provisioning");
        this.this$0.init();
    }
}
