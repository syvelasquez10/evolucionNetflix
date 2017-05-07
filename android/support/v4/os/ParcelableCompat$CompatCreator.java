// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable$Creator;

class ParcelableCompat$CompatCreator<T> implements Parcelable$Creator<T>
{
    final ParcelableCompatCreatorCallbacks<T> mCallbacks;
    
    public ParcelableCompat$CompatCreator(final ParcelableCompatCreatorCallbacks<T> mCallbacks) {
        this.mCallbacks = mCallbacks;
    }
    
    public T createFromParcel(final Parcel parcel) {
        return this.mCallbacks.createFromParcel(parcel, null);
    }
    
    public T[] newArray(final int n) {
        return this.mCallbacks.newArray(n);
    }
}
