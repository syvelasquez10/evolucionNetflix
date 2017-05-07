// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDownloadProgressResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnDownloadProgressResponse> CREATOR;
    final int BR;
    final long Ph;
    final long Pi;
    
    static {
        CREATOR = (Parcelable$Creator)new aj();
    }
    
    OnDownloadProgressResponse(final int br, final long ph, final long pi) {
        this.BR = br;
        this.Ph = ph;
        this.Pi = pi;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long if() {
        return this.Ph;
    }
    
    public long ig() {
        return this.Pi;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aj.a(this, parcel, n);
    }
}
