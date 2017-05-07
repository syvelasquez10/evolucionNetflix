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
    private boolean zzUb;
    private String zzUc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public LaunchOptions() {
        this(1, false, zzf.zzb(Locale.getDefault()));
    }
    
    LaunchOptions(final int mVersionCode, final boolean zzUb, final String zzUc) {
        this.mVersionCode = mVersionCode;
        this.zzUb = zzUb;
        this.zzUc = zzUc;
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
            if (this.zzUb != launchOptions.zzUb || !zzf.zza(this.zzUc, launchOptions.zzUc)) {
                return false;
            }
        }
        return true;
    }
    
    public String getLanguage() {
        return this.zzUc;
    }
    
    public boolean getRelaunchIfRunning() {
        return this.zzUb;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzUb, this.zzUc);
    }
    
    @Override
    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", this.zzUb, this.zzUc);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
