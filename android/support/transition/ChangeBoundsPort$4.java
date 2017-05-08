// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.graphics.drawable.Drawable;
import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.drawable.BitmapDrawable;
import android.animation.AnimatorListenerAdapter;

class ChangeBoundsPort$4 extends AnimatorListenerAdapter
{
    final /* synthetic */ ChangeBoundsPort this$0;
    final /* synthetic */ BitmapDrawable val$drawable;
    final /* synthetic */ ViewGroup val$sceneRoot;
    final /* synthetic */ View val$view;
    
    ChangeBoundsPort$4(final ChangeBoundsPort this$0, final ViewGroup val$sceneRoot, final BitmapDrawable val$drawable, final View val$view) {
        this.this$0 = this$0;
        this.val$sceneRoot = val$sceneRoot;
        this.val$drawable = val$drawable;
        this.val$view = val$view;
    }
    
    public void onAnimationEnd(final Animator animator) {
        ViewOverlay.createFrom((View)this.val$sceneRoot).remove((Drawable)this.val$drawable);
        this.val$view.setVisibility(0);
    }
}
