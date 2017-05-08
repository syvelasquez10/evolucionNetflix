// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import android.support.v4.animation.AnimatorCompatHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class DefaultItemAnimator$5 extends DefaultItemAnimator$VpaListenerAdapter
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ RecyclerView$ViewHolder val$holder;
    
    DefaultItemAnimator$5(final DefaultItemAnimator this$0, final RecyclerView$ViewHolder val$holder, final ViewPropertyAnimatorCompat val$animation) {
        this.this$0 = this$0;
        this.val$holder = val$holder;
        this.val$animation = val$animation;
        super(null);
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        ViewCompat.setAlpha(view, 1.0f);
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        this.this$0.dispatchAddFinished(this.val$holder);
        this.this$0.mAddAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.dispatchAddStarting(this.val$holder);
    }
}
