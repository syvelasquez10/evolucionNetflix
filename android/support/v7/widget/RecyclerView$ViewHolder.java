// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.Log;
import android.support.v4.view.ViewCompat;
import android.view.View;

public abstract class RecyclerView$ViewHolder
{
    static final int FLAG_BOUND = 1;
    static final int FLAG_CHANGED = 64;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    public final View itemView;
    private int mFlags;
    private int mIsRecyclableCount;
    long mItemId;
    int mItemViewType;
    int mOldPosition;
    int mPosition;
    int mPreLayoutPosition;
    private RecyclerView$Recycler mScrapContainer;
    RecyclerView$ViewHolder mShadowedHolder;
    RecyclerView$ViewHolder mShadowingHolder;
    
    public RecyclerView$ViewHolder(final View itemView) {
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mItemViewType = -1;
        this.mPreLayoutPosition = -1;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        this.mIsRecyclableCount = 0;
        this.mScrapContainer = null;
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = itemView;
    }
    
    void addFlags(final int n) {
        this.mFlags |= n;
    }
    
    void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
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
    
    public final long getItemId() {
        return this.mItemId;
    }
    
    public final int getItemViewType() {
        return this.mItemViewType;
    }
    
    public final int getOldPosition() {
        return this.mOldPosition;
    }
    
    public final int getPosition() {
        if (this.mPreLayoutPosition == -1) {
            return this.mPosition;
        }
        return this.mPreLayoutPosition;
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
