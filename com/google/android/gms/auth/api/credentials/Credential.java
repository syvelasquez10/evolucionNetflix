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
    private final String zzRa;
    private final String zzRb;
    private final Uri zzRc;
    private final List<IdToken> zzRd;
    private final String zzRe;
    private final String zzRf;
    private final String zzRg;
    private final String zzRh;
    private final String zzwj;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    Credential(final int mVersionCode, final String zzRa, final String zzRb, final String s, final String mName, final Uri zzRc, final List<IdToken> list, final String zzRe, final String zzRf, final String zzRg, final String zzRh) {
        this.mVersionCode = mVersionCode;
        this.zzRa = zzRa;
        this.zzRb = zzRb;
        this.zzwj = zzx.zzv(s);
        this.mName = mName;
        this.zzRc = zzRc;
        List<IdToken> zzRd;
        if (list == null) {
            zzRd = Collections.emptyList();
        }
        else {
            zzRd = Collections.unmodifiableList((List<? extends IdToken>)list);
        }
        this.zzRd = zzRd;
        this.zzRe = zzRe;
        this.zzRf = zzRf;
        this.zzRg = zzRg;
        this.zzRh = zzRh;
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
            if (!TextUtils.equals((CharSequence)this.zzwj, (CharSequence)credential.zzwj) || !TextUtils.equals((CharSequence)this.mName, (CharSequence)credential.mName) || !zzw.equal(this.zzRc, credential.zzRc) || !TextUtils.equals((CharSequence)this.zzRe, (CharSequence)credential.zzRe) || !TextUtils.equals((CharSequence)this.zzRf, (CharSequence)credential.zzRf) || !TextUtils.equals((CharSequence)this.zzRg, (CharSequence)credential.zzRg)) {
                return false;
            }
        }
        return true;
    }
    
    public String getAccountType() {
        return this.zzRf;
    }
    
    public String getGeneratedPassword() {
        return this.zzRg;
    }
    
    public String getId() {
        return this.zzwj;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getPassword() {
        return this.zzRe;
    }
    
    public Uri getProfilePictureUri() {
        return this.zzRc;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzwj, this.mName, this.zzRc, this.zzRe, this.zzRf, this.zzRg);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzlr() {
        return this.zzRa;
    }
    
    public String zzls() {
        return this.zzRb;
    }
    
    public List<IdToken> zzlt() {
        return this.zzRd;
    }
    
    public String zzlu() {
        return this.zzRh;
    }
}
