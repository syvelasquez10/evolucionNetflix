// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

class UIManagerModule$1$1 implements Runnable
{
    final /* synthetic */ UIManagerModule$1 this$1;
    final /* synthetic */ int val$height;
    final /* synthetic */ int val$width;
    
    UIManagerModule$1$1(final UIManagerModule$1 this$1, final int val$width, final int val$height) {
        this.this$1 = this$1;
        this.val$width = val$width;
        this.val$height = val$height;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.updateNodeSize(this.this$1.val$tag, this.val$width, this.val$height);
    }
}
