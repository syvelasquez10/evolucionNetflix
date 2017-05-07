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
    String zzUM;
    List<String> zzUN;
    String zzUO;
    Uri zzUP;
    List<WebImage> zzwp;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    private ApplicationMetadata() {
        this.mVersionCode = 1;
        this.zzwp = new ArrayList<WebImage>();
        this.zzUN = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int mVersionCode, final String zzUM, final String mName, final List<WebImage> zzwp, final List<String> zzUN, final String zzUO, final Uri zzUP) {
        this.mVersionCode = mVersionCode;
        this.zzUM = zzUM;
        this.mName = mName;
        this.zzwp = zzwp;
        this.zzUN = zzUN;
        this.zzUO = zzUO;
        this.zzUP = zzUP;
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
            if (!zzf.zza(this.zzUM, applicationMetadata.zzUM) || !zzf.zza(this.zzwp, applicationMetadata.zzwp) || !zzf.zza(this.mName, applicationMetadata.mName) || !zzf.zza(this.zzUN, applicationMetadata.zzUN) || !zzf.zza(this.zzUO, applicationMetadata.zzUO) || !zzf.zza(this.zzUP, applicationMetadata.zzUP)) {
                return false;
            }
        }
        return true;
    }
    
    public String getApplicationId() {
        return this.zzUM;
    }
    
    public List<WebImage> getImages() {
        return this.zzwp;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.zzUO;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mVersionCode, this.zzUM, this.mName, this.zzwp, this.zzUN, this.zzUO, this.zzUP);
    }
    
    @Override
    public String toString() {
        final int n = 0;
        final StringBuilder append = new StringBuilder().append("applicationId: ").append(this.zzUM).append(", name: ").append(this.mName).append(", images.count: ");
        int size;
        if (this.zzwp == null) {
            size = 0;
        }
        else {
            size = this.zzwp.size();
        }
        final StringBuilder append2 = append.append(size).append(", namespaces.count: ");
        int size2;
        if (this.zzUN == null) {
            size2 = n;
        }
        else {
            size2 = this.zzUN.size();
        }
        return append2.append(size2).append(", senderAppIdentifier: ").append(this.zzUO).append(", senderAppLaunchUrl: ").append(this.zzUP).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzmj() {
        return this.zzUP;
    }
}
