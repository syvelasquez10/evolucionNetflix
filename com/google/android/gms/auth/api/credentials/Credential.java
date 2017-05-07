// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import java.util.Collections;
import com.google.android.gms.common.internal.zzu;
import android.net.Uri;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Credential implements SafeParcelable
{
    public static final Parcelable$Creator<Credential> CREATOR;
    private final List<IdToken> mIdTokens;
    private final String mName;
    final int zzCY;
    private final String zzKI;
    private final String zzOS;
    private final String zzOT;
    private final Uri zzOU;
    private final String zzOV;
    private final String zzOW;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    Credential(final int zzCY, final String zzOS, final String zzOT, final String s, final String mName, final Uri zzOU, final List<IdToken> list, final String zzOV, final String zzOW) {
        this.zzCY = zzCY;
        this.zzOS = zzOS;
        this.zzOT = zzOT;
        this.zzKI = zzu.zzu(s);
        this.mName = mName;
        this.zzOU = zzOU;
        List<IdToken> mIdTokens;
        if (list == null) {
            mIdTokens = Collections.emptyList();
        }
        else {
            mIdTokens = Collections.unmodifiableList((List<? extends IdToken>)list);
        }
        this.mIdTokens = mIdTokens;
        this.zzOV = zzOV;
        this.zzOW = zzOW;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountType() {
        return this.zzOW;
    }
    
    public String getId() {
        return this.zzKI;
    }
    
    public List<IdToken> getIdTokens() {
        return this.mIdTokens;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getPassword() {
        return this.zzOV;
    }
    
    public Uri getProfilePictureUri() {
        return this.zzOU;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzkZ() {
        return this.zzOS;
    }
    
    public String zzla() {
        return this.zzOT;
    }
}
