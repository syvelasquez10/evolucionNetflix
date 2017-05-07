// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

public abstract class zzf
{
    public static int zzahY;
    public static int zzahZ;
    
    static {
        zzf.zzahY = 0;
        zzf.zzahZ = 1;
    }
    
    public abstract int getEventType();
    
    public abstract long getTimeMillis();
    
    @Override
    public String toString() {
        return this.getTimeMillis() + "\t" + this.getEventType() + "\t" + this.zzqd() + this.zzqg();
    }
    
    public abstract long zzqd();
    
    public abstract String zzqg();
}
