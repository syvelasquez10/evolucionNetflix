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
    private final Uri zzacb;
    private final int zznP;
    private final int zznQ;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    WebImage(final int mVersionCode, final Uri zzacb, final int zznP, final int zznQ) {
        this.mVersionCode = mVersionCode;
        this.zzacb = zzacb;
        this.zznP = zznP;
        this.zznQ = zznQ;
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
            if (!zzw.equal(this.zzacb, webImage.zzacb) || this.zznP != webImage.zznP || this.zznQ != webImage.zznQ) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zznQ;
    }
    
    public Uri getUrl() {
        return this.zzacb;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public int getWidth() {
        return this.zznP;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzacb, this.zznP, this.zznQ);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.zznP, this.zznQ, this.zzacb.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
