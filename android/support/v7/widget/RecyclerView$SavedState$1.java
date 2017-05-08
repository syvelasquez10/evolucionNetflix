// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class RecyclerView$SavedState$1 implements ParcelableCompatCreatorCallbacks<RecyclerView$SavedState>
{
    @Override
    public RecyclerView$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new RecyclerView$SavedState(parcel, classLoader);
    }
    
    @Override
    public RecyclerView$SavedState[] newArray(final int n) {
        return new RecyclerView$SavedState[n];
    }
}
