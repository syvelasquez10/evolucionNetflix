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
    final long FF;
    final long FG;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new aa();
    }
    
    OnDownloadProgressResponse(final int xh, final long ff, final long fg) {
        this.xH = xh;
        this.FF = ff;
        this.FG = fg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long fJ() {
        return this.FF;
    }
    
    public long fK() {
        return this.FG;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aa.a(this, parcel, n);
    }
}
