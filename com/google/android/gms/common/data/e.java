// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class e<T extends SafeParcelable> extends DataBuffer<T>
{
    private static final String[] JS;
    private final Parcelable$Creator<T> JT;
    
    static {
        JS = new String[] { "data" };
    }
    
    public e(final DataHolder dataHolder, final Parcelable$Creator<T> jt) {
        super(dataHolder);
        this.JT = jt;
    }
    
    public T aq(final int n) {
        final byte[] f = this.IC.f("data", n, 0);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(f, 0, f.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.JT.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
