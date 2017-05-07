// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.analytics.GoogleAnalytics;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;

public class zzc
{
    private final zzf zzLy;
    
    protected zzc(final zzf zzLy) {
        zzx.zzv(zzLy);
        this.zzLy = zzLy;
    }
    
    private void zza(final int n, final String s, final Object o, final Object o2, final Object o3) {
        zzaf zzir = null;
        if (this.zzLy != null) {
            zzir = this.zzLy.zzir();
        }
        if (zzir != null) {
            zzir.zza(n, s, o, o2, o3);
        }
        else {
            final String s2 = zzy.zzNa.get();
            if (Log.isLoggable(s2, n)) {
                Log.println(n, s2, zzc(s, o, o2, o3));
            }
        }
    }
    
    protected static String zzc(String s, final Object o, final Object o2, final Object o3) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        final String zzi = zzi(o);
        final String zzi2 = zzi(o2);
        final String zzi3 = zzi(o3);
        final StringBuilder sb = new StringBuilder();
        s = "";
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            sb.append(s2);
            s = ": ";
        }
        String s3 = s;
        if (!TextUtils.isEmpty((CharSequence)zzi)) {
            sb.append(s);
            sb.append(zzi);
            s3 = ", ";
        }
        s = s3;
        if (!TextUtils.isEmpty((CharSequence)zzi2)) {
            sb.append(s3);
            sb.append(zzi2);
            s = ", ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzi3)) {
            sb.append(s);
            sb.append(zzi3);
        }
        return sb.toString();
    }
    
    private static String zzi(final Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Boolean) {
            String s;
            if (o == Boolean.TRUE) {
                s = "true";
            }
            else {
                s = "false";
            }
            return s;
        }
        if (o instanceof Throwable) {
            return ((Throwable)o).toString();
        }
        return o.toString();
    }
    
    protected Context getContext() {
        return this.zzLy.getContext();
    }
    
    public void zza(final String s, final Object o) {
        this.zza(2, s, o, null, null);
    }
    
    public void zza(final String s, final Object o, final Object o2) {
        this.zza(2, s, o, o2, null);
    }
    
    public void zza(final String s, final Object o, final Object o2, final Object o3) {
        this.zza(3, s, o, o2, o3);
    }
    
    public void zzaY(final String s) {
        this.zza(2, s, null, null, null);
    }
    
    public void zzaZ(final String s) {
        this.zza(3, s, null, null, null);
    }
    
    public void zzb(final String s, final Object o) {
        this.zza(3, s, o, null, null);
    }
    
    public void zzb(final String s, final Object o, final Object o2) {
        this.zza(3, s, o, o2, null);
    }
    
    public void zzb(final String s, final Object o, final Object o2, final Object o3) {
        this.zza(5, s, o, o2, o3);
    }
    
    public void zzba(final String s) {
        this.zza(4, s, null, null, null);
    }
    
    public void zzbb(final String s) {
        this.zza(5, s, null, null, null);
    }
    
    public void zzbc(final String s) {
        this.zza(6, s, null, null, null);
    }
    
    public void zzc(final String s, final Object o) {
        this.zza(4, s, o, null, null);
    }
    
    public void zzc(final String s, final Object o, final Object o2) {
        this.zza(5, s, o, o2, null);
    }
    
    public void zzd(final String s, final Object o) {
        this.zza(5, s, o, null, null);
    }
    
    public void zzd(final String s, final Object o, final Object o2) {
        this.zza(6, s, o, o2, null);
    }
    
    public void zze(final String s, final Object o) {
        this.zza(6, s, o, null, null);
    }
    
    protected zzan zzhA() {
        return this.zzLy.zzhA();
    }
    
    public GoogleAnalytics zzhu() {
        return this.zzLy.zzis();
    }
    
    protected zzb zzhz() {
        return this.zzLy.zzhz();
    }
    
    public zzf zzia() {
        return this.zzLy;
    }
    
    protected void zzib() {
        if (this.zzif().zzjk()) {
            throw new IllegalStateException("Call only supported on the client side");
        }
    }
    
    protected void zzic() {
        this.zzLy.zzic();
    }
    
    protected zzlm zzid() {
        return this.zzLy.zzid();
    }
    
    protected zzaf zzie() {
        return this.zzLy.zzie();
    }
    
    protected zzr zzif() {
        return this.zzLy.zzif();
    }
    
    protected zzof zzig() {
        return this.zzLy.zzig();
    }
    
    protected zzv zzih() {
        return this.zzLy.zzih();
    }
    
    protected zzai zzii() {
        return this.zzLy.zzii();
    }
    
    protected zzn zzij() {
        return this.zzLy.zziv();
    }
    
    protected zza zzik() {
        return this.zzLy.zziu();
    }
    
    protected zzk zzil() {
        return this.zzLy.zzil();
    }
    
    protected zzu zzim() {
        return this.zzLy.zzim();
    }
    
    public boolean zzin() {
        return Log.isLoggable((String)zzy.zzNa.get(), 2);
    }
}
