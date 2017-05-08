// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

class KongInteractivePostPlayManager$10$1 implements Runnable
{
    final /* synthetic */ KongInteractivePostPlayManager$10 this$1;
    
    KongInteractivePostPlayManager$10$1(final KongInteractivePostPlayManager$10 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.loadPostPlayResources();
    }
}
