// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class NetflixActivity$2 implements View$OnClickListener
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$2(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.d("NetflixActivity", "Handle click on No Network overlay");
        this.this$0.handleActionOnNoNetworkOverlay();
    }
}
