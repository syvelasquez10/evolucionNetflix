// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;

public class ni
{
    private int akA;
    private final ArrayList<ni$a> akz;
    
    public ni() {
        this(100);
    }
    
    public ni(final int akA) {
        this.akz = new ArrayList<ni$a>();
        this.akA = akA;
    }
    
    private void mV() {
        while (this.getSize() > this.getCapacity()) {
            this.akz.remove(0);
        }
    }
    
    public void a(final nl nl, final nh nh) {
        this.akz.add(new ni$a(nl, nh, null));
        this.mV();
    }
    
    public void clear() {
        this.akz.clear();
    }
    
    public int getCapacity() {
        return this.akA;
    }
    
    public int getSize() {
        return this.akz.size();
    }
    
    public boolean isEmpty() {
        return this.akz.isEmpty();
    }
    
    public ArrayList<ni$a> mU() {
        return this.akz;
    }
}
