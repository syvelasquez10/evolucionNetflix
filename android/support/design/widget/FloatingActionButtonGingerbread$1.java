// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Animation;

class FloatingActionButtonGingerbread$1 extends AnimationUtils$AnimationListenerAdapter
{
    final /* synthetic */ FloatingActionButtonGingerbread this$0;
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener;
    
    FloatingActionButtonGingerbread$1(final FloatingActionButtonGingerbread this$0, final boolean val$fromUser, final FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener) {
        this.this$0 = this$0;
        this.val$fromUser = val$fromUser;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        this.this$0.mAnimState = 0;
        this.this$0.mView.internalSetVisibility(8, this.val$fromUser);
        if (this.val$listener != null) {
            this.val$listener.onHidden();
        }
    }
}
