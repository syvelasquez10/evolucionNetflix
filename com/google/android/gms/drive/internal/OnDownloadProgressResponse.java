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
    final int kg;
    final long rx;
    final long ry;
    
    static {
        CREATOR = (Parcelable$Creator)new s();
    }
    
    OnDownloadProgressResponse(final int kg, final long rx, final long ry) {
        this.kg = kg;
        this.rx = rx;
        this.ry = ry;
    }
    
    public long cR() {
        return this.rx;
    }
    
    public long cS() {
        return this.ry;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        s.a(this, parcel, n);
    }
}
