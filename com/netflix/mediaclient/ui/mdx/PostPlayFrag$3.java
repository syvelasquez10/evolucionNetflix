// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlayFrag$3 implements View$OnClickListener
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$3(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.getActivity().sendBroadcast(new Intent("com.netflix.mediaclient.service.ACTION_CLOSE_MINI_PLAYER"));
    }
}
