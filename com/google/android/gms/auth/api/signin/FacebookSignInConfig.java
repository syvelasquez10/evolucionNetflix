// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.Collection;
import android.os.Parcel;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FacebookSignInConfig implements SafeParcelable
{
    public static final Parcelable$Creator<FacebookSignInConfig> CREATOR;
    private Intent mIntent;
    final int versionCode;
    private final ArrayList<String> zzRR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public FacebookSignInConfig() {
        this(1, null, new ArrayList<String>());
    }
    
    FacebookSignInConfig(final int versionCode, final Intent mIntent, final ArrayList<String> zzRR) {
        this.versionCode = versionCode;
        this.mIntent = mIntent;
        this.zzRR = zzRR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public Intent zzlD() {
        return this.mIntent;
    }
    
    public ArrayList<String> zzlE() {
        return new ArrayList<String>(this.zzRR);
    }
}
