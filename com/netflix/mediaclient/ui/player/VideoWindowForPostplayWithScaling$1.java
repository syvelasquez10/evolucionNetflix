// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

class VideoWindowForPostplayWithScaling$1 implements Runnable
{
    final /* synthetic */ VideoWindowForPostplayWithScaling this$0;
    final /* synthetic */ int val$leftMargin;
    final /* synthetic */ float val$scale;
    final /* synthetic */ int val$topMargin;
    
    VideoWindowForPostplayWithScaling$1(final VideoWindowForPostplayWithScaling this$0, final int val$leftMargin, final int val$topMargin, final float val$scale) {
        this.this$0 = this$0;
        this.val$leftMargin = val$leftMargin;
        this.val$topMargin = val$topMargin;
        this.val$scale = val$scale;
    }
    
    @Override
    public void run() {
        this.this$0.resizeSurfaceView(this.val$leftMargin, this.val$topMargin, this.val$scale);
        this.this$0.resizeTextureView(this.val$leftMargin, this.val$topMargin);
    }
}
