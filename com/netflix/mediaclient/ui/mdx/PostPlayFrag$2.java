// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.View;
import android.view.View$OnClickListener;

class PostPlayFrag$2 implements View$OnClickListener
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$2(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.handleStop();
    }
}
