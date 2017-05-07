// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.i;

public class OnListEntriesResponse extends i implements SafeParcelable
{
    public static final Parcelable$Creator<OnListEntriesResponse> CREATOR;
    final int BR;
    final boolean Or;
    final DataHolder Pm;
    
    static {
        CREATOR = (Parcelable$Creator)new an();
    }
    
    OnListEntriesResponse(final int br, final DataHolder pm, final boolean or) {
        this.BR = br;
        this.Pm = pm;
        this.Or = or;
    }
    
    @Override
    protected void I(final Parcel parcel, final int n) {
        an.a(this, parcel, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataHolder ii() {
        return this.Pm;
    }
    
    public boolean ij() {
        return this.Or;
    }
}
