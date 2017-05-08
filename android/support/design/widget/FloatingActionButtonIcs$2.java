// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingActionButtonIcs$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ FloatingActionButtonIcs this$0;
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener;
    
    FloatingActionButtonIcs$2(final FloatingActionButtonIcs this$0, final boolean val$fromUser, final FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener) {
        this.this$0 = this$0;
        this.val$fromUser = val$fromUser;
        this.val$listener = val$listener;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.mAnimState = 0;
        if (this.val$listener != null) {
            this.val$listener.onShown();
        }
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.mView.internalSetVisibility(0, this.val$fromUser);
    }
}
