// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.fitness.data.DataSource;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class lh implements SafeParcelable
{
    public static final Parcelable$Creator<lh> CREATOR;
    private final int BR;
    private final DataSource Sh;
    
    static {
        CREATOR = (Parcelable$Creator)new li();
    }
    
    lh(final int br, final DataSource sh) {
        this.BR = br;
        this.Sh = sh;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", this.Sh);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        li.a(this, parcel, n);
    }
}
