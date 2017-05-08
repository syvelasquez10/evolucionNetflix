// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Arrays;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable
{
    public static final Parcelable$Creator<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> CREATOR;
    int mGapDir;
    int[] mGapPerSpan;
    boolean mHasUnwantedGapAfter;
    int mPosition;
    
    static {
        CREATOR = (Parcelable$Creator)new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem$1();
    }
    
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem() {
    }
    
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(final Parcel parcel) {
        boolean mHasUnwantedGapAfter = true;
        this.mPosition = parcel.readInt();
        this.mGapDir = parcel.readInt();
        if (parcel.readInt() != 1) {
            mHasUnwantedGapAfter = false;
        }
        this.mHasUnwantedGapAfter = mHasUnwantedGapAfter;
        final int int1 = parcel.readInt();
        if (int1 > 0) {
            parcel.readIntArray(this.mGapPerSpan = new int[int1]);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getGapForSpan(final int n) {
        if (this.mGapPerSpan == null) {
            return 0;
        }
        return this.mGapPerSpan[n];
    }
    
    @Override
    public String toString() {
        return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mGapDir);
        if (this.mHasUnwantedGapAfter) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.mGapPerSpan != null && this.mGapPerSpan.length > 0) {
            parcel.writeInt(this.mGapPerSpan.length);
            parcel.writeIntArray(this.mGapPerSpan);
            return;
        }
        parcel.writeInt(0);
    }
}
