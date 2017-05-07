// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class hm$b implements Result, SafeParcelable
{
    public static final ho CREATOR;
    final int BR;
    public Status Ck;
    public List<hs> Cl;
    
    static {
        CREATOR = new ho();
    }
    
    public hm$b() {
        this.BR = 1;
    }
    
    hm$b(final int br, final Status ck, final List<hs> cl) {
        this.BR = br;
        this.Ck = ck;
        this.Cl = cl;
    }
    
    public int describeContents() {
        final ho creator = hm$b.CREATOR;
        return 0;
    }
    
    @Override
    public Status getStatus() {
        return this.Ck;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ho creator = hm$b.CREATOR;
        ho.a(this, parcel, n);
    }
}
