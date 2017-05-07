// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;

public class zzrs<M extends zzrr<M>, T>
{
    public final int tag;
    protected final int type;
    protected final Class<T> zzbcb;
    protected final boolean zzbcc;
    
    int zzS(final Object o) {
        if (this.zzbcc) {
            return this.zzT(o);
        }
        return this.zzU(o);
    }
    
    protected int zzT(final Object o) {
        int n = 0;
        int n2;
        for (int length = Array.getLength(o), i = 0; i < length; ++i, n = n2) {
            n2 = n;
            if (Array.get(o, i) != null) {
                n2 = n + this.zzU(Array.get(o, i));
            }
        }
        return n;
    }
    
    protected int zzU(final Object o) {
        final int zzlE = zzsa.zzlE(this.tag);
        switch (this.type) {
            default: {
                throw new IllegalArgumentException("Unknown type " + this.type);
            }
            case 10: {
                return zzrq.zzb(zzlE, (zzrx)o);
            }
            case 11: {
                return zzrq.zzc(zzlE, (zzrx)o);
            }
        }
    }
    
    void zza(final Object o, final zzrq zzrq) {
        if (this.zzbcc) {
            this.zzc(o, zzrq);
            return;
        }
        this.zzb(o, zzrq);
    }
    
    protected void zzb(final Object o, final zzrq zzrq) {
        while (true) {
            Label_0101: {
                Label_0076: {
                    while (true) {
                        Label_0110: {
                            try {
                                zzrq.zzlw(this.tag);
                                switch (this.type) {
                                    case 10: {
                                        break Label_0076;
                                    }
                                    case 11: {
                                        break Label_0101;
                                    }
                                    default: {
                                        break Label_0110;
                                    }
                                }
                                throw new IllegalArgumentException("Unknown type " + this.type);
                            }
                            catch (IOException ex) {
                                throw new IllegalStateException(ex);
                            }
                            break Label_0076;
                        }
                        continue;
                    }
                }
                final zzrx zzrx = (zzrx)o;
                final int zzlE = zzsa.zzlE(this.tag);
                zzrq.zzb(zzrx);
                zzrq.zzD(zzlE, 4);
                return;
            }
            zzrq.zzc((zzrx)o);
        }
    }
    
    protected void zzc(final Object o, final zzrq zzrq) {
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            final Object value = Array.get(o, i);
            if (value != null) {
                this.zzb(value, zzrq);
            }
        }
    }
}
