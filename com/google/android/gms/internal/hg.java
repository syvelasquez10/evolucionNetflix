// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hg implements SafeParcelable
{
    public static final hh CREATOR;
    final int BR;
    final String BZ;
    final String Ca;
    final String Cb;
    
    static {
        CREATOR = new hh();
    }
    
    hg(final int br, final String bz, final String ca, final String cb) {
        this.BR = br;
        this.BZ = bz;
        this.Ca = ca;
        this.Cb = cb;
    }
    
    public hg(final String s, final String s2, final String s3) {
        this(1, s, s2, s3);
    }
    
    public int describeContents() {
        final hh creator = hg.CREATOR;
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", this.BZ, this.Ca, this.Cb);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hh creator = hg.CREATOR;
        hh.a(this, parcel, n);
    }
}
