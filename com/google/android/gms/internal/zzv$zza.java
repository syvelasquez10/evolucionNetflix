// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

class zzv$zza
{
    public String key;
    public long zzaB;
    public String zzb;
    public long zzc;
    public long zzd;
    public long zze;
    public long zzf;
    public Map<String, String> zzg;
    
    private zzv$zza() {
    }
    
    public zzv$zza(final String key, final zzb$zza zzb$zza) {
        this.key = key;
        this.zzaB = zzb$zza.data.length;
        this.zzb = zzb$zza.zzb;
        this.zzc = zzb$zza.zzc;
        this.zzd = zzb$zza.zzd;
        this.zze = zzb$zza.zze;
        this.zzf = zzb$zza.zzf;
        this.zzg = zzb$zza.zzg;
    }
    
    public static zzv$zza zzf(final InputStream inputStream) {
        final zzv$zza zzv$zza = new zzv$zza();
        if (zzv.zzb(inputStream) != 538247942) {
            throw new IOException();
        }
        zzv$zza.key = zzv.zzd(inputStream);
        zzv$zza.zzb = zzv.zzd(inputStream);
        if (zzv$zza.zzb.equals("")) {
            zzv$zza.zzb = null;
        }
        zzv$zza.zzc = zzv.zzc(inputStream);
        zzv$zza.zzd = zzv.zzc(inputStream);
        zzv$zza.zze = zzv.zzc(inputStream);
        zzv$zza.zzf = zzv.zzc(inputStream);
        zzv$zza.zzg = zzv.zze(inputStream);
        return zzv$zza;
    }
    
    public boolean zza(final OutputStream outputStream) {
        try {
            zzv.zza(outputStream, 538247942);
            zzv.zza(outputStream, this.key);
            String zzb;
            if (this.zzb == null) {
                zzb = "";
            }
            else {
                zzb = this.zzb;
            }
            zzv.zza(outputStream, zzb);
            zzv.zza(outputStream, this.zzc);
            zzv.zza(outputStream, this.zzd);
            zzv.zza(outputStream, this.zze);
            zzv.zza(outputStream, this.zzf);
            zzv.zza(this.zzg, outputStream);
            outputStream.flush();
            return true;
        }
        catch (IOException ex) {
            zzs.zzb("%s", ex.toString());
            return false;
        }
    }
    
    public zzb$zza zzb(final byte[] data) {
        final zzb$zza zzb$zza = new zzb$zza();
        zzb$zza.data = data;
        zzb$zza.zzb = this.zzb;
        zzb$zza.zzc = this.zzc;
        zzb$zza.zzd = this.zzd;
        zzb$zza.zze = this.zze;
        zzb$zza.zzf = this.zzf;
        zzb$zza.zzg = this.zzg;
        return zzb$zza;
    }
}
