// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Bundle;
import java.util.Arrays;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class h implements SafeParcelable
{
    public static final j CREATOR;
    private final String[] Uk;
    private final String[] Ul;
    private final String[] Um;
    private final String Un;
    private final String Uo;
    private final String Up;
    private final String Uq;
    private final PlusCommonExtras Ur;
    private final String wG;
    private final int xH;
    
    static {
        CREATOR = new j();
    }
    
    h(final int xh, final String wg, final String[] uk, final String[] ul, final String[] um, final String un, final String uo, final String up, final String uq, final PlusCommonExtras ur) {
        this.xH = xh;
        this.wG = wg;
        this.Uk = uk;
        this.Ul = ul;
        this.Um = um;
        this.Un = un;
        this.Uo = uo;
        this.Up = up;
        this.Uq = uq;
        this.Ur = ur;
    }
    
    public h(final String wg, final String[] uk, final String[] ul, final String[] um, final String un, final String uo, final String up, final PlusCommonExtras ur) {
        this.xH = 1;
        this.wG = wg;
        this.Uk = uk;
        this.Ul = ul;
        this.Um = um;
        this.Un = un;
        this.Uo = uo;
        this.Up = up;
        this.Uq = null;
        this.Ur = ur;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof h) {
            final h h = (h)o;
            if (this.xH == h.xH && fo.equal(this.wG, h.wG) && Arrays.equals(this.Uk, h.Uk) && Arrays.equals(this.Ul, h.Ul) && Arrays.equals(this.Um, h.Um) && fo.equal(this.Un, h.Un) && fo.equal(this.Uo, h.Uo) && fo.equal(this.Up, h.Up) && fo.equal(this.Uq, h.Uq) && fo.equal(this.Ur, h.Ur)) {
                return true;
            }
        }
        return false;
    }
    
    public String getAccountName() {
        return this.wG;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.xH, this.wG, this.Uk, this.Ul, this.Um, this.Un, this.Uo, this.Up, this.Uq, this.Ur);
    }
    
    public String[] iP() {
        return this.Uk;
    }
    
    public String[] iQ() {
        return this.Ul;
    }
    
    public String[] iR() {
        return this.Um;
    }
    
    public String iS() {
        return this.Un;
    }
    
    public String iT() {
        return this.Uo;
    }
    
    public String iU() {
        return this.Up;
    }
    
    public String iV() {
        return this.Uq;
    }
    
    public PlusCommonExtras iW() {
        return this.Ur;
    }
    
    public Bundle iX() {
        final Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        this.Ur.m(bundle);
        return bundle;
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("versionCode", this.xH).a("accountName", this.wG).a("requestedScopes", this.Uk).a("visibleActivities", this.Ul).a("requiredFeatures", this.Um).a("packageNameForAuth", this.Un).a("callingPackageName", this.Uo).a("applicationName", this.Up).a("extra", this.Ur.toString()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
