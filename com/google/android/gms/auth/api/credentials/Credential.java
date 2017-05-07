// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.text.TextUtils;
import java.util.Collections;
import com.google.android.gms.common.internal.zzx;
import java.util.List;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Credential implements SafeParcelable
{
    public static final Parcelable$Creator<Credential> CREATOR;
    private final String mName;
    final int mVersionCode;
    private final Uri zzSh;
    private final List<IdToken> zzSi;
    private final String zzSj;
    private final String zzSk;
    private final String zzSl;
    private final String zzSm;
    private final String zzwN;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    Credential(final int mVersionCode, final String s, final String mName, final Uri zzSh, final List<IdToken> list, final String zzSj, final String zzSk, final String zzSl, final String zzSm) {
        this.mVersionCode = mVersionCode;
        this.zzwN = zzx.zzw(s);
        this.mName = mName;
        this.zzSh = zzSh;
        List<IdToken> zzSi;
        if (list == null) {
            zzSi = Collections.emptyList();
        }
        else {
            zzSi = Collections.unmodifiableList((List<? extends IdToken>)list);
        }
        this.zzSi = zzSi;
        this.zzSj = zzSj;
        this.zzSk = zzSk;
        this.zzSl = zzSl;
        this.zzSm = zzSm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Credential)) {
                return false;
            }
            final Credential credential = (Credential)o;
            if (!TextUtils.equals((CharSequence)this.zzwN, (CharSequence)credential.zzwN) || !TextUtils.equals((CharSequence)this.mName, (CharSequence)credential.mName) || !zzw.equal(this.zzSh, credential.zzSh) || !TextUtils.equals((CharSequence)this.zzSj, (CharSequence)credential.zzSj) || !TextUtils.equals((CharSequence)this.zzSk, (CharSequence)credential.zzSk) || !TextUtils.equals((CharSequence)this.zzSl, (CharSequence)credential.zzSl)) {
                return false;
            }
        }
        return true;
    }
    
    public String getAccountType() {
        return this.zzSk;
    }
    
    public String getGeneratedPassword() {
        return this.zzSl;
    }
    
    public String getId() {
        return this.zzwN;
    }
    
    public List<IdToken> getIdTokens() {
        return this.zzSi;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getPassword() {
        return this.zzSj;
    }
    
    public Uri getProfilePictureUri() {
        return this.zzSh;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzwN, this.mName, this.zzSh, this.zzSj, this.zzSk, this.zzSl);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzlI() {
        return this.zzSm;
    }
}
