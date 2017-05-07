// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final int mVersionCode;
    private final Uri zzaeg;
    private final int zznQ;
    private final int zznR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    WebImage(final int mVersionCode, final Uri zzaeg, final int zznQ, final int zznR) {
        this.mVersionCode = mVersionCode;
        this.zzaeg = zzaeg;
        this.zznQ = zznQ;
        this.zznR = zznR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof WebImage)) {
                return false;
            }
            final WebImage webImage = (WebImage)o;
            if (!zzw.equal(this.zzaeg, webImage.zzaeg) || this.zznQ != webImage.zznQ || this.zznR != webImage.zznR) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zznR;
    }
    
    public Uri getUrl() {
        return this.zzaeg;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public int getWidth() {
        return this.zznQ;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzaeg, this.zznQ, this.zznR);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.zznQ, this.zznR, this.zzaeg.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
