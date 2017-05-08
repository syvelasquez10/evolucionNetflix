// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

class NetflixActivity$DefaultManagerStatusListener$1 implements Runnable
{
    final /* synthetic */ NetflixActivity$DefaultManagerStatusListener this$1;
    
    NetflixActivity$DefaultManagerStatusListener$1(final NetflixActivity$DefaultManagerStatusListener this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.expandCastPlayerIfVisible();
    }
}
