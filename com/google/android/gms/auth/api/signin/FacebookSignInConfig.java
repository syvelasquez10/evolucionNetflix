// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FacebookSignInConfig implements SafeParcelable
{
    public static final Parcelable$Creator<FacebookSignInConfig> CREATOR;
    private Intent mIntent;
    final int versionCode;
    private final ArrayList<String> zzSX;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public FacebookSignInConfig() {
        this(1, null, new ArrayList<String>());
    }
    
    FacebookSignInConfig(final int versionCode, final Intent mIntent, final ArrayList<String> zzSX) {
        this.versionCode = versionCode;
        this.mIntent = mIntent;
        this.zzSX = zzSX;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final FacebookSignInConfig facebookSignInConfig = (FacebookSignInConfig)o;
                if (this.zzSX.size() == facebookSignInConfig.zzlS().size() && this.zzSX.containsAll(facebookSignInConfig.zzlS())) {
                    return true;
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        Collections.sort(this.zzSX);
        return this.zzSX.hashCode();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public Intent zzlR() {
        return this.mIntent;
    }
    
    public ArrayList<String> zzlS() {
        return new ArrayList<String>(this.zzSX);
    }
}
