// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.cast.internal.zzf;
import java.util.Locale;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LaunchOptions implements SafeParcelable
{
    public static final Parcelable$Creator<LaunchOptions> CREATOR;
    private final int zzCY;
    private boolean zzRy;
    private String zzRz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public LaunchOptions() {
        this(1, false, zzf.zzb(Locale.getDefault()));
    }
    
    LaunchOptions(final int zzCY, final boolean zzRy, final String zzRz) {
        this.zzCY = zzCY;
        this.zzRy = zzRy;
        this.zzRz = zzRz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof LaunchOptions)) {
                return false;
            }
            final LaunchOptions launchOptions = (LaunchOptions)o;
            if (this.zzRy != launchOptions.zzRy || !zzf.zza(this.zzRz, launchOptions.zzRz)) {
                return false;
            }
        }
        return true;
    }
    
    public String getLanguage() {
        return this.zzRz;
    }
    
    public boolean getRelaunchIfRunning() {
        return this.zzRy;
    }
    
    int getVersionCode() {
        return this.zzCY;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzRy, this.zzRz);
    }
    
    @Override
    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", this.zzRy, this.zzRz);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
