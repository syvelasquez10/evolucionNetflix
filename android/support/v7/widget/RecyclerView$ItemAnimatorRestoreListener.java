// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.ViewGroup;

class RecyclerView$ItemAnimatorRestoreListener implements RecyclerView$ItemAnimator$ItemAnimatorListener
{
    final /* synthetic */ RecyclerView this$0;
    
    private RecyclerView$ItemAnimatorRestoreListener(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAddFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        recyclerView$ViewHolder.setIsRecyclable(true);
        if (recyclerView$ViewHolder.isRecyclable()) {
            this.this$0.removeAnimatingView(recyclerView$ViewHolder.itemView);
        }
    }
    
    @Override
    public void onChangeFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        recyclerView$ViewHolder.setIsRecyclable(true);
        if (recyclerView$ViewHolder.mShadowedHolder != null && recyclerView$ViewHolder.mShadowingHolder == null) {
            recyclerView$ViewHolder.mShadowedHolder = null;
            recyclerView$ViewHolder.setFlags(-65, recyclerView$ViewHolder.mFlags);
        }
        recyclerView$ViewHolder.mShadowingHolder = null;
        if (recyclerView$ViewHolder.isRecyclable()) {
            this.this$0.removeAnimatingView(recyclerView$ViewHolder.itemView);
        }
    }
    
    @Override
    public void onMoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        recyclerView$ViewHolder.setIsRecyclable(true);
        if (recyclerView$ViewHolder.isRecyclable()) {
            this.this$0.removeAnimatingView(recyclerView$ViewHolder.itemView);
        }
    }
    
    @Override
    public void onRemoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        recyclerView$ViewHolder.setIsRecyclable(true);
        if (!this.this$0.removeAnimatingView(recyclerView$ViewHolder.itemView) && recyclerView$ViewHolder.isTmpDetached()) {
            this.this$0.removeDetachedView(recyclerView$ViewHolder.itemView, false);
        }
    }
}
