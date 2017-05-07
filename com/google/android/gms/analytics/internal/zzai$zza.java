// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import android.content.SharedPreferences;
import android.util.Pair;
import java.util.UUID;
import android.content.SharedPreferences$Editor;
import com.google.android.gms.common.internal.zzx;

public final class zzai$zza
{
    private final String mName;
    private final long zzOA;
    final /* synthetic */ zzai zzOB;
    
    private zzai$zza(final zzai zzOB, final String mName, final long zzOA) {
        this.zzOB = zzOB;
        zzx.zzcs(mName);
        zzx.zzZ(zzOA > 0L);
        this.mName = mName;
        this.zzOA = zzOA;
    }
    
    private void zzkE() {
        final long currentTimeMillis = this.zzOB.zzid().currentTimeMillis();
        final SharedPreferences$Editor edit = this.zzOB.zzOw.edit();
        edit.remove(this.zzkJ());
        edit.remove(this.zzkK());
        edit.putLong(this.zzkI(), currentTimeMillis);
        edit.commit();
    }
    
    private long zzkF() {
        final long zzkH = this.zzkH();
        if (zzkH == 0L) {
            return 0L;
        }
        return Math.abs(zzkH - this.zzOB.zzid().currentTimeMillis());
    }
    
    private long zzkH() {
        return this.zzOB.zzOw.getLong(this.zzkI(), 0L);
    }
    
    private String zzkI() {
        return this.mName + ":start";
    }
    
    private String zzkJ() {
        return this.mName + ":count";
    }
    
    public void zzbl(final String s) {
        if (this.zzkH() == 0L) {
            this.zzkE();
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = "";
        }
        while (true) {
            while (true) {
                synchronized (this) {
                    final long long1 = this.zzOB.zzOw.getLong(this.zzkJ(), 0L);
                    if (long1 <= 0L) {
                        final SharedPreferences$Editor edit = this.zzOB.zzOw.edit();
                        edit.putString(this.zzkK(), s2);
                        edit.putLong(this.zzkJ(), 1L);
                        edit.apply();
                        return;
                    }
                    if ((UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (long1 + 1L)) {
                        final int n = 1;
                        final SharedPreferences$Editor edit2 = this.zzOB.zzOw.edit();
                        if (n != 0) {
                            edit2.putString(this.zzkK(), s2);
                        }
                        edit2.putLong(this.zzkJ(), long1 + 1L);
                        edit2.apply();
                        return;
                    }
                }
                final int n = 0;
                continue;
            }
        }
    }
    
    public Pair<String, Long> zzkG() {
        final long zzkF = this.zzkF();
        if (zzkF >= this.zzOA) {
            if (zzkF > this.zzOA * 2L) {
                this.zzkE();
                return null;
            }
            final String string = this.zzOB.zzOw.getString(this.zzkK(), (String)null);
            final long long1 = this.zzOB.zzOw.getLong(this.zzkJ(), 0L);
            this.zzkE();
            if (string != null && long1 > 0L) {
                return (Pair<String, Long>)new Pair((Object)string, (Object)long1);
            }
        }
        return null;
    }
    
    protected String zzkK() {
        return this.mName + ":value";
    }
}
