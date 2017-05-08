// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.util.LongSparseArray;
import android.support.v4.util.ArrayMap;

class ViewInfoStore
{
    final ArrayMap<RecyclerView$ViewHolder, ViewInfoStore$InfoRecord> mLayoutHolderMap;
    final LongSparseArray<RecyclerView$ViewHolder> mOldChangedHolders;
    
    ViewInfoStore() {
        this.mLayoutHolderMap = new ArrayMap<RecyclerView$ViewHolder, ViewInfoStore$InfoRecord>();
        this.mOldChangedHolders = new LongSparseArray<RecyclerView$ViewHolder>();
    }
    
    private RecyclerView$ItemAnimator$ItemHolderInfo popFromLayoutStep(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo = null;
        final int indexOfKey = this.mLayoutHolderMap.indexOfKey(recyclerView$ViewHolder);
        RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2;
        if (indexOfKey < 0) {
            recyclerView$ItemAnimator$ItemHolderInfo2 = recyclerView$ItemAnimator$ItemHolderInfo;
        }
        else {
            final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.valueAt(indexOfKey);
            recyclerView$ItemAnimator$ItemHolderInfo2 = recyclerView$ItemAnimator$ItemHolderInfo;
            if (viewInfoStore$InfoRecord != null) {
                recyclerView$ItemAnimator$ItemHolderInfo2 = recyclerView$ItemAnimator$ItemHolderInfo;
                if ((viewInfoStore$InfoRecord.flags & n) != 0x0) {
                    viewInfoStore$InfoRecord.flags &= ~n;
                    RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo3;
                    if (n == 4) {
                        recyclerView$ItemAnimator$ItemHolderInfo3 = viewInfoStore$InfoRecord.preInfo;
                    }
                    else {
                        if (n != 8) {
                            throw new IllegalArgumentException("Must provide flag PRE or POST");
                        }
                        recyclerView$ItemAnimator$ItemHolderInfo3 = viewInfoStore$InfoRecord.postInfo;
                    }
                    recyclerView$ItemAnimator$ItemHolderInfo2 = recyclerView$ItemAnimator$ItemHolderInfo3;
                    if ((viewInfoStore$InfoRecord.flags & 0xC) == 0x0) {
                        this.mLayoutHolderMap.removeAt(indexOfKey);
                        ViewInfoStore$InfoRecord.recycle(viewInfoStore$InfoRecord);
                        return recyclerView$ItemAnimator$ItemHolderInfo3;
                    }
                }
            }
        }
        return recyclerView$ItemAnimator$ItemHolderInfo2;
    }
    
