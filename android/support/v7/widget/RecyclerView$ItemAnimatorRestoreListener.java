// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.Log;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.ref.WeakReference;
import android.view.View;
import java.util.List;

class RecyclerView$ItemAnimatorRestoreListener implements RecyclerView$ItemAnimator$ItemAnimatorListener
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$ItemAnimatorRestoreListener(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        recyclerView$ViewHolder.setIsRecyclable(true);
        if (recyclerView$ViewHolder.mShadowedHolder != null && recyclerView$ViewHolder.mShadowingHolder == null) {
            recyclerView$ViewHolder.mShadowedHolder = null;
        }
        recyclerView$ViewHolder.mShadowingHolder = null;
        if (!recyclerView$ViewHolder.shouldBeKeptAsChild() && !this.this$0.removeAnimatingView(recyclerView$ViewHolder.itemView) && recyclerView$ViewHolder.isTmpDetached()) {
            this.this$0.removeDetachedView(recyclerView$ViewHolder.itemView, false);
        }
    }
}
