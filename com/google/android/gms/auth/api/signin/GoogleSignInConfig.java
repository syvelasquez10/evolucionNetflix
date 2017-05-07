// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.Collection;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleSignInConfig implements SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInConfig> CREATOR;
    final int versionCode;
    private final ArrayList<Scope> zzRR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public GoogleSignInConfig() {
        this(1, new ArrayList<Scope>());
    }
    
    GoogleSignInConfig(final int versionCode, final ArrayList<Scope> zzRR) {
        this.versionCode = versionCode;
        this.zzRR = zzRR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
    
    public ArrayList<Scope> zzlE() {
        return new ArrayList<Scope>(this.zzRR);
    }
}
