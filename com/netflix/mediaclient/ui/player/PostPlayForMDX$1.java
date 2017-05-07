// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import android.view.View$OnClickListener;

class PostPlayForMDX$1 implements View$OnClickListener
{
    final /* synthetic */ PostPlayForMDX this$0;
    
    PostPlayForMDX$1(final PostPlayForMDX this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.handleStop();
    }
}
