// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class NetflixActivity$13$1 implements Runnable
{
    final /* synthetic */ NetflixActivity$13 this$1;
    
    NetflixActivity$13$1(final NetflixActivity$13 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        AndroidUtils.forceStop((Context)this.this$1.this$0);
    }
}
