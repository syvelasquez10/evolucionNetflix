// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.concurrent.TimeUnit;
import android.support.v4.os.TraceCompat;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

final class GapWorker implements Runnable
{
    static final ThreadLocal<GapWorker> sGapWorker;
    static Comparator<GapWorker$Task> sTaskComparator;
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews;
    private ArrayList<GapWorker$Task> mTasks;
    
    static {
        sGapWorker = new ThreadLocal<GapWorker>();
        GapWorker.sTaskComparator = new GapWorker$1();
    }
    
    GapWorker() {
        this.mRecyclerViews = new ArrayList<RecyclerView>();
        this.mTasks = new ArrayList<GapWorker$Task>();
    }
    
    private void buildTaskList() {
        final int size = this.mRecyclerViews.size();
        int i = 0;
        int n = 0;
        while (i < size) {
            final RecyclerView recyclerView = this.mRecyclerViews.get(i);
            recyclerView.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, false);
            n += recyclerView.mPrefetchRegistry.mCount;
            ++i;
        }
        this.mTasks.ensureCapacity(n);
        int j = 0;
        int n2 = 0;
        while (j < size) {
            final RecyclerView view = this.mRecyclerViews.get(j);
            final GapWorker$LayoutPrefetchRegistryImpl mPrefetchRegistry = view.mPrefetchRegistry;
            final int viewVelocity = Math.abs(mPrefetchRegistry.mPrefetchDx) + Math.abs(mPrefetchRegistry.mPrefetchDy);
            for (int k = 0; k < mPrefetchRegistry.mCount * 2; k += 2) {
                GapWorker$Task gapWorker$Task;
                if (n2 >= this.mTasks.size()) {
                    gapWorker$Task = new GapWorker$Task();
                    this.mTasks.add(gapWorker$Task);
                }
                else {
                    gapWorker$Task = this.mTasks.get(n2);
                }
                final int distanceToItem = mPrefetchRegistry.mPrefetchArray[k + 1];
                gapWorker$Task.immediate = (distanceToItem <= viewVelocity);
                gapWorker$Task.viewVelocity = viewVelocity;
                gapWorker$Task.distanceToItem = distanceToItem;
                gapWorker$Task.view = view;
                gapWorker$Task.position = mPrefetchRegistry.mPrefetchArray[k];
                ++n2;
            }
            ++j;
        }
        Collections.sort(this.mTasks, GapWorker.sTaskComparator);
    }
    
    private void flushTaskWithDeadline(final GapWorker$Task gapWorker$Task, final long n) {
        long n2;
        if (gapWorker$Task.immediate) {
            n2 = Long.MAX_VALUE;
        }
        else {
            n2 = n;
        }
        final RecyclerView$ViewHolder prefetchPositionWithDeadline = this.prefetchPositionWithDeadline(gapWorker$Task.view, gapWorker$Task.position, n2);
        if (prefetchPositionWithDeadline != null && prefetchPositionWithDeadline.mNestedRecyclerView != null) {
            this.prefetchInnerRecyclerViewWithDeadline((RecyclerView)prefetchPositionWithDeadline.mNestedRecyclerView.get(), n);
        }
    }
    
    private void flushTasksWithDeadline(final long n) {
        for (int i = 0; i < this.mTasks.size(); ++i) {
            final GapWorker$Task gapWorker$Task = this.mTasks.get(i);
            if (gapWorker$Task.view == null) {
                break;
            }
            this.flushTaskWithDeadline(gapWorker$Task, n);
            gapWorker$Task.clear();
        }
    }
    
    static boolean isPrefetchPositionAttached(final RecyclerView recyclerView, final int n) {
        final boolean b = false;
        final int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        int n2 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n2 >= unfilteredChildCount) {
                break;
            }
            final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(n2));
            if (childViewHolderInt.mPosition == n && !childViewHolderInt.isInvalid()) {
                b2 = true;
                break;
            }
            ++n2;
        }
        return b2;
    }
    
    private void prefetchInnerRecyclerViewWithDeadline(final RecyclerView recyclerView, final long n) {
        if (recyclerView != null) {
            if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
                recyclerView.removeAndRecycleViews();
            }
            final GapWorker$LayoutPrefetchRegistryImpl mPrefetchRegistry = recyclerView.mPrefetchRegistry;
            mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView, true);
            if (mPrefetchRegistry.mCount != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    recyclerView.mState.prepareForNestedPrefetch(recyclerView.mAdapter);
                    for (int i = 0; i < mPrefetchRegistry.mCount * 2; i += 2) {
                        this.prefetchPositionWithDeadline(recyclerView, mPrefetchRegistry.mPrefetchArray[i], n);
                    }
                }
                finally {
                    TraceCompat.endSection();
                }
            }
        }
    }
    
    private RecyclerView$ViewHolder prefetchPositionWithDeadline(final RecyclerView recyclerView, final int n, final long n2) {
        RecyclerView$ViewHolder recyclerView$ViewHolder;
        if (isPrefetchPositionAttached(recyclerView, n)) {
            recyclerView$ViewHolder = null;
        }
        else {
            final RecyclerView$Recycler mRecycler = recyclerView.mRecycler;
            final RecyclerView$ViewHolder tryGetViewHolderForPositionByDeadline = mRecycler.tryGetViewHolderForPositionByDeadline(n, false, n2);
            if ((recyclerView$ViewHolder = tryGetViewHolderForPositionByDeadline) != null) {
                if (tryGetViewHolderForPositionByDeadline.isBound()) {
                    mRecycler.recycleView(tryGetViewHolderForPositionByDeadline.itemView);
                    return tryGetViewHolderForPositionByDeadline;
                }
                mRecycler.addViewHolderToRecycledViewPool(tryGetViewHolderForPositionByDeadline, false);
                return tryGetViewHolderForPositionByDeadline;
            }
        }
        return recyclerView$ViewHolder;
    }
    
    public void add(final RecyclerView recyclerView) {
        this.mRecyclerViews.add(recyclerView);
    }
    
    void postFromTraversal(final RecyclerView recyclerView, final int n, final int n2) {
        if (recyclerView.isAttachedToWindow() && this.mPostTimeNs == 0L) {
            this.mPostTimeNs = recyclerView.getNanoTime();
            recyclerView.post((Runnable)this);
        }
        recyclerView.mPrefetchRegistry.setPrefetchVector(n, n2);
    }
    
    void prefetch(final long n) {
        this.buildTaskList();
        this.flushTasksWithDeadline(n);
    }
    
    public void remove(final RecyclerView recyclerView) {
        this.mRecyclerViews.remove(recyclerView);
    }
    
    @Override
    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (this.mRecyclerViews.isEmpty()) {
                return;
            }
            final long nanos = TimeUnit.MILLISECONDS.toNanos(this.mRecyclerViews.get(0).getDrawingTime());
            if (nanos == 0L) {
                return;
            }
            this.prefetch(nanos + this.mFrameIntervalNs);
        }
        finally {
            this.mPostTimeNs = 0L;
            TraceCompat.endSection();
        }
    }
}
