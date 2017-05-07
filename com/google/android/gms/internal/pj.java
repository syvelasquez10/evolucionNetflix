// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class pj
{
    private ph<?, ?> awF;
    private Object awG;
    private List<po> awH;
    
    pj() {
        this.awH = new ArrayList<po>();
    }
    
    private byte[] toByteArray() throws IOException {
        final byte[] array = new byte[this.c()];
        this.a(pf.q(array));
        return array;
    }
    
    void a(final pf pf) throws IOException {
        if (this.awG != null) {
            this.awF.a(this.awG, pf);
        }
        else {
            final Iterator<po> iterator = this.awH.iterator();
            while (iterator.hasNext()) {
                iterator.next().a(pf);
            }
        }
    }
    
    void a(final po po) {
        this.awH.add(po);
    }
    
     <T> T b(final ph<?, T> awF) {
        if (this.awG != null) {
            if (this.awF != awF) {
                throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
            }
        }
        else {
            this.awF = awF;
            this.awG = awF.l(this.awH);
            this.awH = null;
        }
        return (T)this.awG;
    }
    
    int c() {
        int a;
        if (this.awG != null) {
            a = this.awF.A(this.awG);
        }
        else {
            final Iterator<po> iterator = this.awH.iterator();
            int n = 0;
            while (true) {
                a = n;
                if (!iterator.hasNext()) {
                    break;
                }
                n += iterator.next().c();
            }
        }
        return a;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (o == this) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof pj) {
                final pj pj = (pj)o;
                if (this.awG != null && pj.awG != null) {
                    b2 = b;
                    if (this.awF == pj.awF) {
                        if (!this.awF.awz.isArray()) {
                            return this.awG.equals(pj.awG);
                        }
                        if (this.awG instanceof byte[]) {
                            return Arrays.equals((byte[])this.awG, (byte[])pj.awG);
                        }
                        if (this.awG instanceof int[]) {
                            return Arrays.equals((int[])this.awG, (int[])pj.awG);
                        }
                        if (this.awG instanceof long[]) {
                            return Arrays.equals((long[])this.awG, (long[])pj.awG);
                        }
                        if (this.awG instanceof float[]) {
                            return Arrays.equals((float[])this.awG, (float[])pj.awG);
                        }
                        if (this.awG instanceof double[]) {
                            return Arrays.equals((double[])this.awG, (double[])pj.awG);
                        }
                        if (this.awG instanceof boolean[]) {
                            return Arrays.equals((boolean[])this.awG, (boolean[])pj.awG);
                        }
                        return Arrays.deepEquals((Object[])this.awG, (Object[])pj.awG);
                    }
                }
                else {
                    if (this.awH != null && pj.awH != null) {
                        return this.awH.equals(pj.awH);
                    }
                    try {
                        return Arrays.equals(this.toByteArray(), pj.toByteArray());
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        try {
            return Arrays.hashCode(this.toByteArray()) + 527;
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
