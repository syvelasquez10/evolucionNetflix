// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.AnimationDrawable;

class MdxMenu$2 implements Runnable
{
    final /* synthetic */ MdxMenu this$0;
    final /* synthetic */ AnimationDrawable val$icon;
    
    MdxMenu$2(final MdxMenu this$0, final AnimationDrawable val$icon) {
        this.this$0 = this$0;
        this.val$icon = val$icon;
    }
    
    @Override
    public void run() {
        this.val$icon.start();
    }
}
