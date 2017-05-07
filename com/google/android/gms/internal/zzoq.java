// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import android.util.Log;
import java.util.UUID;
import com.google.android.gms.common.internal.zzx;

public final class zzoq extends zzod<zzoq>
{
    private String zzaIF;
    private int zzaIG;
    private int zzaIH;
    private String zzaII;
    private String zzaIJ;
    private boolean zzaIK;
    private boolean zzaIL;
    private boolean zzaIM;
    
    public zzoq() {
        this(false);
    }
    
    public zzoq(final boolean b) {
        this(b, zzxS());
    }
    
    public zzoq(final boolean zzaIL, final int zzaIG) {
        zzx.zzbD(zzaIG);
        this.zzaIG = zzaIG;
        this.zzaIL = zzaIL;
    }
    
    static int zzxS() {
        final UUID randomUUID = UUID.randomUUID();
        int n = (int)(randomUUID.getLeastSignificantBits() & 0x7FFFFFFFL);
        if (n == 0 && (n = (int)(randomUUID.getMostSignificantBits() & 0x7FFFFFFFL)) == 0) {
            Log.e("GAv4", "UUID.randomUUID() returned 0.");
            return Integer.MAX_VALUE;
        }
        return n;
    }
    
    private void zzxZ() {
        if (this.zzaIM) {
            throw new IllegalStateException("ScreenViewInfo is immutable");
        }
    }
    
    public boolean isMutable() {
        return !this.zzaIM;
    }
    
    public void setScreenName(final String zzaIF) {
        this.zzxZ();
        this.zzaIF = zzaIF;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("screenName", this.zzaIF);
        hashMap.put("interstitial", (String)this.zzaIK);
        hashMap.put("automatic", (String)this.zzaIL);
        hashMap.put("screenId", (String)this.zzaIG);
        hashMap.put("referrerScreenId", (String)this.zzaIH);
        hashMap.put("referrerScreenName", this.zzaII);
        hashMap.put("referrerUri", this.zzaIJ);
        return zzod.zzA(hashMap);
    }
    
    public void zzal(final boolean zzaIL) {
        this.zzxZ();
        this.zzaIL = zzaIL;
    }
    
    public void zzam(final boolean zzaIK) {
        this.zzxZ();
        this.zzaIK = zzaIK;
    }
    
    public int zzbp() {
        return this.zzaIG;
    }
    
    public void zzc(final zzoq zzoq) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaIF)) {
            zzoq.setScreenName(this.zzaIF);
        }
        if (this.zzaIG != 0) {
            zzoq.zzhS(this.zzaIG);
        }
        if (this.zzaIH != 0) {
            zzoq.zzhT(this.zzaIH);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaII)) {
            zzoq.zzdU(this.zzaII);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIJ)) {
            zzoq.zzdV(this.zzaIJ);
        }
        if (this.zzaIK) {
            zzoq.zzam(this.zzaIK);
        }
        if (this.zzaIL) {
            zzoq.zzal(this.zzaIL);
        }
    }
    
    public void zzdU(final String zzaII) {
        this.zzxZ();
        this.zzaII = zzaII;
    }
    
    public void zzdV(final String zzaIJ) {
        this.zzxZ();
        if (TextUtils.isEmpty((CharSequence)zzaIJ)) {
            this.zzaIJ = null;
            return;
        }
        this.zzaIJ = zzaIJ;
    }
    
    public void zzhS(final int zzaIG) {
        this.zzxZ();
        this.zzaIG = zzaIG;
    }
    
    public void zzhT(final int zzaIH) {
        this.zzxZ();
        this.zzaIH = zzaIH;
    }
    
    public String zzxT() {
        return this.zzaIF;
    }
    
    public int zzxU() {
        return this.zzaIH;
    }
    
    public String zzxV() {
        return this.zzaII;
    }
    
    public String zzxW() {
        return this.zzaIJ;
    }
    
    public void zzxX() {
        this.zzaIM = true;
    }
    
    public boolean zzxY() {
        return this.zzaIK;
    }
}
