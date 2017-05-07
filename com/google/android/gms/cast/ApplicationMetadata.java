// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.cast.internal.zzf;
import java.util.ArrayList;
import com.google.android.gms.common.images.WebImage;
import android.net.Uri;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ApplicationMetadata implements SafeParcelable
{
    public static final Parcelable$Creator<ApplicationMetadata> CREATOR;
    String mName;
    private final int zzCY;
    String zzQu;
    List<String> zzQv;
    String zzQw;
    Uri zzQx;
    List<WebImage> zzvi;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    private ApplicationMetadata() {
        this.zzCY = 1;
        this.zzvi = new ArrayList<WebImage>();
        this.zzQv = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int zzCY, final String zzQu, final String mName, final List<WebImage> zzvi, final List<String> zzQv, final String zzQw, final Uri zzQx) {
        this.zzCY = zzCY;
        this.zzQu = zzQu;
        this.mName = mName;
        this.zzvi = zzvi;
        this.zzQv = zzQv;
        this.zzQw = zzQw;
        this.zzQx = zzQx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ApplicationMetadata)) {
                return false;
            }
            final ApplicationMetadata applicationMetadata = (ApplicationMetadata)o;
            if (!zzf.zza(this.zzQu, applicationMetadata.zzQu) || !zzf.zza(this.zzvi, applicationMetadata.zzvi) || !zzf.zza(this.mName, applicationMetadata.mName) || !zzf.zza(this.zzQv, applicationMetadata.zzQv) || !zzf.zza(this.zzQw, applicationMetadata.zzQw) || !zzf.zza(this.zzQx, applicationMetadata.zzQx)) {
                return false;
            }
        }
        return true;
    }
    
    public String getApplicationId() {
        return this.zzQu;
    }
    
    public List<WebImage> getImages() {
        return this.zzvi;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.zzQw;
    }
    
    int getVersionCode() {
        return this.zzCY;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzCY, this.zzQu, this.mName, this.zzvi, this.zzQv, this.zzQw, this.zzQx);
    }
    
    @Override
    public String toString() {
        final int n = 0;
        final StringBuilder append = new StringBuilder().append("applicationId: ").append(this.zzQu).append(", name: ").append(this.mName).append(", images.count: ");
        int size;
        if (this.zzvi == null) {
            size = 0;
        }
        else {
            size = this.zzvi.size();
        }
        final StringBuilder append2 = append.append(size).append(", namespaces.count: ");
        int size2;
        if (this.zzQv == null) {
            size2 = n;
        }
        else {
            size2 = this.zzQv.size();
        }
        return append2.append(size2).append(", senderAppIdentifier: ").append(this.zzQw).append(", senderAppLaunchUrl: ").append(this.zzQx).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzlc() {
        return this.zzQx;
    }
}
