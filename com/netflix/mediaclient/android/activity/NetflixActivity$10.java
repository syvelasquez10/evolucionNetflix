// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

class NetflixActivity$10 implements Runnable
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$10(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.slidingPanel.expandPane();
    }
}
