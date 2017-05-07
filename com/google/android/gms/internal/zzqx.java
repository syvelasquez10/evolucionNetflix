// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.common.internal.zzx;

public abstract class zzqx
{
    private zzqn zzaUo;
    private zzql zzaUp;
    private zzlm zzpO;
    
    public zzqx(final zzqn zzqn, final zzql zzql) {
        this(zzqn, zzql, zzlo.zzpN());
    }
    
    public zzqx(final zzqn zzaUo, final zzql zzaUp, final zzlm zzpO) {
        boolean b = true;
        if (zzaUo.zzBv().size() != 1) {
            b = false;
        }
        zzx.zzZ(b);
        this.zzaUo = zzaUo;
        this.zzaUp = zzaUp;
        this.zzpO = zzpO;
    }
    
    protected abstract zzqx$zzb zza(final zzqi p0);
    
    protected abstract void zza(final zzqo p0);
    
    public void zza(final zzqx$zza zzqx$zza) {
        zzbg.e("ResourceManager: Failed to download a resource: " + zzqx$zza.name());
        final zzqi zzqi = this.zzaUo.zzBv().get(0);
        final zzqx$zzb zza = this.zza(zzqi);
        zzqo$zza zzqo$zza;
        if (zza != null && zza.zzBX() instanceof zzqp$zzc) {
            zzqo$zza = new zzqo$zza(Status.zzaaD, zzqi, null, (zzqp$zzc)zza.zzBX(), zza.zzBx(), zza.zzBB());
        }
        else {
            zzqo$zza = new zzqo$zza(Status.zzaaF, zzqi, zzqo$zza$zza.zzaTO);
        }
        this.zza(new zzqo(zzqo$zza));
    }
    
    public void zzu(byte[] array) {
        while (true) {
            zzbg.v("ResourceManager: Resource downloaded from Network: " + this.zzaUo.getId());
            final zzqi zzqi = this.zzaUo.zzBv().get(0);
            final zzqo$zza$zza zzaTO = zzqo$zza$zza.zzaTO;
            Object zzBX = null;
            long n = 0L;
            zzqo$zza$zza zzBx = zzaTO;
        Label_0257_Outer:
            while (true) {
            Label_0326:
                while (true) {
                    try {
                        final Object zzt = this.zzaUp.zzt(array);
                        n = n;
                        zzBX = zzt;
                        zzBx = zzaTO;
                        final long n2 = n = this.zzpO.currentTimeMillis();
                        zzBX = zzt;
                        zzBx = zzaTO;
                        if (zzt == null) {
                            n = n2;
                            zzBX = zzt;
                            zzBx = zzaTO;
                            zzbg.zzaD("Parsed resource from network is null");
                            n = n2;
                            zzBX = zzt;
                            zzBx = zzaTO;
                            final zzqx$zzb zza = this.zza(zzqi);
                            n = n2;
                            zzBX = zzt;
                            zzBx = zzaTO;
                            if (zza != null) {
                                n = n2;
                                zzBX = zzt;
                                zzBx = zzaTO;
                                final Object zzBX2 = zza.zzBX();
                                n = n2;
                                zzBX = zzBX2;
                                zzBx = zzaTO;
                                final zzqo$zza$zza zzBx2 = zza.zzBx();
                                n = n2;
                                zzBX = zzBX2;
                                zzBx = zzBx2;
                                n = zza.zzBB();
                                zzBx = zzBx2;
                                zzBX = zzBX2;
                            }
                        }
                        if (zzBX != null) {
                            array = (byte[])(Object)new zzqo$zza(Status.zzaaD, zzqi, array, (zzqp$zzc)zzBX, zzBx, n);
                            this.zza(new zzqo((zzqo$zza)(Object)array));
                            return;
                        }
                    }
                    catch (zzqp$zzg zzqp$zzg) {
                        zzbg.zzaD("Resource from network is corrupted");
                        final zzqx$zzb zza2 = this.zza(zzqi);
                        if (zza2 != null) {
                            zzBX = zza2.zzBX();
                            zzBx = zza2.zzBx();
                            continue Label_0257_Outer;
                        }
                        break Label_0326;
                    }
                    array = (byte[])(Object)new zzqo$zza(Status.zzaaF, zzqi, zzqo$zza$zza.zzaTO);
                    continue;
                }
                continue Label_0257_Outer;
            }
        }
    }
}
