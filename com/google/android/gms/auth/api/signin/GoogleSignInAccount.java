// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmp;
import android.net.Uri;
import com.google.android.gms.internal.zzmn;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleSignInAccount implements SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInAccount> CREATOR;
    public static zzmn zzSY;
    final int versionCode;
    private String zzSZ;
    private String zzSs;
    private String zzTa;
    private Uri zzTb;
    private String zzTc;
    private long zzTd;
    private String zzwN;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
        GoogleSignInAccount.zzSY = zzmp.zzqt();
    }
    
    GoogleSignInAccount(final int versionCode, final String s, final String zzSs, final String zzSZ, final String zzTa, final Uri zzTb, final String zzTc, final long zzTd) {
        this.versionCode = versionCode;
        this.zzwN = zzx.zzcr(s);
        this.zzSs = zzSs;
        this.zzSZ = zzSZ;
        this.zzTa = zzTa;
        this.zzTb = zzTb;
        this.zzTc = zzTc;
        this.zzTd = zzTd;
    }
    
    private JSONObject zzlX() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", (Object)this.getId());
            if (this.getIdToken() != null) {
                jsonObject.put("tokenId", (Object)this.getIdToken());
            }
            if (this.getEmail() != null) {
                jsonObject.put("email", (Object)this.getEmail());
            }
            if (this.getDisplayName() != null) {
                jsonObject.put("displayName", (Object)this.getDisplayName());
            }
            if (this.zzlT() != null) {
                jsonObject.put("photoUrl", (Object)this.zzlT().toString());
            }
            if (this.zzlU() != null) {
                jsonObject.put("serverAuthCode", (Object)this.zzlU());
            }
            jsonObject.put("expirationTime", this.zzTd);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof GoogleSignInAccount && ((GoogleSignInAccount)o).zzlW().equals(this.zzlW());
    }
    
    public String getDisplayName() {
        return this.zzTa;
    }
    
    public String getEmail() {
        return this.zzSZ;
    }
    
    public String getId() {
        return this.zzwN;
    }
    
    public String getIdToken() {
        return this.zzSs;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
    
    public Uri zzlT() {
        return this.zzTb;
    }
    
    public String zzlU() {
        return this.zzTc;
    }
    
    public long zzlV() {
        return this.zzTd;
    }
    
    public String zzlW() {
        return this.zzlX().toString();
    }
}
