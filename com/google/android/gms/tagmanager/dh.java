// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class dh extends Number implements Comparable<dh>
{
    private double arG;
    private long arH;
    private boolean arI;
    
    private dh(final double arG) {
        this.arG = arG;
        this.arI = false;
    }
    
    private dh(final long arH) {
        this.arH = arH;
        this.arI = true;
    }
    
    public static dh a(final Double n) {
        return new dh(n);
    }
    
    public static dh cT(final String s) throws NumberFormatException {
        try {
            return new dh(Long.parseLong(s));
        }
        catch (NumberFormatException ex) {
            try {
                return new dh(Double.parseDouble(s));
            }
            catch (NumberFormatException ex2) {
                throw new NumberFormatException(s + " is not a valid TypedNumber");
            }
        }
    }
    
    public static dh z(final long n) {
        return new dh(n);
    }
    
    public int a(final dh dh) {
        if (this.py() && dh.py()) {
            return new Long(this.arH).compareTo(Long.valueOf(dh.arH));
        }
        return Double.compare(this.doubleValue(), dh.doubleValue());
    }
    
    @Override
    public byte byteValue() {
        return (byte)this.longValue();
    }
    
    @Override
    public double doubleValue() {
        if (this.py()) {
            return this.arH;
        }
        return this.arG;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof dh && this.a((dh)o) == 0;
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
        return this.pA();
    }
    
    @Override
    public long longValue() {
        return this.pz();
    }
    
    public int pA() {
        return (int)this.longValue();
    }
    
    public short pB() {
        return (short)this.longValue();
    }
    
    public boolean px() {
        return !this.py();
    }
    
    public boolean py() {
        return this.arI;
    }
    
    public long pz() {
        if (this.py()) {
            return this.arH;
        }
        return (long)this.arG;
    }
    
    @Override
    public short shortValue() {
        return this.pB();
    }
    
    @Override
    public String toString() {
        if (this.py()) {
            return Long.toString(this.arH);
        }
        return Double.toString(this.arG);
    }
}
