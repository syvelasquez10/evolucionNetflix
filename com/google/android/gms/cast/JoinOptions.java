// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class JoinOptions implements SafeParcelable
{
    public static final Parcelable$Creator<JoinOptions> CREATOR;
    private final int mVersionCode;
    private int zzVR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public JoinOptions() {
        this(1, 0);
    }
    
    JoinOptions(final int mVersionCode, final int zzVR) {
        this.mVersionCode = mVersionCode;
        this.zzVR = zzVR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof JoinOptions)) {
                return false;
            }
            if (this.zzVR != ((JoinOptions)o).zzVR) {
                return false;
            }
        }
        return true;
    }
    
    public int getConnectionType() {
        return this.zzVR;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzVR);
    }
    
    @Override
    public String toString() {
        String s = null;
        switch (this.zzVR) {
            default: {
                s = "UNKNOWN";
                break;
            }
            case 0: {
                s = "STRONG";
                break;
            }
            case 2: {
                s = "INVISIBLE";
                break;
            }
        }
        return String.format("joinOptions(connectionType=%s)", s);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
