// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class StaggeredGridLayoutManager$LazySpanLookup
{
    int[] mData;
    List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> mFullSpanItems;
    
    private int invalidateFullSpansAfter(final int n) {
        if (this.mFullSpanItems == null) {
            return -1;
        }
        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItem = this.getFullSpanItem(n);
        if (fullSpanItem != null) {
            this.mFullSpanItems.remove(fullSpanItem);
        }
        final int size = this.mFullSpanItems.size();
        int i = 0;
        while (true) {
            while (i < size) {
                if (this.mFullSpanItems.get(i).mPosition >= n) {
                    if (i != -1) {
                        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.mFullSpanItems.get(i);
                        this.mFullSpanItems.remove(i);
                        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
                    }
                    return -1;
                }
                else {
                    ++i;
                }
            }
            i = -1;
            continue;
        }
    }
    
    private void offsetFullSpansForAddition(final int n, final int n2) {
        if (this.mFullSpanItems != null) {
            for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.mFullSpanItems.get(i);
                if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition >= n) {
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition += n2;
                }
            }
        }
    }
    
    private void offsetFullSpansForRemoval(final int n, final int n2) {
        if (this.mFullSpanItems != null) {
            for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.mFullSpanItems.get(i);
                if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition >= n) {
                    if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition < n + n2) {
                        this.mFullSpanItems.remove(i);
                    }
                    else {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition -= n2;
                    }
                }
            }
        }
    }
    
    public void addFullSpanItem(final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem) {
        if (this.mFullSpanItems == null) {
            this.mFullSpanItems = new ArrayList<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem>();
        }
        for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
            final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = this.mFullSpanItems.get(i);
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mPosition == staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition) {
                this.mFullSpanItems.remove(i);
            }
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mPosition >= staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition) {
                this.mFullSpanItems.add(i, staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
                return;
            }
        }
        this.mFullSpanItems.add(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
    }
    
    void clear() {
        if (this.mData != null) {
            Arrays.fill(this.mData, -1);
        }
        this.mFullSpanItems = null;
    }
    
    void ensureSize(final int n) {
        if (this.mData == null) {
            Arrays.fill(this.mData = new int[Math.max(n, 10) + 1], -1);
        }
        else if (n >= this.mData.length) {
            final int[] mData = this.mData;
            System.arraycopy(mData, 0, this.mData = new int[this.sizeForPosition(n)], 0, mData.length);
            Arrays.fill(this.mData, mData.length, this.mData.length, -1);
        }
    }
    
    int forceInvalidateAfter(final int n) {
        if (this.mFullSpanItems != null) {
            for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                if (this.mFullSpanItems.get(i).mPosition >= n) {
                    this.mFullSpanItems.remove(i);
                }
            }
        }
        return this.invalidateAfter(n);
    }
    
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem getFirstFullSpanItemInRange(final int n, final int n2, final int n3, final boolean b) {
        if (this.mFullSpanItems != null) {
            for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.mFullSpanItems.get(i);
                if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition >= n2) {
                    return null;
                }
                if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition >= n) {
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
                    if (n3 == 0) {
                        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2;
                    }
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
                    if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mGapDir == n3) {
                        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2;
                    }
                    if (b) {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
                        if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mHasUnwantedGapAfter) {
                            return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2;
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem getFullSpanItem(final int n) {
        if (this.mFullSpanItems != null) {
            for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
                if ((staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.mFullSpanItems.get(i)).mPosition == n) {
                    return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
                }
            }
            return null;
        }
        return null;
    }
    
    int getSpan(final int n) {
        if (this.mData == null || n >= this.mData.length) {
            return -1;
        }
        return this.mData[n];
    }
    
    int invalidateAfter(final int n) {
        if (this.mData == null || n >= this.mData.length) {
            return -1;
        }
        final int invalidateFullSpansAfter = this.invalidateFullSpansAfter(n);
        if (invalidateFullSpansAfter == -1) {
            Arrays.fill(this.mData, n, this.mData.length, -1);
            return this.mData.length;
        }
        Arrays.fill(this.mData, n, invalidateFullSpansAfter + 1, -1);
        return invalidateFullSpansAfter + 1;
    }
    
    void offsetForAddition(final int n, final int n2) {
        if (this.mData == null || n >= this.mData.length) {
            return;
        }
        this.ensureSize(n + n2);
        System.arraycopy(this.mData, n, this.mData, n + n2, this.mData.length - n - n2);
        Arrays.fill(this.mData, n, n + n2, -1);
        this.offsetFullSpansForAddition(n, n2);
    }
    
    void offsetForRemoval(final int n, final int n2) {
        if (this.mData == null || n >= this.mData.length) {
            return;
        }
        this.ensureSize(n + n2);
        System.arraycopy(this.mData, n + n2, this.mData, n, this.mData.length - n - n2);
        Arrays.fill(this.mData, this.mData.length - n2, this.mData.length, -1);
        this.offsetFullSpansForRemoval(n, n2);
    }
    
    void setSpan(final int n, final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span) {
        this.ensureSize(n);
        this.mData[n] = staggeredGridLayoutManager$Span.mIndex;
    }
    
    int sizeForPosition(final int n) {
        int i;
        for (i = this.mData.length; i <= n; i *= 2) {}
        return i;
    }
}
