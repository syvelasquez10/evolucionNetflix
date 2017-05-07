// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class dg extends Number implements Comparable<dg>
{
    private double aaC;
    private long aaD;
    private boolean aaE;
    
    private dg(final double aaC) {
        this.aaC = aaC;
        this.aaE = false;
    }
    
    private dg(final long aaD) {
        this.aaD = aaD;
        this.aaE = true;
    }
    
    public static dg a(final Double n) {
        return new dg(n);
    }
    
    public static dg bW(final String s) throws NumberFormatException {
        try {
            return new dg(Long.parseLong(s));
        }
        catch (NumberFormatException ex) {
            try {
                return new dg(Double.parseDouble(s));
            }
            catch (NumberFormatException ex2) {
                throw new NumberFormatException(s + " is not a valid TypedNumber");
            }
        }
    }
    
    public static dg w(final long n) {
        return new dg(n);
    }
    
    public int a(final dg dg) {
        if (this.lJ() && dg.lJ()) {
            return new Long(this.aaD).compareTo(Long.valueOf(dg.aaD));
        }
        return Double.compare(this.doubleValue(), dg.doubleValue());
    }
    
    @Override
    public byte byteValue() {
        return (byte)this.longValue();
    }
    
    @Override
    public double doubleValue() {
        if (this.lJ()) {
            return this.aaD;
        }
        return this.aaC;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof dg && this.a((dg)o) == 0;
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
        return this.lL();
    }
    
    public boolean lI() {
        return !this.lJ();
    }
    
    public boolean lJ() {
        return this.aaE;
    }
    
    public long lK() {
        if (this.lJ()) {
            return this.aaD;
        }
        return (long)this.aaC;
    }
    
    public int lL() {
        return (int)this.longValue();
    }
    
    public short lM() {
        return (short)this.longValue();
    }
    
    @Override
    public long longValue() {
        return this.lK();
    }
    
    @Override
    public short shortValue() {
        return this.lM();
    }
    
    @Override
    public String toString() {
        if (this.lJ()) {
            return Long.toString(this.aaD);
        }
        return Double.toString(this.aaC);
    }
}
