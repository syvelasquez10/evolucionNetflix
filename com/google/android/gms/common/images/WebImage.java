// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzt;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final int zzCY;
    private final Uri zzZm;
    private final int zznM;
    private final int zznN;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    WebImage(final int zzCY, final Uri zzZm, final int zznM, final int zznN) {
        this.zzCY = zzCY;
        this.zzZm = zzZm;
        this.zznM = zznM;
        this.zznN = zznN;
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
            if (!zzt.equal(this.zzZm, webImage.zzZm) || this.zznM != webImage.zznM || this.zznN != webImage.zznN) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zznN;
    }
    
    public Uri getUrl() {
        return this.zzZm;
    }
    
    int getVersionCode() {
        return this.zzCY;
    }
    
    public int getWidth() {
        return this.zznM;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzZm, this.zznM, this.zznN);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.zznM, this.zznN, this.zzZm.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
