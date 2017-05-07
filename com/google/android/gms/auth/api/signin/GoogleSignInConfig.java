// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.auth.api.signin.internal.zzc;
import java.util.List;
import java.util.Collections;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Set;
import java.util.ArrayList;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public class GoogleSignInConfig implements Api$ApiOptions$Optional, SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInConfig> CREATOR;
    public static final Scope zzTe;
    public static final Scope zzTf;
    public static final Scope zzTg;
    public static final GoogleSignInConfig zzTh;
    final int versionCode;
    private Account zzQd;
    private final ArrayList<Scope> zzSX;
    private boolean zzTi;
    private final boolean zzTj;
    private final boolean zzTk;
    private String zzTl;
    
    static {
        zzTe = new Scope("profile");
        zzTf = new Scope("email");
        zzTg = new Scope("openid");
        zzTh = new GoogleSignInConfig$zza().zzmc();
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    GoogleSignInConfig(final int versionCode, final ArrayList<Scope> zzSX, final Account zzQd, final boolean zzTi, final boolean zzTj, final boolean zzTk, final String zzTl) {
        this.versionCode = versionCode;
        this.zzSX = zzSX;
        this.zzQd = zzQd;
        this.zzTi = zzTi;
        this.zzTj = zzTj;
        this.zzTk = zzTk;
        this.zzTl = zzTl;
    }
    
    private GoogleSignInConfig(final Set<Scope> set, final Account account, final boolean b, final boolean b2, final boolean b3, final String s) {
        this(1, new ArrayList<Scope>(set), account, b, b2, b3, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final GoogleSignInConfig googleSignInConfig = (GoogleSignInConfig)o;
                if (this.zzSX.size() == googleSignInConfig.zzlS().size() && this.zzSX.containsAll(googleSignInConfig.zzlS())) {
                    if (this.zzQd == null) {
                        if (googleSignInConfig.getAccount() != null) {
                            return false;
                        }
                    }
                    else if (!this.zzQd.equals((Object)googleSignInConfig.getAccount())) {
                        return false;
                    }
                    if (TextUtils.isEmpty((CharSequence)this.zzTl)) {
                        if (!TextUtils.isEmpty((CharSequence)googleSignInConfig.zzmb())) {
                            return false;
                        }
                    }
                    else if (!this.zzTl.equals(googleSignInConfig.zzmb())) {
                        return false;
                    }
                    if (this.zzTk == googleSignInConfig.zzma() && this.zzTi == googleSignInConfig.zzlY() && this.zzTj == googleSignInConfig.zzlZ()) {
                        return true;
                    }
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }
    
    public Account getAccount() {
        return this.zzQd;
    }
    
    @Override
    public int hashCode() {
        final ArrayList<String> list = (ArrayList<String>)new ArrayList<Comparable>();
        final Iterator<Scope> iterator = this.zzSX.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().zznG());
        }
        Collections.sort((List<Comparable>)list);
        return new zzc().zzl(list).zzl(this.zzQd).zzl(this.zzTl).zzP(this.zzTk).zzP(this.zzTi).zzP(this.zzTj).zzmd();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    public ArrayList<Scope> zzlS() {
        return new ArrayList<Scope>(this.zzSX);
    }
    
    public boolean zzlY() {
        return this.zzTi;
    }
    
    public boolean zzlZ() {
        return this.zzTj;
    }
    
    public boolean zzma() {
        return this.zzTk;
    }
    
    public String zzmb() {
        return this.zzTl;
    }
}
