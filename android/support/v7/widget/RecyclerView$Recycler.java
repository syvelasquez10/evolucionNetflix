// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.ViewGroup;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public final class RecyclerView$Recycler
{
    final ArrayList<RecyclerView$ViewHolder> mAttachedScrap;
    final ArrayList<RecyclerView$ViewHolder> mCachedViews;
    ArrayList<RecyclerView$ViewHolder> mChangedScrap;
    private RecyclerView$RecycledViewPool mRecyclerPool;
    private final List<RecyclerView$ViewHolder> mUnmodifiableAttachedScrap;
    private RecyclerView$ViewCacheExtension mViewCacheExtension;
    private int mViewCacheMax;
    final /* synthetic */ RecyclerView this$0;
    
    public RecyclerView$Recycler(final RecyclerView this$0) {
        this.this$0 = this$0;
        this.mAttachedScrap = new ArrayList<RecyclerView$ViewHolder>();
        this.mChangedScrap = null;
        this.mCachedViews = new ArrayList<RecyclerView$ViewHolder>();
        this.mUnmodifiableAttachedScrap = Collections.unmodifiableList((List<? extends RecyclerView$ViewHolder>)this.mAttachedScrap);
        this.mViewCacheMax = 2;
    }
    
    private void attachAccessibilityDelegate(final View view) {
        if (this.this$0.isAccessibilityEnabled()) {
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
            }
            if (!ViewCompat.hasAccessibilityDelegate(view)) {
                ViewCompat.setAccessibilityDelegate(view, this.this$0.mAccessibilityDelegate.getItemDelegate());
            }
        }
    }
    
    private void invalidateDisplayListInt(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (recyclerView$ViewHolder.itemView instanceof ViewGroup) {
            this.invalidateDisplayListInt((ViewGroup)recyclerView$ViewHolder.itemView, false);
        }
    }
    
    private void invalidateDisplayListInt(final ViewGroup viewGroup, final boolean b) {
        for (int i = viewGroup.getChildCount() - 1; i >= 0; --i) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                this.invalidateDisplayListInt((ViewGroup)child, true);
            }
        }
        if (!b) {
            return;
        }
        if (viewGroup.getVisibility() == 4) {
            viewGroup.setVisibility(0);
            viewGroup.setVisibility(4);
            return;
        }
        final int visibility = viewGroup.getVisibility();
        viewGroup.setVisibility(4);
        viewGroup.setVisibility(visibility);
    }
    
    void addViewHolderToRecycledViewPool(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        ViewCompat.setAccessibilityDelegate(recyclerView$ViewHolder.itemView, null);
        this.dispatchViewRecycled(recyclerView$ViewHolder);
        recyclerView$ViewHolder.mOwnerRecyclerView = null;
        this.getRecycledViewPool().putRecycledView(recyclerView$ViewHolder);
    }
    
    public void clear() {
        this.mAttachedScrap.clear();
        this.recycleAndClearCachedViews();
    }
    
    void clearOldPositions() {
        final int n = 0;
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            this.mCachedViews.get(i).clearOldPosition();
        }
        for (int size2 = this.mAttachedScrap.size(), j = 0; j < size2; ++j) {
            this.mAttachedScrap.get(j).clearOldPosition();
        }
        if (this.mChangedScrap != null) {
            for (int size3 = this.mChangedScrap.size(), k = n; k < size3; ++k) {
                this.mChangedScrap.get(k).clearOldPosition();
            }
        }
    }
    
    void clearScrap() {
        this.mAttachedScrap.clear();
        if (this.mChangedScrap != null) {
            this.mChangedScrap.clear();
        }
    }
    
    public int convertPreLayoutPositionToPostLayout(final int n) {
        if (n < 0 || n >= this.this$0.mState.getItemCount()) {
            throw new IndexOutOfBoundsException("invalid position " + n + ". State " + "item count is " + this.this$0.mState.getItemCount());
        }
        if (!this.this$0.mState.isPreLayout()) {
            return n;
        }
        return this.this$0.mAdapterHelper.findPositionOffset(n);
    }
    
    void dispatchViewRecycled(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (this.this$0.mRecyclerListener != null) {
            this.this$0.mRecyclerListener.onViewRecycled(recyclerView$ViewHolder);
        }
        if (this.this$0.mAdapter != null) {
            this.this$0.mAdapter.onViewRecycled(recyclerView$ViewHolder);
        }
        if (this.this$0.mState != null) {
            this.this$0.mViewInfoStore.removeViewHolder(recyclerView$ViewHolder);
        }
    }
    
    RecyclerView$ViewHolder getChangedScrapViewForPosition(int i) {
        final int n = 0;
        if (this.mChangedScrap != null) {
            final int size = this.mChangedScrap.size();
            if (size != 0) {
                for (int j = 0; j < size; ++j) {
                    final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mChangedScrap.get(j);
                    if (!recyclerView$ViewHolder.wasReturnedFromScrap() && recyclerView$ViewHolder.getLayoutPosition() == i) {
                        recyclerView$ViewHolder.addFlags(32);
                        return recyclerView$ViewHolder;
                    }
                }
                if (this.this$0.mAdapter.hasStableIds()) {
                    i = this.this$0.mAdapterHelper.findPositionOffset(i);
                    if (i > 0 && i < this.this$0.mAdapter.getItemCount()) {
                        final long itemId = this.this$0.mAdapter.getItemId(i);
                        RecyclerView$ViewHolder recyclerView$ViewHolder2;
                        for (i = n; i < size; ++i) {
                            recyclerView$ViewHolder2 = this.mChangedScrap.get(i);
                            if (!recyclerView$ViewHolder2.wasReturnedFromScrap() && recyclerView$ViewHolder2.getItemId() == itemId) {
                                recyclerView$ViewHolder2.addFlags(32);
                                return recyclerView$ViewHolder2;
                            }
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }
    
    RecyclerView$RecycledViewPool getRecycledViewPool() {
        if (this.mRecyclerPool == null) {
            this.mRecyclerPool = new RecyclerView$RecycledViewPool();
        }
        return this.mRecyclerPool;
    }
    
    int getScrapCount() {
        return this.mAttachedScrap.size();
    }
    
    public List<RecyclerView$ViewHolder> getScrapList() {
        return this.mUnmodifiableAttachedScrap;
    }
    
    View getScrapViewAt(final int n) {
        return this.mAttachedScrap.get(n).itemView;
    }
    
    RecyclerView$ViewHolder getScrapViewForId(final long n, final int n2, final boolean b) {
        for (int i = this.mAttachedScrap.size() - 1; i >= 0; --i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mAttachedScrap.get(i);
            if (recyclerView$ViewHolder.getItemId() == n && !recyclerView$ViewHolder.wasReturnedFromScrap()) {
                if (n2 == recyclerView$ViewHolder.getItemViewType()) {
                    recyclerView$ViewHolder.addFlags(32);
                    RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                    if (recyclerView$ViewHolder.isRemoved()) {
                        recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                        if (!this.this$0.mState.isPreLayout()) {
                            recyclerView$ViewHolder.setFlags(2, 14);
                            recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                        }
                    }
                    return recyclerView$ViewHolder2;
                }
                if (!b) {
                    this.mAttachedScrap.remove(i);
                    this.this$0.removeDetachedView(recyclerView$ViewHolder.itemView, false);
                    this.quickRecycleScrapView(recyclerView$ViewHolder.itemView);
                }
            }
        }
        for (int j = this.mCachedViews.size() - 1; j >= 0; --j) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder3 = this.mCachedViews.get(j);
            if (recyclerView$ViewHolder3.getItemId() == n) {
                if (n2 == recyclerView$ViewHolder3.getItemViewType()) {
                    final RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder3;
                    if (!b) {
                        this.mCachedViews.remove(j);
                        return recyclerView$ViewHolder3;
                    }
                    return recyclerView$ViewHolder2;
                }
                else if (!b) {
                    this.recycleCachedViewAt(j);
                }
            }
        }
        return null;
    }
    
    RecyclerView$ViewHolder getScrapViewForPosition(int indexOfChild, int i, final boolean b) {
        final int n = 0;
        final int size = this.mAttachedScrap.size();
        int j = 0;
        while (j < size) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mAttachedScrap.get(j);
            if (!recyclerView$ViewHolder.wasReturnedFromScrap() && recyclerView$ViewHolder.getLayoutPosition() == indexOfChild && !recyclerView$ViewHolder.isInvalid() && (this.this$0.mState.mInPreLayout || !recyclerView$ViewHolder.isRemoved())) {
                if (i != -1 && recyclerView$ViewHolder.getItemViewType() != i) {
                    Log.e("RecyclerView", "Scrap view for position " + indexOfChild + " isn't dirty but has" + " wrong view type! (found " + recyclerView$ViewHolder.getItemViewType() + " but expected " + i + ")");
                    break;
                }
                recyclerView$ViewHolder.addFlags(32);
                return recyclerView$ViewHolder;
            }
            else {
                ++j;
            }
        }
        if (!b) {
            final View hiddenNonRemovedView = this.this$0.mChildHelper.findHiddenNonRemovedView(indexOfChild, i);
            if (hiddenNonRemovedView != null) {
                final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(hiddenNonRemovedView);
                this.this$0.mChildHelper.unhide(hiddenNonRemovedView);
                indexOfChild = this.this$0.mChildHelper.indexOfChild(hiddenNonRemovedView);
                if (indexOfChild == -1) {
                    throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + childViewHolderInt);
                }
                this.this$0.mChildHelper.detachViewFromParent(indexOfChild);
                this.scrapView(hiddenNonRemovedView);
                childViewHolderInt.addFlags(8224);
                return childViewHolderInt;
            }
        }
        final int size2 = this.mCachedViews.size();
        i = n;
        while (i < size2) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder2 = this.mCachedViews.get(i);
            if (!recyclerView$ViewHolder2.isInvalid() && recyclerView$ViewHolder2.getLayoutPosition() == indexOfChild) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder = recyclerView$ViewHolder2;
                if (!b) {
                    this.mCachedViews.remove(i);
                    return recyclerView$ViewHolder2;
                }
                return recyclerView$ViewHolder;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public View getViewForPosition(final int n) {
        return this.getViewForPosition(n, false);
    }
    
    View getViewForPosition(int n, final boolean b) {
        final boolean b2 = true;
        if (n < 0 || n >= this.this$0.mState.getItemCount()) {
            throw new IndexOutOfBoundsException("Invalid item position " + n + "(" + n + "). Item count:" + this.this$0.mState.getItemCount());
        }
        RecyclerView$ViewHolder changedScrapViewForPosition;
        boolean b3;
        if (this.this$0.mState.isPreLayout()) {
            changedScrapViewForPosition = this.getChangedScrapViewForPosition(n);
            if (changedScrapViewForPosition != null) {
                b3 = true;
            }
            else {
                b3 = false;
            }
        }
        else {
            changedScrapViewForPosition = null;
            b3 = false;
        }
        RecyclerView$ViewHolder recyclerView$ViewHolder = changedScrapViewForPosition;
        if (changedScrapViewForPosition == null) {
            final RecyclerView$ViewHolder scrapViewForPosition = this.getScrapViewForPosition(n, -1, b);
            if ((recyclerView$ViewHolder = scrapViewForPosition) != null) {
                if (!this.validateViewHolderForOffsetPosition(scrapViewForPosition)) {
                    if (!b) {
                        scrapViewForPosition.addFlags(4);
                        if (scrapViewForPosition.isScrap()) {
                            this.this$0.removeDetachedView(scrapViewForPosition.itemView, false);
                            scrapViewForPosition.unScrap();
                        }
                        else if (scrapViewForPosition.wasReturnedFromScrap()) {
                            scrapViewForPosition.clearReturnedFromScrapFlag();
                        }
                        this.recycleViewHolderInternal(scrapViewForPosition);
                    }
                    recyclerView$ViewHolder = null;
                }
                else {
                    b3 = true;
                    recyclerView$ViewHolder = scrapViewForPosition;
                }
            }
        }
    Label_0586_Outer:
        while (true) {
            RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder;
            boolean b4 = b3;
            while (true) {
                Label_0934: {
                    if (recyclerView$ViewHolder != null) {
                        break Label_0934;
                    }
                    final int positionOffset = this.this$0.mAdapterHelper.findPositionOffset(n);
                    if (positionOffset < 0 || positionOffset >= this.this$0.mAdapter.getItemCount()) {
                        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + n + "(offset:" + positionOffset + ")." + "state:" + this.this$0.mState.getItemCount());
                    }
                    final int itemViewType = this.this$0.mAdapter.getItemViewType(positionOffset);
                    RecyclerView$ViewHolder scrapViewForId = recyclerView$ViewHolder;
                    boolean b5 = b3;
                    if (this.this$0.mAdapter.hasStableIds()) {
                        final RecyclerView$ViewHolder recyclerView$ViewHolder3 = scrapViewForId = this.getScrapViewForId(this.this$0.mAdapter.getItemId(positionOffset), itemViewType, b);
                        b5 = b3;
                        if (recyclerView$ViewHolder3 != null) {
                            recyclerView$ViewHolder3.mPosition = positionOffset;
                            b5 = true;
                            scrapViewForId = recyclerView$ViewHolder3;
                        }
                    }
                    RecyclerView$ViewHolder recyclerView$ViewHolder4;
                    if ((recyclerView$ViewHolder4 = scrapViewForId) == null) {
                        recyclerView$ViewHolder4 = scrapViewForId;
                        if (this.mViewCacheExtension != null) {
                            final View viewForPositionAndType = this.mViewCacheExtension.getViewForPositionAndType(this, n, itemViewType);
                            recyclerView$ViewHolder4 = scrapViewForId;
                            if (viewForPositionAndType != null) {
                                final RecyclerView$ViewHolder childViewHolder = this.this$0.getChildViewHolder(viewForPositionAndType);
                                if (childViewHolder == null) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                }
                                recyclerView$ViewHolder4 = childViewHolder;
                                if (childViewHolder.shouldIgnore()) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                }
                            }
                        }
                    }
                    RecyclerView$ViewHolder recyclerView$ViewHolder5;
                    if ((recyclerView$ViewHolder5 = recyclerView$ViewHolder4) == null) {
                        final RecyclerView$ViewHolder recycledView = this.getRecycledViewPool().getRecycledView(itemViewType);
                        if ((recyclerView$ViewHolder5 = recycledView) != null) {
                            recycledView.resetInternal();
                            recyclerView$ViewHolder5 = recycledView;
                            if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                                this.invalidateDisplayListInt(recycledView);
                                recyclerView$ViewHolder5 = recycledView;
                            }
                        }
                    }
                    recyclerView$ViewHolder2 = recyclerView$ViewHolder5;
                    b4 = b5;
                    if (recyclerView$ViewHolder5 != null) {
                        break Label_0934;
                    }
                    final RecyclerView$ViewHolder viewHolder = this.this$0.mAdapter.createViewHolder(this.this$0, itemViewType);
                    final boolean b6 = b5;
                    if (b6 && !this.this$0.mState.isPreLayout() && viewHolder.hasAnyOfTheFlags(8192)) {
                        viewHolder.setFlags(0, 8192);
                        if (this.this$0.mState.mRunSimpleAnimations) {
                            this.this$0.recordAnimationInfoIfBouncedHiddenView(viewHolder, this.this$0.mItemAnimator.recordPreLayoutInformation(this.this$0.mState, viewHolder, RecyclerView$ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder) | 0x1000, viewHolder.getUnmodifiedPayloads()));
                        }
                    }
                    if (this.this$0.mState.isPreLayout() && viewHolder.isBound()) {
                        viewHolder.mPreLayoutPosition = n;
                        n = 0;
                    }
                    else if (!viewHolder.isBound() || viewHolder.needsUpdate() || viewHolder.isInvalid()) {
                        final int positionOffset2 = this.this$0.mAdapterHelper.findPositionOffset(n);
                        viewHolder.mOwnerRecyclerView = this.this$0;
                        this.this$0.mAdapter.bindViewHolder(viewHolder, positionOffset2);
                        this.attachAccessibilityDelegate(viewHolder.itemView);
                        if (this.this$0.mState.isPreLayout()) {
                            viewHolder.mPreLayoutPosition = n;
                        }
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    final ViewGroup$LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
                    RecyclerView$LayoutParams recyclerView$LayoutParams;
                    if (layoutParams == null) {
                        recyclerView$LayoutParams = (RecyclerView$LayoutParams)this.this$0.generateDefaultLayoutParams();
                        viewHolder.itemView.setLayoutParams((ViewGroup$LayoutParams)recyclerView$LayoutParams);
                    }
                    else if (!this.this$0.checkLayoutParams(layoutParams)) {
                        recyclerView$LayoutParams = (RecyclerView$LayoutParams)this.this$0.generateLayoutParams(layoutParams);
                        viewHolder.itemView.setLayoutParams((ViewGroup$LayoutParams)recyclerView$LayoutParams);
                    }
                    else {
                        recyclerView$LayoutParams = (RecyclerView$LayoutParams)layoutParams;
                    }
                    recyclerView$LayoutParams.mViewHolder = viewHolder;
                    recyclerView$LayoutParams.mPendingInvalidate = (b6 && n != 0 && b2);
                    return viewHolder.itemView;
                }
                final RecyclerView$ViewHolder viewHolder = recyclerView$ViewHolder2;
                final boolean b6 = b4;
                continue;
            }
            continue Label_0586_Outer;
        }
    }
    
    void markItemDecorInsetsDirty() {
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)this.mCachedViews.get(i).itemView.getLayoutParams();
            if (recyclerView$LayoutParams != null) {
                recyclerView$LayoutParams.mInsetsDirty = true;
            }
        }
    }
    
    void markKnownViewsInvalid() {
        if (this.this$0.mAdapter != null && this.this$0.mAdapter.hasStableIds()) {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
                if (recyclerView$ViewHolder != null) {
                    recyclerView$ViewHolder.addFlags(6);
                    recyclerView$ViewHolder.addChangePayload(null);
                }
            }
        }
        else {
            this.recycleAndClearCachedViews();
        }
    }
    
    void offsetPositionRecordsForInsert(final int n, final int n2) {
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null && recyclerView$ViewHolder.mPosition >= n) {
                recyclerView$ViewHolder.offsetPosition(n2, true);
            }
        }
    }
    
    void offsetPositionRecordsForMove(final int n, final int n2) {
        int n3;
        int n4;
        int n5;
        if (n < n2) {
            n3 = -1;
            n4 = n2;
            n5 = n;
        }
        else {
            n3 = 1;
            n4 = n;
            n5 = n2;
        }
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null && recyclerView$ViewHolder.mPosition >= n5 && recyclerView$ViewHolder.mPosition <= n4) {
                if (recyclerView$ViewHolder.mPosition == n) {
                    recyclerView$ViewHolder.offsetPosition(n2 - n, false);
                }
                else {
                    recyclerView$ViewHolder.offsetPosition(n3, false);
                }
            }
        }
    }
    
    void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
        for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null) {
                if (recyclerView$ViewHolder.mPosition >= n + n2) {
                    recyclerView$ViewHolder.offsetPosition(-n2, b);
                }
                else if (recyclerView$ViewHolder.mPosition >= n) {
                    recyclerView$ViewHolder.addFlags(8);
                    this.recycleCachedViewAt(i);
                }
            }
        }
    }
    
    void onAdapterChanged(final RecyclerView$Adapter recyclerView$Adapter, final RecyclerView$Adapter recyclerView$Adapter2, final boolean b) {
        this.clear();
        this.getRecycledViewPool().onAdapterChanged(recyclerView$Adapter, recyclerView$Adapter2, b);
    }
    
    void quickRecycleScrapView(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.mScrapContainer = null;
        childViewHolderInt.mInChangeScrap = false;
        childViewHolderInt.clearReturnedFromScrapFlag();
        this.recycleViewHolderInternal(childViewHolderInt);
    }
    
    void recycleAndClearCachedViews() {
        for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
            this.recycleCachedViewAt(i);
        }
        this.mCachedViews.clear();
    }
    
    void recycleCachedViewAt(final int n) {
        this.addViewHolderToRecycledViewPool(this.mCachedViews.get(n));
        this.mCachedViews.remove(n);
    }
    
    public void recycleView(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isTmpDetached()) {
            this.this$0.removeDetachedView(view, false);
        }
        if (childViewHolderInt.isScrap()) {
            childViewHolderInt.unScrap();
        }
        else if (childViewHolderInt.wasReturnedFromScrap()) {
            childViewHolderInt.clearReturnedFromScrapFlag();
        }
        this.recycleViewHolderInternal(childViewHolderInt);
    }
    
    void recycleViewHolderInternal(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        boolean b = true;
        boolean b2 = false;
        if (recyclerView$ViewHolder.isScrap() || recyclerView$ViewHolder.itemView.getParent() != null) {
            final StringBuilder append = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(recyclerView$ViewHolder.isScrap()).append(" isAttached:");
            if (recyclerView$ViewHolder.itemView.getParent() == null) {
                b = false;
            }
            throw new IllegalArgumentException(append.append(b).toString());
        }
        if (recyclerView$ViewHolder.isTmpDetached()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + recyclerView$ViewHolder);
        }
        if (recyclerView$ViewHolder.shouldIgnore()) {
            throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
        }
        final boolean access$700 = recyclerView$ViewHolder.doesTransientStatePreventRecycling();
        boolean b3;
        if (this.this$0.mAdapter != null && access$700 && this.this$0.mAdapter.onFailedToRecycleView(recyclerView$ViewHolder)) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        int n2 = 0;
        Label_0263: {
            if (b3 || recyclerView$ViewHolder.isRecyclable()) {
                while (true) {
                    Label_0301: {
                        if (recyclerView$ViewHolder.hasAnyOfTheFlags(14)) {
                            break Label_0301;
                        }
                        int size;
                        final int n = size = this.mCachedViews.size();
                        if (n >= this.mViewCacheMax && (size = n) > 0) {
                            this.recycleCachedViewAt(0);
                            size = n - 1;
                        }
                        if (size >= this.mViewCacheMax) {
                            break Label_0301;
                        }
                        this.mCachedViews.add(recyclerView$ViewHolder);
                        n2 = 1;
                        if (n2 == 0) {
                            this.addViewHolderToRecycledViewPool(recyclerView$ViewHolder);
                            b2 = true;
                        }
                        break Label_0263;
                    }
                    n2 = 0;
                    continue;
                }
            }
            n2 = 0;
        }
        this.this$0.mViewInfoStore.removeViewHolder(recyclerView$ViewHolder);
        if (n2 == 0 && !b2 && access$700) {
            recyclerView$ViewHolder.mOwnerRecyclerView = null;
        }
    }
    
    void scrapView(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !this.this$0.canReuseUpdatedViewHolder(childViewHolderInt)) {
            if (this.mChangedScrap == null) {
                this.mChangedScrap = new ArrayList<RecyclerView$ViewHolder>();
            }
            childViewHolderInt.setScrapContainer(this, true);
            this.mChangedScrap.add(childViewHolderInt);
            return;
        }
        if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.this$0.mAdapter.hasStableIds()) {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
        }
        childViewHolderInt.setScrapContainer(this, false);
        this.mAttachedScrap.add(childViewHolderInt);
    }
    
    void setAdapterPositionsAsUnknown() {
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null) {
                recyclerView$ViewHolder.addFlags(512);
            }
        }
    }
    
    void setRecycledViewPool(final RecyclerView$RecycledViewPool mRecyclerPool) {
        if (this.mRecyclerPool != null) {
            this.mRecyclerPool.detach();
        }
        if ((this.mRecyclerPool = mRecyclerPool) != null) {
            this.mRecyclerPool.attach(this.this$0.getAdapter());
        }
    }
    
    void setViewCacheExtension(final RecyclerView$ViewCacheExtension mViewCacheExtension) {
        this.mViewCacheExtension = mViewCacheExtension;
    }
    
    public void setViewCacheSize(final int mViewCacheMax) {
        this.mViewCacheMax = mViewCacheMax;
        for (int n = this.mCachedViews.size() - 1; n >= 0 && this.mCachedViews.size() > mViewCacheMax; --n) {
            this.recycleCachedViewAt(n);
        }
    }
    
    void unscrapView(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (recyclerView$ViewHolder.mInChangeScrap) {
            this.mChangedScrap.remove(recyclerView$ViewHolder);
        }
        else {
            this.mAttachedScrap.remove(recyclerView$ViewHolder);
        }
        recyclerView$ViewHolder.mScrapContainer = null;
        recyclerView$ViewHolder.mInChangeScrap = false;
        recyclerView$ViewHolder.clearReturnedFromScrapFlag();
    }
    
    boolean validateViewHolderForOffsetPosition(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final boolean b = true;
        boolean preLayout;
        if (recyclerView$ViewHolder.isRemoved()) {
            preLayout = this.this$0.mState.isPreLayout();
        }
        else {
            if (recyclerView$ViewHolder.mPosition < 0 || recyclerView$ViewHolder.mPosition >= this.this$0.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + recyclerView$ViewHolder);
            }
            if (!this.this$0.mState.isPreLayout() && this.this$0.mAdapter.getItemViewType(recyclerView$ViewHolder.mPosition) != recyclerView$ViewHolder.getItemViewType()) {
                return false;
            }
            preLayout = b;
            if (this.this$0.mAdapter.hasStableIds()) {
                preLayout = b;
                if (recyclerView$ViewHolder.getItemId() != this.this$0.mAdapter.getItemId(recyclerView$ViewHolder.mPosition)) {
                    return false;
                }
            }
        }
        return preLayout;
    }
    
    void viewRangeUpdate(final int n, final int n2) {
        for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null) {
                final int layoutPosition = recyclerView$ViewHolder.getLayoutPosition();
                if (layoutPosition >= n && layoutPosition < n + n2) {
                    recyclerView$ViewHolder.addFlags(2);
                    this.recycleCachedViewAt(i);
                }
            }
        }
    }
}
