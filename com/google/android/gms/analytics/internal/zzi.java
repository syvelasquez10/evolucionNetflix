// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.List;
import android.os.RemoteException;
import java.util.Map;
import java.util.Collections;
import com.google.android.gms.common.internal.zzx;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.zzb;
import android.content.ComponentName;

public class zzi extends zzd
{
    private final zzi$zza zzLW;
    private zzac zzLX;
    private final zzt zzLY;
    private zzaj zzLZ;
    
    protected zzi(final zzf zzf) {
        super(zzf);
        this.zzLZ = new zzaj(zzf.zzid());
        this.zzLW = new zzi$zza(this);
        this.zzLY = new zzi$1(this, zzf);
    }
    
    private void onDisconnect() {
        this.zzhz().zzhX();
    }
    
    private void onServiceDisconnected(final ComponentName componentName) {
        this.zzic();
        if (this.zzLX != null) {
            this.zzLX = null;
            this.zza("Disconnected from device AnalyticsService", componentName);
            this.onDisconnect();
        }
    }
    
    private void zza(final zzac zzLX) {
        this.zzic();
        this.zzLX = zzLX;
        this.zziB();
        this.zzhz().onServiceConnected();
    }
    
    private void zziB() {
        this.zzLZ.start();
        this.zzLY.zzt(this.zzif().zzjF());
    }
    
    private void zziC() {
        this.zzic();
        if (!this.isConnected()) {
            return;
        }
        this.zzaY("Inactivity, disconnecting from device AnalyticsService");
        this.disconnect();
    }
    
    public boolean connect() {
        this.zzic();
        this.zzio();
        if (this.zzLX != null) {
            return true;
        }
        final zzac zziD = this.zzLW.zziD();
        if (zziD != null) {
            this.zzLX = zziD;
            this.zziB();
            return true;
        }
        return false;
    }
    
    public void disconnect() {
        this.zzic();
        this.zzio();
        while (true) {
            try {
                zzb.zzpD().zza(this.getContext(), (ServiceConnection)this.zzLW);
                if (this.zzLX != null) {
                    this.zzLX = null;
                    this.onDisconnect();
                }
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            catch (IllegalStateException ex2) {
                continue;
            }
            break;
        }
    }
    
    public boolean isConnected() {
        this.zzic();
        this.zzio();
        return this.zzLX != null;
    }
    
    public boolean zzb(final zzab zzab) {
        zzx.zzv(zzab);
        this.zzic();
        this.zzio();
        final zzac zzLX = this.zzLX;
        if (zzLX == null) {
            return false;
        }
        Label_0067: {
            if (!zzab.zzkm()) {
                break Label_0067;
            }
            String s = this.zzif().zzjy();
            while (true) {
                final List<Command> emptyList = Collections.emptyList();
                try {
                    zzLX.zza(zzab.zzn(), zzab.zzkk(), s, emptyList);
                    this.zziB();
                    return true;
                    s = this.zzif().zzjz();
                }
                catch (RemoteException ex) {
                    this.zzaY("Failed to send hits to AnalyticsService");
                    return false;
                }
            }
        }
    }
    
    @Override
    protected void zzhB() {
    }
}
