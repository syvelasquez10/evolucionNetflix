// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Iterator;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class zzru implements Cloneable
{
    private zzrs<?, ?> zzbch;
    private Object zzbci;
    private List<zzrz> zzbcj;
    
    zzru() {
        this.zzbcj = new ArrayList<zzrz>();
    }
    
    private byte[] toByteArray() {
        final byte[] array = new byte[this.zzB()];
        this.zza(zzrq.zzA(array));
        return array;
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
            if (o instanceof zzru) {
                final zzru zzru = (zzru)o;
                if (this.zzbci != null && zzru.zzbci != null) {
                    b2 = b;
                    if (this.zzbch == zzru.zzbch) {
                        if (!this.zzbch.zzbcb.isArray()) {
                            return this.zzbci.equals(zzru.zzbci);
                        }
                        if (this.zzbci instanceof byte[]) {
                            return Arrays.equals((byte[])this.zzbci, (byte[])zzru.zzbci);
                        }
                        if (this.zzbci instanceof int[]) {
                            return Arrays.equals((int[])this.zzbci, (int[])zzru.zzbci);
                        }
                        if (this.zzbci instanceof long[]) {
                            return Arrays.equals((long[])this.zzbci, (long[])zzru.zzbci);
                        }
                        if (this.zzbci instanceof float[]) {
                            return Arrays.equals((float[])this.zzbci, (float[])zzru.zzbci);
                        }
                        if (this.zzbci instanceof double[]) {
                            return Arrays.equals((double[])this.zzbci, (double[])zzru.zzbci);
                        }
                        if (this.zzbci instanceof boolean[]) {
                            return Arrays.equals((boolean[])this.zzbci, (boolean[])zzru.zzbci);
                        }
                        return Arrays.deepEquals((Object[])this.zzbci, (Object[])zzru.zzbci);
                    }
                }
                else {
                    if (this.zzbcj != null && zzru.zzbcj != null) {
                        return this.zzbcj.equals(zzru.zzbcj);
                    }
                    try {
                        return Arrays.equals(this.toByteArray(), zzru.toByteArray());
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
    
    int zzB() {
        int zzS;
        if (this.zzbci != null) {
            zzS = this.zzbch.zzS(this.zzbci);
        }
        else {
            final Iterator<zzrz> iterator = this.zzbcj.iterator();
            int n = 0;
            while (true) {
                zzS = n;
                if (!iterator.hasNext()) {
                    break;
                }
                n += iterator.next().zzB();
            }
        }
        return zzS;
    }
    
    public final zzru zzDo() {
        int i = 0;
        final zzru zzru = new zzru();
        try {
            zzru.zzbch = this.zzbch;
            if (this.zzbcj == null) {
                zzru.zzbcj = null;
            }
            else {
                zzru.zzbcj.addAll(this.zzbcj);
            }
            if (this.zzbci == null) {
                return zzru;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
        if (this.zzbci instanceof zzrx) {
            zzru.zzbci = ((zzrx)this.zzbci).zzDm();
            return zzru;
        }
        if (this.zzbci instanceof byte[]) {
            zzru.zzbci = ((byte[])this.zzbci).clone();
            return zzru;
        }
        if (this.zzbci instanceof byte[][]) {
            final byte[][] array = (byte[][])this.zzbci;
            final byte[][] zzbci = new byte[array.length][];
            zzru.zzbci = zzbci;
            for (int j = 0; j < array.length; ++j) {
                zzbci[j] = array[j].clone();
            }
        }
        else {
            if (this.zzbci instanceof boolean[]) {
                zzru.zzbci = ((boolean[])this.zzbci).clone();
                return zzru;
            }
            if (this.zzbci instanceof int[]) {
                zzru.zzbci = ((int[])this.zzbci).clone();
                return zzru;
            }
            if (this.zzbci instanceof long[]) {
                zzru.zzbci = ((long[])this.zzbci).clone();
                return zzru;
            }
            if (this.zzbci instanceof float[]) {
                zzru.zzbci = ((float[])this.zzbci).clone();
                return zzru;
            }
            if (this.zzbci instanceof double[]) {
                zzru.zzbci = ((double[])this.zzbci).clone();
                return zzru;
            }
            if (this.zzbci instanceof zzrx[]) {
                final zzrx[] array2 = (zzrx[])this.zzbci;
                final zzrx[] zzbci2 = new zzrx[array2.length];
                zzru.zzbci = zzbci2;
                while (i < array2.length) {
                    zzbci2[i] = array2[i].zzDm();
                    ++i;
                }
            }
        }
        return zzru;
    }
    
    void zza(final zzrq zzrq) {
        if (this.zzbci != null) {
            this.zzbch.zza(this.zzbci, zzrq);
        }
        else {
            final Iterator<zzrz> iterator = this.zzbcj.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(zzrq);
            }
        }
    }
}
