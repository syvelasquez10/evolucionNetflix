// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import android.support.v4.animation.AnimatorCompatHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class DefaultItemAnimator$4 extends DefaultItemAnimator$VpaListenerAdapter
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ RecyclerView$ViewHolder val$holder;
    
    DefaultItemAnimator$4(final DefaultItemAnimator this$0, final RecyclerView$ViewHolder val$holder, final ViewPropertyAnimatorCompat val$animation) {
        this.this$0 = this$0;
        this.val$holder = val$holder;
        this.val$animation = val$animation;
        super(null);
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        ViewCompat.setAlpha(view, 1.0f);
        this.this$0.dispatchRemoveFinished(this.val$holder);
        this.this$0.mRemoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.dispatchRemoveStarting(this.val$holder);
    }
}
