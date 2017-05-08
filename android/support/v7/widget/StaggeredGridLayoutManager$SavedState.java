// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class StaggeredGridLayoutManager$SavedState implements Parcelable
{
    public static final Parcelable$Creator<StaggeredGridLayoutManager$SavedState> CREATOR;
    boolean mAnchorLayoutFromEnd;
    int mAnchorPosition;
    List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> mFullSpanItems;
    boolean mLastLayoutRTL;
    boolean mReverseLayout;
    int[] mSpanLookup;
    int mSpanLookupSize;
    int[] mSpanOffsets;
    int mSpanOffsetsSize;
    int mVisibleAnchorPosition;
    
    static {
        CREATOR = (Parcelable$Creator)new StaggeredGridLayoutManager$SavedState$1();
    }
    
    public StaggeredGridLayoutManager$SavedState() {
    }
    
    StaggeredGridLayoutManager$SavedState(final Parcel parcel) {
        final boolean b = true;
        this.mAnchorPosition = parcel.readInt();
        this.mVisibleAnchorPosition = parcel.readInt();
        this.mSpanOffsetsSize = parcel.readInt();
        if (this.mSpanOffsetsSize > 0) {
            parcel.readIntArray(this.mSpanOffsets = new int[this.mSpanOffsetsSize]);
        }
        this.mSpanLookupSize = parcel.readInt();
        if (this.mSpanLookupSize > 0) {
            parcel.readIntArray(this.mSpanLookup = new int[this.mSpanLookupSize]);
        }
        this.mReverseLayout = (parcel.readInt() == 1);
        this.mAnchorLayoutFromEnd = (parcel.readInt() == 1);
        this.mLastLayoutRTL = (parcel.readInt() == 1 && b);
        this.mFullSpanItems = (List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem>)parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
    }
    
    public StaggeredGridLayoutManager$SavedState(final StaggeredGridLayoutManager$SavedState staggeredGridLayoutManager$SavedState) {
        this.mSpanOffsetsSize = staggeredGridLayoutManager$SavedState.mSpanOffsetsSize;
        this.mAnchorPosition = staggeredGridLayoutManager$SavedState.mAnchorPosition;
        this.mVisibleAnchorPosition = staggeredGridLayoutManager$SavedState.mVisibleAnchorPosition;
        this.mSpanOffsets = staggeredGridLayoutManager$SavedState.mSpanOffsets;
        this.mSpanLookupSize = staggeredGridLayoutManager$SavedState.mSpanLookupSize;
        this.mSpanLookup = staggeredGridLayoutManager$SavedState.mSpanLookup;
        this.mReverseLayout = staggeredGridLayoutManager$SavedState.mReverseLayout;
        this.mAnchorLayoutFromEnd = staggeredGridLayoutManager$SavedState.mAnchorLayoutFromEnd;
        this.mLastLayoutRTL = staggeredGridLayoutManager$SavedState.mLastLayoutRTL;
        this.mFullSpanItems = staggeredGridLayoutManager$SavedState.mFullSpanItems;
    }
    
    public int describeContents() {
        return 0;
    }
    
    void invalidateAnchorPositionInfo() {
        this.mSpanOffsets = null;
        this.mSpanOffsetsSize = 0;
        this.mAnchorPosition = -1;
        this.mVisibleAnchorPosition = -1;
    }
    
    void invalidateSpanInfo() {
        this.mSpanOffsets = null;
        this.mSpanOffsetsSize = 0;
        this.mSpanLookupSize = 0;
        this.mSpanLookup = null;
        this.mFullSpanItems = null;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        parcel.writeInt(this.mAnchorPosition);
        parcel.writeInt(this.mVisibleAnchorPosition);
        parcel.writeInt(this.mSpanOffsetsSize);
        if (this.mSpanOffsetsSize > 0) {
            parcel.writeIntArray(this.mSpanOffsets);
        }
        parcel.writeInt(this.mSpanLookupSize);
        if (this.mSpanLookupSize > 0) {
            parcel.writeIntArray(this.mSpanLookup);
        }
        if (this.mReverseLayout) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.mAnchorLayoutFromEnd) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.mLastLayoutRTL) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeList((List)this.mFullSpanItems);
    }
}
