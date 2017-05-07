// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import android.content.Intent;

public final class c
{
    public static <T extends SafeParcelable> T a(final Intent intent, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final byte[] byteArrayExtra = intent.getByteArrayExtra(s);
        if (byteArrayExtra == null) {
            return null;
        }
        return a(byteArrayExtra, parcelable$Creator);
    }
    
    public static <T extends SafeParcelable> T a(final byte[] array, final Parcelable$Creator<T> parcelable$Creator) {
        n.i(parcelable$Creator);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)parcelable$Creator.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
    
    public static <T extends SafeParcelable> void a(final T t, final Intent intent, final String s) {
        intent.putExtra(s, a(t));
    }
    
    public static <T extends SafeParcelable> byte[] a(final T t) {
        final Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
