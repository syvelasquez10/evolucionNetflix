// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final int BR;
    private final Uri KJ;
    private final int lf;
    private final int lg;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    WebImage(final int br, final Uri kj, final int lf, final int lg) {
        this.BR = br;
        this.KJ = kj;
        this.lf = lf;
        this.lg = lg;
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
            if (!m.equal(this.KJ, webImage.KJ) || this.lf != webImage.lf || this.lg != webImage.lg) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.lg;
    }
    
    public Uri getUrl() {
        return this.KJ;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public int getWidth() {
        return this.lf;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.KJ, this.lf, this.lg);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.lf, this.lg, this.KJ.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
