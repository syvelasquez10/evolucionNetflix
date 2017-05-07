// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View;
import java.util.List;

class ChildHelper
{
    final ChildHelper$Bucket mBucket;
    final ChildHelper$Callback mCallback;
    final List<View> mHiddenViews;
    
    ChildHelper(final ChildHelper$Callback mCallback) {
        this.mCallback = mCallback;
        this.mBucket = new ChildHelper$Bucket();
        this.mHiddenViews = new ArrayList<View>();
    }
    
    private int getOffset(int n) {
        if (n >= 0) {
            int n2;
            for (int childCount = this.mCallback.getChildCount(), i = n; i < childCount; i += n2) {
                n2 = n - (i - this.mBucket.countOnesBefore(i));
                if (n2 == 0) {
                    while (true) {
                        n = i;
                        if (!this.mBucket.get(i)) {
                            return n;
                        }
                        ++i;
                    }
                }
                else {}
            }
            return -1;
        }
        n = -1;
        return n;
    }
    
    void addView(final View view, int n, final boolean b) {
        if (n < 0) {
            n = this.mCallback.getChildCount();
        }
        else {
            n = this.getOffset(n);
        }
        this.mCallback.addView(view, n);
        this.mBucket.insert(n, b);
        if (b) {
            this.mHiddenViews.add(view);
        }
    }
    
    void addView(final View view, final boolean b) {
        this.addView(view, -1, b);
    }
    
    void attachViewToParent(final View view, int n, final ViewGroup$LayoutParams viewGroup$LayoutParams, final boolean b) {
        if (n < 0) {
            n = this.mCallback.getChildCount();
        }
        else {
            n = this.getOffset(n);
        }
        this.mCallback.attachViewToParent(view, n, viewGroup$LayoutParams);
        this.mBucket.insert(n, b);
        if (b) {
            this.mHiddenViews.add(view);
        }
    }
    
    void detachViewFromParent(int offset) {
        offset = this.getOffset(offset);
        this.mCallback.detachViewFromParent(offset);
        this.mBucket.remove(offset);
    }
    
    View findHiddenNonRemovedView(final int n, final int n2) {
        for (int size = this.mHiddenViews.size(), i = 0; i < size; ++i) {
            final View view = this.mHiddenViews.get(i);
            final RecyclerView$ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getPosition() == n && !childViewHolder.isInvalid() && (n2 == -1 || childViewHolder.getItemViewType() == n2)) {
                return view;
            }
        }
        return null;
    }
    
    View getChildAt(int offset) {
        offset = this.getOffset(offset);
        return this.mCallback.getChildAt(offset);
    }
    
    int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }
    
    View getUnfilteredChildAt(final int n) {
        return this.mCallback.getChildAt(n);
    }
    
    int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }
    
    void hide(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.mBucket.set(indexOfChild);
        this.mHiddenViews.add(view);
    }
    
    int indexOfChild(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild != -1 && !this.mBucket.get(indexOfChild)) {
            return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
        }
        return -1;
    }
    
    boolean isHidden(final View view) {
        return this.mHiddenViews.contains(view);
    }
    
    void removeAllViewsUnfiltered() {
        this.mCallback.removeAllViews();
        this.mBucket.reset();
        this.mHiddenViews.clear();
    }
    
    void removeView(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild >= 0) {
            this.mCallback.removeViewAt(indexOfChild);
            if (this.mBucket.remove(indexOfChild)) {
                this.mHiddenViews.remove(view);
            }
        }
    }
    
    void removeViewAt(int offset) {
        offset = this.getOffset(offset);
        final View child = this.mCallback.getChildAt(offset);
        if (child != null) {
            this.mCallback.removeViewAt(offset);
            if (this.mBucket.remove(offset)) {
                this.mHiddenViews.remove(child);
            }
        }
    }
    
    boolean removeViewIfHidden(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1) {
            if (this.mHiddenViews.remove(view)) {}
        }
        else {
            if (!this.mBucket.get(indexOfChild)) {
                return false;
            }
            this.mBucket.remove(indexOfChild);
            this.mCallback.removeViewAt(indexOfChild);
            if (!this.mHiddenViews.remove(view)) {
                return true;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }
}
