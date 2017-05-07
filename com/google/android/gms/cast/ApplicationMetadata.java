// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
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
    private final int mVersionCode;
    String zzSX;
    List<String> zzSY;
    String zzSZ;
    Uri zzTa;
    List<WebImage> zzvL;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    private ApplicationMetadata() {
        this.mVersionCode = 1;
        this.zzvL = new ArrayList<WebImage>();
        this.zzSY = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int mVersionCode, final String zzSX, final String mName, final List<WebImage> zzvL, final List<String> zzSY, final String zzSZ, final Uri zzTa) {
        this.mVersionCode = mVersionCode;
        this.zzSX = zzSX;
        this.mName = mName;
        this.zzvL = zzvL;
        this.zzSY = zzSY;
        this.zzSZ = zzSZ;
        this.zzTa = zzTa;
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
            if (!zzf.zza(this.zzSX, applicationMetadata.zzSX) || !zzf.zza(this.zzvL, applicationMetadata.zzvL) || !zzf.zza(this.mName, applicationMetadata.mName) || !zzf.zza(this.zzSY, applicationMetadata.zzSY) || !zzf.zza(this.zzSZ, applicationMetadata.zzSZ) || !zzf.zza(this.zzTa, applicationMetadata.zzTa)) {
                return false;
            }
        }
        return true;
    }
    
    public String getApplicationId() {
        return this.zzSX;
    }
    
    public List<WebImage> getImages() {
        return this.zzvL;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.zzSZ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mVersionCode, this.zzSX, this.mName, this.zzvL, this.zzSY, this.zzSZ, this.zzTa);
    }
    
    @Override
    public String toString() {
        final int n = 0;
        final StringBuilder append = new StringBuilder().append("applicationId: ").append(this.zzSX).append(", name: ").append(this.mName).append(", images.count: ");
        int size;
        if (this.zzvL == null) {
            size = 0;
        }
        else {
            size = this.zzvL.size();
        }
        final StringBuilder append2 = append.append(size).append(", namespaces.count: ");
        int size2;
        if (this.zzSY == null) {
            size2 = n;
        }
        else {
            size2 = this.zzSY.size();
        }
        return append2.append(size2).append(", senderAppIdentifier: ").append(this.zzSZ).append(", senderAppLaunchUrl: ").append(this.zzTa).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzlM() {
        return this.zzTa;
    }
}
