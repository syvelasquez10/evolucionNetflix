// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import java.util.ArrayList;

public class ni
{
    private int akA;
    private final ArrayList<a> akz;
    
    public ni() {
        this(100);
    }
    
    public ni(final int akA) {
        this.akz = new ArrayList<a>();
        this.akA = akA;
    }
    
    private void mV() {
        while (this.getSize() > this.getCapacity()) {
            this.akz.remove(0);
        }
    }
    
    public void a(final nl nl, final nh nh) {
        this.akz.add(new a(nl, nh));
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
    
    public ArrayList<a> mU() {
        return this.akz;
    }
    
    public static class a
    {
        public final nl akB;
        public final nh akC;
        public final pq.c akD;
        
        private a(final nl nl, final nh nh) {
            this.akB = n.i(nl);
            this.akC = n.i(nh);
            this.akD = null;
        }
    }
}
