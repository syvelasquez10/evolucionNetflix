// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jm$b implements SafeParcelable
{
    public static final jl CREATOR;
    final ji$a<?, ?> ME;
    final String fv;
    final int versionCode;
    
    static {
        CREATOR = new jl();
    }
    
    jm$b(final int versionCode, final String fv, final ji$a<?, ?> me) {
        this.versionCode = versionCode;
        this.fv = fv;
        this.ME = me;
    }
    
    jm$b(final String fv, final ji$a<?, ?> me) {
        this.versionCode = 1;
        this.fv = fv;
        this.ME = me;
    }
    
    public int describeContents() {
        final jl creator = jm$b.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jl creator = jm$b.CREATOR;
        jl.a(this, parcel, n);
    }
}
