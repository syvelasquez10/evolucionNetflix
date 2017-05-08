// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import android.view.View$OnClickListener;

class PostPlay$4 implements View$OnClickListener
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$4(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.moveFromInterruptedToPlayingFromStart();
    }
}
