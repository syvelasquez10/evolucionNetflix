// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;

class NetflixActivity$16 implements Runnable
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$16(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.startActivity(ProfileSelectionActivity.createStartIntentSingleTop((Context)this.this$0.getNetflixApplication()));
        this.this$0.finish();
    }
}
