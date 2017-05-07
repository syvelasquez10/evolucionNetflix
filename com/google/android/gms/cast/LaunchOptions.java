// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.cast.internal.zzf;
import java.util.Locale;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LaunchOptions implements SafeParcelable
{
    public static final Parcelable$Creator<LaunchOptions> CREATOR;
    private final int mVersionCode;
    private boolean zzVS;
    private String zzVT;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    public LaunchOptions() {
        this(1, false, zzf.zzb(Locale.getDefault()));
    }
    
    LaunchOptions(final int mVersionCode, final boolean zzVS, final String zzVT) {
        this.mVersionCode = mVersionCode;
        this.zzVS = zzVS;
        this.zzVT = zzVT;
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
            if (this.zzVS != launchOptions.zzVS || !zzf.zza(this.zzVT, launchOptions.zzVT)) {
                return false;
            }
        }
        return true;
    }
    
    public String getLanguage() {
        return this.zzVT;
    }
    
    public boolean getRelaunchIfRunning() {
        return this.zzVS;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzVS, this.zzVT);
    }
    
    @Override
    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", this.zzVS, this.zzVT);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
