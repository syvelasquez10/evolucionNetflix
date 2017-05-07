// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jd implements SafeParcelable
{
    public static final je CREATOR;
    private final int BR;
    private final jf Mk;
    
    static {
        CREATOR = new je();
    }
    
    jd(final int br, final jf mk) {
        this.BR = br;
        this.Mk = mk;
    }
    
    private jd(final jf mk) {
        this.BR = 1;
        this.Mk = mk;
    }
    
    public static jd a(final ji$b<?, ?> ji$b) {
        if (ji$b instanceof jf) {
            return new jd((jf)ji$b);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    public int describeContents() {
        final je creator = jd.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    jf ha() {
        return this.Mk;
    }
    
    public ji$b<?, ?> hb() {
        if (this.Mk != null) {
            return this.Mk;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final je creator = jd.CREATOR;
        je.a(this, parcel, n);
    }
}
