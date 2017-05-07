// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class me implements SafeParcelable
{
    public static final mf CREATOR;
    final int BR;
    private final boolean afc;
    private final List<mo> afd;
    
    static {
        CREATOR = new mf();
    }
    
    me(final int br, final boolean afc, final List<mo> afd) {
        this.BR = br;
        this.afc = afc;
        this.afd = afd;
    }
    
    public int describeContents() {
        final mf creator = me.CREATOR;
        return 0;
    }
    
    public boolean mc() {
        return this.afc;
    }
    
    public List<mo> md() {
        return this.afd;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mf creator = me.CREATOR;
        mf.a(this, parcel, n);
    }
}
