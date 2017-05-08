// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ClientIdentity implements SafeParcelable
{
    public static final zzc CREATOR;
    private final int mVersionCode;
    public final String packageName;
    public final int uid;
    
    static {
        CREATOR = new zzc();
    }
    
    ClientIdentity(final int mVersionCode, final int uid, final String packageName) {
        this.mVersionCode = mVersionCode;
        this.uid = uid;
        this.packageName = packageName;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null || !(o instanceof ClientIdentity)) {
                return false;
            }
            final ClientIdentity clientIdentity = (ClientIdentity)o;
            if (clientIdentity.uid != this.uid || !zzw.equal(clientIdentity.packageName, this.packageName)) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return this.uid;
    }
    
    @Override
    public String toString() {
        return String.format("%d:%s", this.uid, this.packageName);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