    void addToAppearedInPreLayoutHolders(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo preInfo) {
        ViewInfoStore$InfoRecord obtain;
        if ((obtain = this.mLayoutHolderMap.get(recyclerView$ViewHolder)) == null) {
            obtain = ViewInfoStore$InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder, obtain);
        }
        obtain.flags |= 0x2;
        obtain.preInfo = preInfo;
    }
    
    void addToDisappearedInLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        ViewInfoStore$InfoRecord obtain;
        if ((obtain = this.mLayoutHolderMap.get(recyclerView$ViewHolder)) == null) {
            obtain = ViewInfoStore$InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder, obtain);
        }
        obtain.flags |= 0x1;
    }
    
    void addToOldChangeHolders(final long n, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.mOldChangedHolders.put(n, recyclerView$ViewHolder);
    }
    
    void addToPostLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo postInfo) {
        ViewInfoStore$InfoRecord obtain;
        if ((obtain = this.mLayoutHolderMap.get(recyclerView$ViewHolder)) == null) {
            obtain = ViewInfoStore$InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder, obtain);
        }
        obtain.postInfo = postInfo;
        obtain.flags |= 0x8;
    }
    
    void addToPreLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo preInfo) {
        ViewInfoStore$InfoRecord obtain;
        if ((obtain = this.mLayoutHolderMap.get(recyclerView$ViewHolder)) == null) {
            obtain = ViewInfoStore$InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder, obtain);
        }
        obtain.preInfo = preInfo;
        obtain.flags |= 0x4;
    }
    
    void clear() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.clear();
    }
    
    RecyclerView$ViewHolder getFromOldChangeHolders(final long n) {
        return this.mOldChangedHolders.get(n);
    }
    
    boolean isDisappearing(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.get(recyclerView$ViewHolder);
        return viewInfoStore$InfoRecord != null && (viewInfoStore$InfoRecord.flags & 0x1) != 0x0;
    }
    
    boolean isInPreLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.get(recyclerView$ViewHolder);
        return viewInfoStore$InfoRecord != null && (viewInfoStore$InfoRecord.flags & 0x4) != 0x0;
    }
    
    void onDetach() {
        ViewInfoStore$InfoRecord.drainCache();
    }
    
    public void onViewDetached(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.removeFromDisappearedInLayout(recyclerView$ViewHolder);
    }
    
    RecyclerView$ItemAnimator$ItemHolderInfo popFromPostLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return this.popFromLayoutStep(recyclerView$ViewHolder, 8);
    }
    
    RecyclerView$ItemAnimator$ItemHolderInfo popFromPreLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return this.popFromLayoutStep(recyclerView$ViewHolder, 4);
    }
    
    void process(final ViewInfoStore$ProcessCallback viewInfoStore$ProcessCallback) {
        for (int i = this.mLayoutHolderMap.size() - 1; i >= 0; --i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mLayoutHolderMap.keyAt(i);
            final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.removeAt(i);
            if ((viewInfoStore$InfoRecord.flags & 0x3) == 0x3) {
                viewInfoStore$ProcessCallback.unused(recyclerView$ViewHolder);
            }
            else if ((viewInfoStore$InfoRecord.flags & 0x1) != 0x0) {
                if (viewInfoStore$InfoRecord.preInfo == null) {
                    viewInfoStore$ProcessCallback.unused(recyclerView$ViewHolder);
                }
                else {
                    viewInfoStore$ProcessCallback.processDisappeared(recyclerView$ViewHolder, viewInfoStore$InfoRecord.preInfo, viewInfoStore$InfoRecord.postInfo);
                }
            }
            else if ((viewInfoStore$InfoRecord.flags & 0xE) == 0xE) {
                viewInfoStore$ProcessCallback.processAppeared(recyclerView$ViewHolder, viewInfoStore$InfoRecord.preInfo, viewInfoStore$InfoRecord.postInfo);
            }
            else if ((viewInfoStore$InfoRecord.flags & 0xC) == 0xC) {
                viewInfoStore$ProcessCallback.processPersistent(recyclerView$ViewHolder, viewInfoStore$InfoRecord.preInfo, viewInfoStore$InfoRecord.postInfo);
            }
            else if ((viewInfoStore$InfoRecord.flags & 0x4) != 0x0) {
                viewInfoStore$ProcessCallback.processDisappeared(recyclerView$ViewHolder, viewInfoStore$InfoRecord.preInfo, null);
            }
            else if ((viewInfoStore$InfoRecord.flags & 0x8) != 0x0) {
                viewInfoStore$ProcessCallback.processAppeared(recyclerView$ViewHolder, viewInfoStore$InfoRecord.preInfo, viewInfoStore$InfoRecord.postInfo);
            }
            else if ((viewInfoStore$InfoRecord.flags & 0x2) != 0x0) {}
            ViewInfoStore$InfoRecord.recycle(viewInfoStore$InfoRecord);
        }
    }
    
    void removeFromDisappearedInLayout(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.get(recyclerView$ViewHolder);
        if (viewInfoStore$InfoRecord == null) {
            return;
        }
        viewInfoStore$InfoRecord.flags &= 0xFFFFFFFE;
    }
    
    void removeViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        for (int i = this.mOldChangedHolders.size() - 1; i >= 0; --i) {
            if (recyclerView$ViewHolder == this.mOldChangedHolders.valueAt(i)) {
                this.mOldChangedHolders.removeAt(i);
                break;
            }
        }
        final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = this.mLayoutHolderMap.remove(recyclerView$ViewHolder);
        if (viewInfoStore$InfoRecord != null) {
            ViewInfoStore$InfoRecord.recycle(viewInfoStore$InfoRecord);
        }
    }
}
