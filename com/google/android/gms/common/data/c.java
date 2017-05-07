// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c<T extends SafeParcelable> extends DataBuffer<T>
{
    private static final String[] BF;
    private final Parcelable$Creator<T> BG;
    
    static {
        BF = new String[] { "data" };
    }
    
    public c(final DataHolder dataHolder, final Parcelable$Creator<T> bg) {
        super(dataHolder);
        this.BG = bg;
    }
    
    public T F(final int n) {
        final byte[] byteArray = this.BB.getByteArray("data", n, 0);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(byteArray, 0, byteArray.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.BG.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
