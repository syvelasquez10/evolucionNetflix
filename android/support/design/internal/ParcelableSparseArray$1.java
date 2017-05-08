// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class ParcelableSparseArray$1 implements ParcelableCompatCreatorCallbacks<ParcelableSparseArray>
{
    @Override
    public ParcelableSparseArray createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new ParcelableSparseArray(parcel, classLoader);
    }
    
    @Override
    public ParcelableSparseArray[] newArray(final int n) {
        return new ParcelableSparseArray[n];
    }
}
