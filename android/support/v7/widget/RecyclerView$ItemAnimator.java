// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;

public abstract class RecyclerView$ItemAnimator
{
    private long mAddDuration;
    private long mChangeDuration;
    private ArrayList<RecyclerView$ItemAnimator$ItemAnimatorFinishedListener> mFinishedListeners;
    private RecyclerView$ItemAnimator$ItemAnimatorListener mListener;
    private long mMoveDuration;
    private long mRemoveDuration;
    private boolean mSupportsChangeAnimations;
    
    public RecyclerView$ItemAnimator() {
        this.mListener = null;
        this.mFinishedListeners = new ArrayList<RecyclerView$ItemAnimator$ItemAnimatorFinishedListener>();
        this.mAddDuration = 120L;
        this.mRemoveDuration = 120L;
        this.mMoveDuration = 250L;
        this.mChangeDuration = 250L;
        this.mSupportsChangeAnimations = false;
    }
    
    public abstract boolean animateAdd(final RecyclerView$ViewHolder p0);
    
    public abstract boolean animateChange(final RecyclerView$ViewHolder p0, final RecyclerView$ViewHolder p1, final int p2, final int p3, final int p4, final int p5);
    
    public abstract boolean animateMove(final RecyclerView$ViewHolder p0, final int p1, final int p2, final int p3, final int p4);
    
    public abstract boolean animateRemove(final RecyclerView$ViewHolder p0);
    
    public final void dispatchAddFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onAddFinished(recyclerView$ViewHolder);
        if (this.mListener != null) {
            this.mListener.onAddFinished(recyclerView$ViewHolder);
        }
    }
    
    public final void dispatchAddStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onAddStarting(recyclerView$ViewHolder);
    }
    
    public final void dispatchAnimationsFinished() {
        for (int size = this.mFinishedListeners.size(), i = 0; i < size; ++i) {
            this.mFinishedListeners.get(i).onAnimationsFinished();
        }
        this.mFinishedListeners.clear();
    }
    
    public final void dispatchChangeFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
        this.onChangeFinished(recyclerView$ViewHolder, b);
        if (this.mListener != null) {
            this.mListener.onChangeFinished(recyclerView$ViewHolder);
        }
    }
    
    public final void dispatchChangeStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
        this.onChangeStarting(recyclerView$ViewHolder, b);
    }
    
    public final void dispatchMoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onMoveFinished(recyclerView$ViewHolder);
        if (this.mListener != null) {
            this.mListener.onMoveFinished(recyclerView$ViewHolder);
        }
    }
    
    public final void dispatchMoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onMoveStarting(recyclerView$ViewHolder);
    }
    
    public final void dispatchRemoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onRemoveFinished(recyclerView$ViewHolder);
        if (this.mListener != null) {
            this.mListener.onRemoveFinished(recyclerView$ViewHolder);
        }
    }
    
    public final void dispatchRemoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onRemoveStarting(recyclerView$ViewHolder);
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
    
    public boolean getSupportsChangeAnimations() {
        return this.mSupportsChangeAnimations;
    }
    
    public abstract boolean isRunning();
    
    public void onAddFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onAddStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onChangeFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
    }
    
    public void onChangeStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
    }
    
    public void onMoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onMoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onRemoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onRemoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public abstract void runPendingAnimations();
    
    void setListener(final RecyclerView$ItemAnimator$ItemAnimatorListener mListener) {
        this.mListener = mListener;
    }
}
