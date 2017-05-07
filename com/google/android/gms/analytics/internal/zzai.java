// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.content.SharedPreferences$Editor;
import android.text.TextUtils;
import android.content.SharedPreferences;

public class zzai extends zzd
{
    private SharedPreferences zzOw;
    private long zzOx;
    private long zzOy;
    private final zzai$zza zzOz;
    
    protected zzai(final zzf zzf) {
        super(zzf);
        this.zzOy = -1L;
        this.zzOz = new zzai$zza(this, "monitoring", this.zzif().zzjQ(), null);
    }
    
    public void zzbk(final String s) {
        this.zzic();
        this.zzio();
        final SharedPreferences$Editor edit = this.zzOw.edit();
        if (TextUtils.isEmpty((CharSequence)s)) {
            edit.remove("installation_campaign");
        }
        else {
            edit.putString("installation_campaign", s);
        }
        if (!edit.commit()) {
            this.zzbb("Failed to commit campaign data");
        }
    }
    
    @Override
    protected void zzhB() {
        this.zzOw = this.getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }
    
    public long zzkA() {
        this.zzic();
        this.zzio();
        if (this.zzOy == -1L) {
            this.zzOy = this.zzOw.getLong("last_dispatch", 0L);
        }
        return this.zzOy;
    }
    
    public void zzkB() {
        this.zzic();
        this.zzio();
        final long currentTimeMillis = this.zzid().currentTimeMillis();
        final SharedPreferences$Editor edit = this.zzOw.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzOy = currentTimeMillis;
    }
    
    public String zzkC() {
        this.zzic();
        this.zzio();
        final String string = this.zzOw.getString("installation_campaign", (String)null);
        if (TextUtils.isEmpty((CharSequence)string)) {
            return null;
        }
        return string;
    }
    
    public zzai$zza zzkD() {
        return this.zzOz;
    }
    
    public long zzky() {
        this.zzic();
        this.zzio();
        if (this.zzOx == 0L) {
            final long long1 = this.zzOw.getLong("first_run", 0L);
            if (long1 != 0L) {
                this.zzOx = long1;
            }
            else {
                final long currentTimeMillis = this.zzid().currentTimeMillis();
                final SharedPreferences$Editor edit = this.zzOw.edit();
                edit.putLong("first_run", currentTimeMillis);
                if (!edit.commit()) {
                    this.zzbb("Failed to commit first run time");
                }
                this.zzOx = currentTimeMillis;
            }
        }
        return this.zzOx;
    }
    
    public zzaj zzkz() {
        return new zzaj(this.zzid(), this.zzky());
    }
}
