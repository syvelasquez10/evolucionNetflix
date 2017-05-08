// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Animation;

class FloatingActionButtonGingerbread$2 extends AnimationUtils$AnimationListenerAdapter
{
    final /* synthetic */ FloatingActionButtonGingerbread this$0;
    final /* synthetic */ FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener;
    
    FloatingActionButtonGingerbread$2(final FloatingActionButtonGingerbread this$0, final FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        this.this$0.mAnimState = 0;
        if (this.val$listener != null) {
            this.val$listener.onShown();
        }
    }
}
