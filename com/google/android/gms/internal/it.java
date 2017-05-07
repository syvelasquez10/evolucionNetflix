// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface it
{
    public static final class a extends kp<a>
    {
        public long aaY;
        public c.j aaZ;
        public c.f fK;
        
        public a() {
            this.lV();
        }
        
        public static a l(final byte[] array) throws ks {
            return kt.a(new a(), array);
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            ko.b(1, this.aaY);
            if (this.fK != null) {
                ko.a(2, this.fK);
            }
            if (this.aaZ != null) {
                ko.a(3, this.aaZ);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            int n2;
            final int n = n2 = super.c() + ko.d(1, this.aaY);
            if (this.fK != null) {
                n2 = n + ko.b(2, this.fK);
            }
            int adY = n2;
            if (this.aaZ != null) {
                adY = n2 + ko.b(3, this.aaZ);
            }
            return this.adY = adY;
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
                if (o instanceof a) {
                    final a a = (a)o;
                    b2 = b;
                    if (this.aaY == a.aaY) {
                        if (this.fK == null) {
                            b2 = b;
                            if (a.fK != null) {
                                return b2;
                            }
                        }
                        else if (!this.fK.equals(a.fK)) {
                            return false;
                        }
                        if (this.aaZ == null) {
                            b2 = b;
                            if (a.aaZ != null) {
                                return b2;
                            }
                        }
                        else if (!this.aaZ.equals(a.aaZ)) {
                            return false;
                        }
                        if (this.adU == null || this.adU.isEmpty()) {
                            if (a.adU != null) {
                                b2 = b;
                                if (!a.adU.isEmpty()) {
                                    return b2;
                                }
                            }
                            return true;
                        }
                        return this.adU.equals(a.adU);
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            final boolean b = false;
            final int n = (int)(this.aaY ^ this.aaY >>> 32);
            int hashCode;
            if (this.fK == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.fK.hashCode();
            }
            int hashCode2;
            if (this.aaZ == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.aaZ.hashCode();
            }
            int hashCode3 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode3 = (b ? 1 : 0);
                }
                else {
                    hashCode3 = this.adU.hashCode();
                }
            }
            return (hashCode2 + (hashCode + (n + 527) * 31) * 31) * 31 + hashCode3;
        }
        
        public a lV() {
            this.aaY = 0L;
            this.fK = null;
            this.aaZ = null;
            this.adU = null;
            this.adY = -1;
            return this;
        }
        
        public a n(final kn kn) throws IOException {
        Label_0057:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 8: {
                        this.aaY = kn.mj();
                        continue;
                    }
                    case 18: {
                        if (this.fK == null) {
                            this.fK = new c.f();
                        }
                        kn.a(this.fK);
                        continue;
                    }
                    case 26: {
                        if (this.aaZ == null) {
                            this.aaZ = new c.j();
                        }
                        kn.a(this.aaZ);
                        continue;
                    }
                }
            }
            return this;
        }
    }
}
