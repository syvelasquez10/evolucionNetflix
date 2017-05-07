// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class zzcb
{
    private static zzcb zzaQY;
    private volatile String zzaOS;
    private volatile zzcb$zza zzaQZ;
    private volatile String zzaRa;
    private volatile String zzaRb;
    
    zzcb() {
        this.clear();
    }
    
    static zzcb zzAv() {
        synchronized (zzcb.class) {
            if (zzcb.zzaQY == null) {
                zzcb.zzaQY = new zzcb();
            }
            return zzcb.zzaQY;
        }
    }
    
    void clear() {
        this.zzaQZ = zzcb$zza.zzaRc;
        this.zzaRa = null;
        this.zzaOS = null;
        this.zzaRb = null;
    }
    
    String getContainerId() {
        return this.zzaOS;
    }
    
    zzcb$zza zzAw() {
        return this.zzaQZ;
    }
    
    String zzAx() {
        return this.zzaRa;
    }
}
