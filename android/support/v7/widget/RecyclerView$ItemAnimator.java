// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.Log;
import android.support.v4.view.ViewCompat;
import java.util.Collections;
import java.lang.ref.WeakReference;
import android.view.View;
import java.util.List;
import java.util.ArrayList;

public abstract class RecyclerView$ItemAnimator
{
    private long mAddDuration;
    private long mChangeDuration;
    private ArrayList<RecyclerView$ItemAnimator$ItemAnimatorFinishedListener> mFinishedListeners;
    private RecyclerView$ItemAnimator$ItemAnimatorListener mListener;
    private long mMoveDuration;
    private long mRemoveDuration;
    
    public RecyclerView$ItemAnimator() {
        this.mListener = null;
        this.mFinishedListeners = new ArrayList<RecyclerView$ItemAnimator$ItemAnimatorFinishedListener>();
        this.mAddDuration = 120L;
        this.mRemoveDuration = 120L;
        this.mMoveDuration = 250L;
        this.mChangeDuration = 250L;
    }
    
    static int buildAdapterChangeFlagsForAnimations(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final int n = recyclerView$ViewHolder.mFlags & 0xE;
        int n2;
        if (recyclerView$ViewHolder.isInvalid()) {
            n2 = 4;
        }
        else {
            n2 = n;
            if ((n & 0x4) == 0x0) {
                final int oldPosition = recyclerView$ViewHolder.getOldPosition();
                final int adapterPosition = recyclerView$ViewHolder.getAdapterPosition();
                n2 = n;
                if (oldPosition != -1) {
                    n2 = n;
                    if (adapterPosition != -1) {
                        n2 = n;
                        if (oldPosition != adapterPosition) {
                            return n | 0x800;
                        }
                    }
                }
            }
        }
        return n2;
    }
    
    public abstract boolean animateAppearance(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    public abstract boolean animateChange(final RecyclerView$ViewHolder p0, final RecyclerView$ViewHolder p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2, final RecyclerView$ItemAnimator$ItemHolderInfo p3);
    
    public abstract boolean animateDisappearance(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    public abstract boolean animatePersistence(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    public boolean canReuseUpdatedViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return true;
    }
    
    public boolean canReuseUpdatedViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final List<Object> list) {
        return this.canReuseUpdatedViewHolder(recyclerView$ViewHolder);
    }
    
    public final void dispatchAnimationFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onAnimationFinished(recyclerView$ViewHolder);
        if (this.mListener != null) {
            this.mListener.onAnimationFinished(recyclerView$ViewHolder);
        }
    }
    
    public final void dispatchAnimationsFinished() {
        for (int size = this.mFinishedListeners.size(), i = 0; i < size; ++i) {
            this.mFinishedListeners.get(i).onAnimationsFinished();
        }
        this.mFinishedListeners.clear();
    }
    
    public abstract void endAnimation(final RecyclerView$ViewHolder p0);
    
    public abstract void endAnimations();
    
    public long getAddDuration() {
        return this.mAddDuration;
    }
    
    public long getChangeDuration() {
        return this.mChangeDuration;
    }
    
    public long getMoveDuration() {
        return this.mMoveDuration;
    }
    
    public long getRemoveDuration() {
        return this.mRemoveDuration;
    }
    
    public abstract boolean isRunning();
    
    public RecyclerView$ItemAnimator$ItemHolderInfo obtainHolderInfo() {
        return new RecyclerView$ItemAnimator$ItemHolderInfo();
    }
    
    public void onAnimationFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public RecyclerView$ItemAnimator$ItemHolderInfo recordPostLayoutInformation(final RecyclerView$State recyclerView$State, final RecyclerView$ViewHolder from) {
        return this.obtainHolderInfo().setFrom(from);
    }
    
    public RecyclerView$ItemAnimator$ItemHolderInfo recordPreLayoutInformation(final RecyclerView$State recyclerView$State, final RecyclerView$ViewHolder from, final int n, final List<Object> list) {
        return this.obtainHolderInfo().setFrom(from);
    }
    
    public abstract void runPendingAnimations();
    
    void setListener(final RecyclerView$ItemAnimator$ItemAnimatorListener mListener) {
        this.mListener = mListener;
    }
}
