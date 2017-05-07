// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Asset implements SafeParcelable
{
    public static final Parcelable$Creator<Asset> CREATOR;
    final int BR;
    private byte[] acw;
    private String auF;
    public ParcelFileDescriptor auG;
    public Uri uri;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Asset(final int br, final byte[] acw, final String auF, final ParcelFileDescriptor auG, final Uri uri) {
        this.BR = br;
        this.acw = acw;
        this.auF = auF;
        this.auG = auG;
        this.uri = uri;
    }
    
    public static Asset createFromBytes(final byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Asset data cannot be null");
        }
        return new Asset(1, array, null, null, null);
    }
    
    public static Asset createFromFd(final ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            throw new IllegalArgumentException("Asset fd cannot be null");
        }
        return new Asset(1, null, null, parcelFileDescriptor, null);
    }
    
    public static Asset createFromRef(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Asset digest cannot be null");
        }
        return new Asset(1, null, s, null, null);
    }
    
    public static Asset createFromUri(final Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Asset uri cannot be null");
        }
        return new Asset(1, null, null, null, uri);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Asset)) {
                return false;
            }
            final Asset asset = (Asset)o;
            if (!m.equal(this.acw, asset.acw) || !m.equal(this.auF, asset.auF) || !m.equal(this.auG, asset.auG) || !m.equal(this.uri, asset.uri)) {
                return false;
            }
        }
        return true;
    }
    
    public byte[] getData() {
        return this.acw;
    }
    
    public String getDigest() {
        return this.auF;
    }
    
    public ParcelFileDescriptor getFd() {
        return this.auG;
    }
    
    public Uri getUri() {
        return this.uri;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.acw, this.auF, this.auG, this.uri);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Asset[@");
        sb.append(Integer.toHexString(this.hashCode()));
        if (this.auF == null) {
            sb.append(", nodigest");
        }
        else {
            sb.append(", ");
            sb.append(this.auF);
        }
        if (this.acw != null) {
            sb.append(", size=");
            sb.append(this.acw.length);
        }
        if (this.auG != null) {
            sb.append(", fd=");
            sb.append(this.auG);
        }
        if (this.uri != null) {
            sb.append(", uri=");
            sb.append(this.uri);
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n | 0x1);
    }
}
