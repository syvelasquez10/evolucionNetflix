// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.SparseArray;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
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
    private ArrayList<RecyclerView$ViewHolder> mChangedScrap;
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
        if (this.this$0.mAccessibilityManager != null && this.this$0.mAccessibilityManager.isEnabled()) {
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
            this.this$0.mState.onViewRecycled(recyclerView$ViewHolder);
        }
    }
    
    RecyclerView$ViewHolder getChangedScrapViewForPosition(int i) {
        final int n = 0;
        if (this.mChangedScrap != null) {
            final int size = this.mChangedScrap.size();
            if (size != 0) {
                for (int j = 0; j < size; ++j) {
                    final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mChangedScrap.get(j);
                    if (!recyclerView$ViewHolder.wasReturnedFromScrap() && recyclerView$ViewHolder.getPosition() == i) {
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
                    this.tryToRecycleCachedViewAt(j);
                }
            }
        }
        return null;
    }
    
    RecyclerView$ViewHolder getScrapViewForPosition(final int n, int i, final boolean b) {
        final int n2 = 0;
        final int size = this.mAttachedScrap.size();
        int j = 0;
        while (j < size) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mAttachedScrap.get(j);
            if (!recyclerView$ViewHolder.wasReturnedFromScrap() && recyclerView$ViewHolder.getPosition() == n && !recyclerView$ViewHolder.isInvalid() && (this.this$0.mState.mInPreLayout || !recyclerView$ViewHolder.isRemoved())) {
                if (i != -1 && recyclerView$ViewHolder.getItemViewType() != i) {
                    Log.e("RecyclerView", "Scrap view for position " + n + " isn't dirty but has" + " wrong view type! (found " + recyclerView$ViewHolder.getItemViewType() + " but expected " + i + ")");
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
            final View hiddenNonRemovedView = this.this$0.mChildHelper.findHiddenNonRemovedView(n, i);
            if (hiddenNonRemovedView != null) {
                this.this$0.mItemAnimator.endAnimation(this.this$0.getChildViewHolder(hiddenNonRemovedView));
            }
        }
        int size2;
        RecyclerView$ViewHolder recyclerView$ViewHolder2;
        for (size2 = this.mCachedViews.size(), i = n2; i < size2; ++i) {
            recyclerView$ViewHolder2 = this.mCachedViews.get(i);
            if (!recyclerView$ViewHolder2.isInvalid() && recyclerView$ViewHolder2.getPosition() == n) {
                if (!b) {
                    this.mCachedViews.remove(i);
                }
                return recyclerView$ViewHolder2;
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
    Label_0606_Outer:
        while (true) {
            RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder;
            boolean b4 = b3;
            while (true) {
                Label_0845: {
                    if (recyclerView$ViewHolder != null) {
                        break Label_0845;
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
                        final RecyclerView$ViewHolder recycledView = this.getRecycledViewPool().getRecycledView(this.this$0.mAdapter.getItemViewType(positionOffset));
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
                        break Label_0845;
                    }
                    final RecyclerView$ViewHolder viewHolder = this.this$0.mAdapter.createViewHolder(this.this$0, this.this$0.mAdapter.getItemViewType(positionOffset));
                    final boolean b6 = b5;
                    if (this.this$0.mState.isPreLayout() && viewHolder.isBound()) {
                        viewHolder.mPreLayoutPosition = n;
                        n = 0;
                    }
                    else if (!viewHolder.isBound() || viewHolder.needsUpdate() || viewHolder.isInvalid()) {
                        this.this$0.mAdapter.bindViewHolder(viewHolder, this.this$0.mAdapterHelper.findPositionOffset(n));
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
                final boolean b6 = b4;
                final RecyclerView$ViewHolder viewHolder = recyclerView$ViewHolder2;
                continue;
            }
            continue Label_0606_Outer;
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
                }
            }
        }
        else {
            for (int j = this.mCachedViews.size() - 1; j >= 0; --j) {
                if (!this.tryToRecycleCachedViewAt(j)) {
                    this.mCachedViews.get(j).addFlags(6);
                }
            }
        }
    }
    
    void offsetPositionRecordsForInsert(final int n, final int n2) {
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null && recyclerView$ViewHolder.getPosition() >= n) {
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
                if (recyclerView$ViewHolder.getPosition() >= n + n2) {
                    recyclerView$ViewHolder.offsetPosition(-n2, b);
                }
                else if (recyclerView$ViewHolder.getPosition() >= n && !this.tryToRecycleCachedViewAt(i)) {
                    recyclerView$ViewHolder.addFlags(4);
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
        childViewHolderInt.clearReturnedFromScrapFlag();
        this.recycleViewHolderInternal(childViewHolderInt);
    }
    
    void recycleAndClearCachedViews() {
        for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
            this.tryToRecycleCachedViewAt(i);
        }
        this.mCachedViews.clear();
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
        final boolean b2 = false;
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
        if (recyclerView$ViewHolder.isRecyclable()) {
            boolean b3 = b2;
            Label_0256: {
                if (!recyclerView$ViewHolder.isInvalid()) {
                    if (!this.this$0.mState.mInPreLayout) {
                        b3 = b2;
                        if (recyclerView$ViewHolder.isRemoved()) {
                            break Label_0256;
                        }
                    }
                    b3 = b2;
                    if (!recyclerView$ViewHolder.isChanged()) {
                        if (this.mCachedViews.size() == this.mViewCacheMax && !this.mCachedViews.isEmpty()) {
                            for (int n = 0; n < this.mCachedViews.size() && !this.tryToRecycleCachedViewAt(n); ++n) {}
                        }
                        b3 = b2;
                        if (this.mCachedViews.size() < this.mViewCacheMax) {
                            this.mCachedViews.add(recyclerView$ViewHolder);
                            b3 = true;
                        }
                    }
                }
            }
            if (!b3) {
                this.getRecycledViewPool().putRecycledView(recyclerView$ViewHolder);
                this.dispatchViewRecycled(recyclerView$ViewHolder);
            }
        }
        this.this$0.mState.onViewRecycled(recyclerView$ViewHolder);
    }
    
    void scrapView(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.setScrapContainer(this);
        if (childViewHolderInt.isChanged() && this.this$0.supportsChangeAnimations()) {
            if (this.mChangedScrap == null) {
                this.mChangedScrap = new ArrayList<RecyclerView$ViewHolder>();
            }
            this.mChangedScrap.add(childViewHolderInt);
            return;
        }
        if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.this$0.mAdapter.hasStableIds()) {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
        }
        this.mAttachedScrap.add(childViewHolderInt);
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
            this.tryToRecycleCachedViewAt(n);
        }
        while (this.mCachedViews.size() > mViewCacheMax) {
            this.mCachedViews.remove(this.mCachedViews.size() - 1);
        }
    }
    
    boolean tryToRecycleCachedViewAt(final int n) {
        final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(n);
        if (recyclerView$ViewHolder.isRecyclable()) {
            this.getRecycledViewPool().putRecycledView(recyclerView$ViewHolder);
            this.dispatchViewRecycled(recyclerView$ViewHolder);
            this.mCachedViews.remove(n);
            return true;
        }
        return false;
    }
    
    void unscrapView(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (!recyclerView$ViewHolder.isChanged() || !this.this$0.supportsChangeAnimations() || this.mChangedScrap == null) {
            this.mAttachedScrap.remove(recyclerView$ViewHolder);
        }
        else {
            this.mChangedScrap.remove(recyclerView$ViewHolder);
        }
        recyclerView$ViewHolder.mScrapContainer = null;
        recyclerView$ViewHolder.clearReturnedFromScrapFlag();
    }
    
    boolean validateViewHolderForOffsetPosition(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (!recyclerView$ViewHolder.isRemoved()) {
            if (recyclerView$ViewHolder.mPosition < 0 || recyclerView$ViewHolder.mPosition >= this.this$0.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + recyclerView$ViewHolder);
            }
            if (!this.this$0.mState.isPreLayout() && this.this$0.mAdapter.getItemViewType(recyclerView$ViewHolder.mPosition) != recyclerView$ViewHolder.getItemViewType()) {
                return false;
            }
            if (this.this$0.mAdapter.hasStableIds() && recyclerView$ViewHolder.getItemId() != this.this$0.mAdapter.getItemId(recyclerView$ViewHolder.mPosition)) {
                return false;
            }
        }
        return true;
    }
    
    void viewRangeUpdate(final int n, final int n2) {
        for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mCachedViews.get(i);
            if (recyclerView$ViewHolder != null) {
                final int position = recyclerView$ViewHolder.getPosition();
                if (position >= n && position < n + n2) {
                    recyclerView$ViewHolder.addFlags(2);
                }
            }
        }
    }
}
