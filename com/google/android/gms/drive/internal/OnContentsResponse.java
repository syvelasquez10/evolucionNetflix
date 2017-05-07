// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnContentsResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnContentsResponse> CREATOR;
    final int BR;
    final Contents Op;
    final boolean Pg;
    
    static {
        CREATOR = (Parcelable$Creator)new ai();
    }
    
    OnContentsResponse(final int br, final Contents op, final boolean pg) {
        this.BR = br;
        this.Op = op;
        this.Pg = pg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Contents id() {
        return this.Op;
    }
    
    public boolean ie() {
        return this.Pg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ai.a(this, parcel, n);
    }
}
