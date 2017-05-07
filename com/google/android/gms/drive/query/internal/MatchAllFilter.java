// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;

public class MatchAllFilter extends AbstractFilter
{
    public static final j CREATOR;
    final int BR;
    
    static {
        CREATOR = new j();
    }
    
    public MatchAllFilter() {
        this(1);
    }
    
    MatchAllFilter(final int br) {
        this.BR = br;
    }
    
    @Override
    public <F> F a(final f<F> f) {
        return f.is();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
