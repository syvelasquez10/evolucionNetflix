// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.pe;
import java.io.IOException;
import com.google.android.gms.internal.pf;
import com.google.android.gms.internal.pl;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pg;

public final class ah extends pg<ah>
{
    public String Pd;
    public long Pe;
    public long Pf;
    public int versionCode;
    
    public ah() {
        this.ic();
    }
    
    public static ah g(final byte[] array) throws pl {
        return pm.a(new ah(), array);
    }
    
    @Override
    public void a(final pf pf) throws IOException {
        pf.s(1, this.versionCode);
        pf.b(2, this.Pd);
        pf.c(3, this.Pe);
        pf.c(4, this.Pf);
        super.a(pf);
    }
    
    @Override
    protected int c() {
        return super.c() + pf.u(1, this.versionCode) + pf.j(2, this.Pd) + pf.e(3, this.Pe) + pf.e(4, this.Pf);
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
            if (o instanceof ah) {
                final ah ah = (ah)o;
                b2 = b;
                if (this.versionCode == ah.versionCode) {
                    if (this.Pd == null) {
                        b2 = b;
                        if (ah.Pd != null) {
                            return b2;
                        }
                    }
                    else if (!this.Pd.equals(ah.Pd)) {
                        return false;
                    }
                    b2 = b;
                    if (this.Pe == ah.Pe) {
                        b2 = b;
                        if (this.Pf == ah.Pf) {
                            return this.a(ah);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        final int versionCode = this.versionCode;
        int hashCode;
        if (this.Pd == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.Pd.hashCode();
        }
        return (((hashCode + (versionCode + 527) * 31) * 31 + (int)(this.Pe ^ this.Pe >>> 32)) * 31 + (int)(this.Pf ^ this.Pf >>> 32)) * 31 + this.qx();
    }
    
    public ah ic() {
        this.versionCode = 1;
        this.Pd = "";
        this.Pe = -1L;
        this.Pf = -1L;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public ah m(final pe pe) throws IOException {
    Label_0065:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0065;
                    }
                    continue;
                }
                case 0: {
                    break Label_0065;
                }
                case 8: {
                    this.versionCode = pe.qj();
                    continue;
                }
                case 18: {
                    this.Pd = pe.readString();
                    continue;
                }
                case 24: {
                    this.Pe = pe.qm();
                    continue;
                }
                case 32: {
                    this.Pf = pe.qm();
                    continue;
                }
            }
        }
        return this;
    }
}
