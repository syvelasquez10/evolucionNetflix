// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.kn;
import java.io.IOException;
import com.google.android.gms.internal.ko;
import com.google.android.gms.internal.ks;
import com.google.android.gms.internal.kt;
import com.google.android.gms.internal.kp;

public final class y extends kp<y>
{
    public String FC;
    public long FD;
    public long FE;
    public int versionCode;
    
    public y() {
        this.fH();
    }
    
    public static y g(final byte[] array) throws ks {
        return kt.a(new y(), array);
    }
    
    @Override
    public void a(final ko ko) throws IOException {
        ko.i(1, this.versionCode);
        ko.b(2, this.FC);
        ko.c(3, this.FD);
        ko.c(4, this.FE);
        super.a(ko);
    }
    
    @Override
    public int c() {
        return this.adY = super.c() + ko.j(1, this.versionCode) + ko.g(2, this.FC) + ko.e(3, this.FD) + ko.e(4, this.FE);
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
            if (o instanceof y) {
                final y y = (y)o;
                b2 = b;
                if (this.versionCode == y.versionCode) {
                    if (this.FC == null) {
                        b2 = b;
                        if (y.FC != null) {
                            return b2;
                        }
                    }
                    else if (!this.FC.equals(y.FC)) {
                        return false;
                    }
                    b2 = b;
                    if (this.FD == y.FD) {
                        b2 = b;
                        if (this.FE == y.FE) {
                            if (this.adU == null || this.adU.isEmpty()) {
                                if (y.adU != null) {
                                    b2 = b;
                                    if (!y.adU.isEmpty()) {
                                        return b2;
                                    }
                                }
                                return true;
                            }
                            return this.adU.equals(y.adU);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public y fH() {
        this.versionCode = 1;
        this.FC = "";
        this.FD = -1L;
        this.FE = -1L;
        this.adU = null;
        this.adY = -1;
        return this;
    }
    
    @Override
    public int hashCode() {
        final boolean b = false;
        final int versionCode = this.versionCode;
        int hashCode;
        if (this.FC == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.FC.hashCode();
        }
        final int n = (int)(this.FD ^ this.FD >>> 32);
        final int n2 = (int)(this.FE ^ this.FE >>> 32);
        int hashCode2 = b ? 1 : 0;
        if (this.adU != null) {
            if (this.adU.isEmpty()) {
                hashCode2 = (b ? 1 : 0);
            }
            else {
                hashCode2 = this.adU.hashCode();
            }
        }
        return (((hashCode + (versionCode + 527) * 31) * 31 + n) * 31 + n2) * 31 + hashCode2;
    }
    
    public y m(final kn kn) throws IOException {
    Label_0065:
        while (true) {
            final int mh = kn.mh();
            switch (mh) {
                default: {
                    if (!this.a(kn, mh)) {
                        break Label_0065;
                    }
                    continue;
                }
                case 0: {
                    break Label_0065;
                }
                case 8: {
                    this.versionCode = kn.mk();
                    continue;
                }
                case 18: {
                    this.FC = kn.readString();
                    continue;
                }
                case 24: {
                    this.FD = kn.mm();
                    continue;
                }
                case 32: {
                    this.FE = kn.mm();
                    continue;
                }
            }
        }
        return this;
    }
}
