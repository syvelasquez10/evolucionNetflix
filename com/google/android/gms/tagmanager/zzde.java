// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class zzde extends Number implements Comparable<zzde>
{
    private double zzaSK;
    private long zzaSL;
    private boolean zzaSM;
    
    private zzde(final double zzaSK) {
        this.zzaSK = zzaSK;
        this.zzaSM = false;
    }
    
    private zzde(final long zzaSL) {
        this.zzaSL = zzaSL;
        this.zzaSM = true;
    }
    
    public static zzde zzT(final long n) {
        return new zzde(n);
    }
    
    public static zzde zza(final Double n) {
        return new zzde(n);
    }
    
    public static zzde zzeX(final String s) {
        try {
            return new zzde(Long.parseLong(s));
        }
        catch (NumberFormatException ex) {
            try {
                return new zzde(Double.parseDouble(s));
            }
            catch (NumberFormatException ex2) {
                throw new NumberFormatException(s + " is not a valid TypedNumber");
            }
        }
    }
    
    @Override
    public byte byteValue() {
        return (byte)this.longValue();
    }
    
    @Override
    public double doubleValue() {
        if (this.zzAW()) {
            return this.zzaSL;
        }
        return this.zzaSK;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof zzde && this.zza((zzde)o) == 0;
    }
    
    @Override
    public float floatValue() {
        return (float)this.doubleValue();
    }
    
    @Override
    public int hashCode() {
        return new Long(this.longValue()).hashCode();
    }
    
    @Override
    public int intValue() {
        return this.zzAY();
    }
    
    @Override
    public long longValue() {
        return this.zzAX();
    }
    
    @Override
    public short shortValue() {
        return this.zzAZ();
    }
    
    @Override
    public String toString() {
        if (this.zzAW()) {
            return Long.toString(this.zzaSL);
        }
        return Double.toString(this.zzaSK);
    }
    
    public boolean zzAV() {
        return !this.zzAW();
    }
    
    public boolean zzAW() {
        return this.zzaSM;
    }
    
    public long zzAX() {
        if (this.zzAW()) {
            return this.zzaSL;
        }
        return (long)this.zzaSK;
    }
    
    public int zzAY() {
        return (int)this.longValue();
    }
    
    public short zzAZ() {
        return (short)this.longValue();
    }
    
    public int zza(final zzde zzde) {
        if (this.zzAW() && zzde.zzAW()) {
            return new Long(this.zzaSL).compareTo(Long.valueOf(zzde.zzaSL));
        }
        return Double.compare(this.doubleValue(), zzde.doubleValue());
    }
}
