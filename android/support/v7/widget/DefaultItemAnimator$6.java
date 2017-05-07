// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class DefaultItemAnimator$6 extends DefaultItemAnimator$VpaListenerAdapter
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ int val$deltaX;
    final /* synthetic */ int val$deltaY;
    final /* synthetic */ RecyclerView$ViewHolder val$holder;
    
    DefaultItemAnimator$6(final DefaultItemAnimator this$0, final RecyclerView$ViewHolder val$holder, final int val$deltaX, final int val$deltaY, final ViewPropertyAnimatorCompat val$animation) {
        this.this$0 = this$0;
        this.val$holder = val$holder;
        this.val$deltaX = val$deltaX;
        this.val$deltaY = val$deltaY;
        this.val$animation = val$animation;
        super(null);
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        if (this.val$deltaX != 0) {
            ViewCompat.setTranslationX(view, 0.0f);
        }
        if (this.val$deltaY != 0) {
            ViewCompat.setTranslationY(view, 0.0f);
        }
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$animation.setListener(null);
        this.this$0.dispatchMoveFinished(this.val$holder);
        this.this$0.mMoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.dispatchMoveStarting(this.val$holder);
    }
}
