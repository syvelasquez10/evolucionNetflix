// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c<T extends SafeParcelable> extends DataBuffer<T>
{
    private static final String[] nI;
    private final Parcelable$Creator<T> nJ;
    
    static {
        nI = new String[] { "data" };
    }
    
    public c(final DataHolder dataHolder, final Parcelable$Creator<T> nj) {
        super(dataHolder);
        this.nJ = nj;
    }
    
    public T B(final int n) {
        final byte[] byteArray = this.nE.getByteArray("data", n, 0);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(byteArray, 0, byteArray.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.nJ.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
