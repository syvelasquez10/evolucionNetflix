// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.AndroidUtils;

class WidevineDrmManager$3 implements Runnable
{
    final /* synthetic */ WidevineDrmManager this$0;
    
    WidevineDrmManager$3(final WidevineDrmManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mUser.logoutUser();
        AndroidUtils.clearApplicationData(this.this$0.mContext);
    }
}
