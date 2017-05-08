// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.os.TraceCompat;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View$MeasureSpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;
import android.util.Log;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collections;
import android.view.View;
import java.util.List;

public abstract class RecyclerView$ViewHolder
{
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_BOUND = 1;
    static final int FLAG_CHANGED = 64;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS;
    public final View itemView;
    private int mFlags;
    private int mIsRecyclableCount;
    long mItemId;
    int mItemViewType;
    int mOldPosition;
    RecyclerView mOwnerRecyclerView;
    List<Object> mPayloads;
    int mPosition;
    int mPreLayoutPosition;
    private RecyclerView$Recycler mScrapContainer;
    RecyclerView$ViewHolder mShadowedHolder;
    RecyclerView$ViewHolder mShadowingHolder;
    List<Object> mUnmodifiedPayloads;
    private int mWasImportantForAccessibilityBeforeHidden;
    
    static {
        FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    }
    
    public RecyclerView$ViewHolder(final View itemView) {
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mItemViewType = -1;
        this.mPreLayoutPosition = -1;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        this.mPayloads = null;
        this.mUnmodifiedPayloads = null;
        this.mIsRecyclableCount = 0;
        this.mScrapContainer = null;
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = itemView;
    }
    
    private void createPayloadsIfNeeded() {
        if (this.mPayloads == null) {
            this.mPayloads = new ArrayList<Object>();
            this.mUnmodifiedPayloads = Collections.unmodifiableList((List<?>)this.mPayloads);
        }
    }
    
    private boolean doesTransientStatePreventRecycling() {
        return (this.mFlags & 0x10) == 0x0 && ViewCompat.hasTransientState(this.itemView);
    }
    
    private void onEnteredHiddenState() {
        this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
        ViewCompat.setImportantForAccessibility(this.itemView, 4);
    }
    
    private void onLeftHiddenState() {
        ViewCompat.setImportantForAccessibility(this.itemView, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    private boolean shouldBeKeptAsChild() {
        return (this.mFlags & 0x10) != 0x0;
    }
    
    void addChangePayload(final Object o) {
        if (o == null) {
            this.addFlags(1024);
        }
        else if ((this.mFlags & 0x400) == 0x0) {
            this.createPayloadsIfNeeded();
            this.mPayloads.add(o);
        }
    }
    
    void addFlags(final int n) {
        this.mFlags |= n;
    }
    
    void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }
    
    void clearPayload() {
        if (this.mPayloads != null) {
            this.mPayloads.clear();
        }
        this.mFlags &= 0xFFFFFBFF;
    }
    
    void clearReturnedFromScrapFlag() {
        this.mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag() {
        this.mFlags &= 0xFFFFFEFF;
    }
    
    void flagRemovedAndOffsetPosition(final int mPosition, final int n, final boolean b) {
        this.addFlags(8);
        this.offsetPosition(n, b);
        this.mPosition = mPosition;
    }
    
    public final int getAdapterPosition() {
        if (this.mOwnerRecyclerView == null) {
            return -1;
        }
        return this.mOwnerRecyclerView.getAdapterPositionFor(this);
    }
    
    public final long getItemId() {
        return this.mItemId;
    }
    
    public final int getItemViewType() {
        return this.mItemViewType;
    }
    
    public final int getLayoutPosition() {
        if (this.mPreLayoutPosition == -1) {
            return this.mPosition;
        }
        return this.mPreLayoutPosition;
    }
    
    public final int getOldPosition() {
        return this.mOldPosition;
    }
    
    @Deprecated
    public final int getPosition() {
        if (this.mPreLayoutPosition == -1) {
            return this.mPosition;
        }
        return this.mPreLayoutPosition;
    }
    
    List<Object> getUnmodifiedPayloads() {
        if ((this.mFlags & 0x400) != 0x0) {
            return RecyclerView$ViewHolder.FULLUPDATE_PAYLOADS;
        }
        if (this.mPayloads == null || this.mPayloads.size() == 0) {
            return RecyclerView$ViewHolder.FULLUPDATE_PAYLOADS;
        }
        return this.mUnmodifiedPayloads;
    }
    
    boolean hasAnyOfTheFlags(final int n) {
        return (this.mFlags & n) != 0x0;
    }
    
    boolean isAdapterPositionUnknown() {
        return (this.mFlags & 0x200) != 0x0 || this.isInvalid();
    }
    
    boolean isBound() {
        return (this.mFlags & 0x1) != 0x0;
    }
    
    boolean isChanged() {
        return (this.mFlags & 0x40) != 0x0;
    }
    
    boolean isInvalid() {
        return (this.mFlags & 0x4) != 0x0;
    }
    
    public final boolean isRecyclable() {
        return (this.mFlags & 0x10) == 0x0 && !ViewCompat.hasTransientState(this.itemView);
    }
    
    boolean isRemoved() {
        return (this.mFlags & 0x8) != 0x0;
    }
    
    boolean isScrap() {
        return this.mScrapContainer != null;
    }
    
    boolean isTmpDetached() {
        return (this.mFlags & 0x100) != 0x0;
    }
    
    boolean needsUpdate() {
        return (this.mFlags & 0x2) != 0x0;
    }
    
    void offsetPosition(final int n, final boolean b) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (b) {
            this.mPreLayoutPosition += n;
        }
        this.mPosition += n;
        if (this.itemView.getLayoutParams() != null) {
            ((RecyclerView$LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
        }
    }
    
    void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        this.clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }
    
    void setFlags(final int n, final int n2) {
        this.mFlags = ((this.mFlags & ~n2) | (n & n2));
    }
    
    public final void setIsRecyclable(final boolean b) {
        int mIsRecyclableCount;
        if (b) {
            mIsRecyclableCount = this.mIsRecyclableCount - 1;
        }
        else {
            mIsRecyclableCount = this.mIsRecyclableCount + 1;
        }
        this.mIsRecyclableCount = mIsRecyclableCount;
        if (this.mIsRecyclableCount < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        }
        else {
            if (!b && this.mIsRecyclableCount == 1) {
                this.mFlags |= 0x10;
                return;
            }
            if (b && this.mIsRecyclableCount == 0) {
                this.mFlags &= 0xFFFFFFEF;
            }
        }
    }
    
    void setScrapContainer(final RecyclerView$Recycler mScrapContainer) {
        this.mScrapContainer = mScrapContainer;
    }
    
    boolean shouldIgnore() {
        return (this.mFlags & 0x80) != 0x0;
    }
    
    void stopIgnoring() {
        this.mFlags &= 0xFFFFFF7F;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
        if (this.isScrap()) {
            sb.append(" scrap");
        }
        if (this.isInvalid()) {
            sb.append(" invalid");
        }
        if (!this.isBound()) {
            sb.append(" unbound");
        }
        if (this.needsUpdate()) {
            sb.append(" update");
        }
        if (this.isRemoved()) {
            sb.append(" removed");
        }
        if (this.shouldIgnore()) {
            sb.append(" ignored");
        }
        if (this.isChanged()) {
            sb.append(" changed");
        }
        if (this.isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!this.isRecyclable()) {
            sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (this.isAdapterPositionUnknown()) {
            sb.append("undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
    
    void unScrap() {
        this.mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap() {
        return (this.mFlags & 0x20) != 0x0;
    }
}
