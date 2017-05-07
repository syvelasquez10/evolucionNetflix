// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Collections;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public abstract class zzk<T> implements Comparable<zzk<T>>
{
    private final zzs$zza zzD;
    private final int zzE;
    private final String zzF;
    private final int zzG;
    private final zzm$zza zzH;
    private Integer zzI;
    private zzl zzJ;
    private boolean zzK;
    private boolean zzL;
    private boolean zzM;
    private long zzN;
    private zzo zzO;
    private zzb$zza zzP;
    
    public zzk(final int zzE, final String zzF, final zzm$zza zzH) {
        zzs$zza zzD;
        if (zzs$zza.zzak) {
            zzD = new zzs$zza();
        }
        else {
            zzD = null;
        }
        this.zzD = zzD;
        this.zzK = true;
        this.zzL = false;
        this.zzM = false;
        this.zzN = 0L;
        this.zzP = null;
        this.zzE = zzE;
        this.zzF = zzF;
        this.zzH = zzH;
        this.zza(new zzd());
        this.zzG = zzb(zzF);
    }
    
    private byte[] zza(final Map<String, String> map, final String s) {
        final StringBuilder sb = new StringBuilder();
        try {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), s));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), s));
                sb.append('&');
            }
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Encoding not supported: " + s, ex);
        }
        return sb.toString().getBytes(s);
    }
    
    private static int zzb(String host) {
        if (!TextUtils.isEmpty((CharSequence)host)) {
            final Uri parse = Uri.parse(host);
            if (parse != null) {
                host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }
    
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }
    
    public int getMethod() {
        return this.zzE;
    }
    
    public String getUrl() {
        return this.zzF;
    }
    
    public boolean isCanceled() {
        return this.zzL;
    }
    
    @Override
    public String toString() {
        final String string = "0x" + Integer.toHexString(this.zzg());
        final StringBuilder sb = new StringBuilder();
        String s;
        if (this.zzL) {
            s = "[X] ";
        }
        else {
            s = "[ ] ";
        }
        return sb.append(s).append(this.getUrl()).append(" ").append(string).append(" ").append(this.zzs()).append(" ").append(this.zzI).toString();
    }
    
    public final zzk<?> zza(final int n) {
        this.zzI = n;
        return this;
    }
    
    public zzk<?> zza(final zzb$zza zzP) {
        this.zzP = zzP;
        return this;
    }
    
    public zzk<?> zza(final zzl zzJ) {
        this.zzJ = zzJ;
        return this;
    }
    
    public zzk<?> zza(final zzo zzO) {
        this.zzO = zzO;
        return this;
    }
    
    protected abstract zzm<T> zza(final zzi p0);
    
    protected abstract void zza(final T p0);
    
    protected zzr zzb(final zzr zzr) {
        return zzr;
    }
    
    public int zzc(final zzk<T> zzk) {
        final zzk$zza zzs = this.zzs();
        final zzk$zza zzs2 = zzk.zzs();
        if (zzs == zzs2) {
            return this.zzI - zzk.zzI;
        }
        return zzs2.ordinal() - zzs.ordinal();
    }
    
    public void zzc(final zzr zzr) {
        if (this.zzH != null) {
            this.zzH.zze(zzr);
        }
    }
    
    public void zzc(final String s) {
        if (zzs$zza.zzak) {
            this.zzD.zza(s, Thread.currentThread().getId());
        }
        else if (this.zzN == 0L) {
            this.zzN = SystemClock.elapsedRealtime();
        }
    }
    
    void zzd(final String s) {
        if (this.zzJ != null) {
            this.zzJ.zzf((zzk<Object>)this);
        }
        if (zzs$zza.zzak) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.zzD.zza(s, id);
                this.zzD.zzd(this.toString());
                return;
            }
            new Handler(Looper.getMainLooper()).post((Runnable)new zzk$1(this, s, id));
        }
        else {
            final long n = SystemClock.elapsedRealtime() - this.zzN;
            if (n >= 3000L) {
                zzs.zzb("%d ms: %s", n, this.toString());
            }
        }
    }
    
    public int zzg() {
        return this.zzG;
    }
    
    public String zzh() {
        return this.getUrl();
    }
    
    public zzb$zza zzi() {
        return this.zzP;
    }
    
    @Deprecated
    protected Map<String, String> zzj() {
        return this.zzn();
    }
    
    @Deprecated
    protected String zzk() {
        return this.zzo();
    }
    
    @Deprecated
    public String zzl() {
        return this.zzp();
    }
    
    @Deprecated
    public byte[] zzm() {
        final Map<String, String> zzj = this.zzj();
        if (zzj != null && zzj.size() > 0) {
            return this.zza(zzj, this.zzk());
        }
        return null;
    }
    
    protected Map<String, String> zzn() {
        return null;
    }
    
    protected String zzo() {
        return "UTF-8";
    }
    
    public String zzp() {
        return "application/x-www-form-urlencoded; charset=" + this.zzo();
    }
    
    public byte[] zzq() {
        final Map<String, String> zzn = this.zzn();
        if (zzn != null && zzn.size() > 0) {
            return this.zza(zzn, this.zzo());
        }
        return null;
    }
    
    public final boolean zzr() {
        return this.zzK;
    }
    
    public zzk$zza zzs() {
        return zzk$zza.zzU;
    }
    
    public final int zzt() {
        return this.zzO.zzd();
    }
    
    public zzo zzu() {
        return this.zzO;
    }
    
    public void zzv() {
        this.zzM = true;
    }
    
    public boolean zzw() {
        return this.zzM;
    }
}
