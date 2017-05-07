// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.ix;
import java.io.IOException;
import com.google.android.gms.internal.jb;
import com.google.android.gms.internal.iw;
import com.google.android.gms.internal.iy;
import com.google.android.gms.internal.iz;

public final class q extends iz
{
    public static final q[] rs;
    public String rt;
    public long ru;
    public long rv;
    private int rw;
    public int versionCode;
    
    static {
        rs = new q[0];
    }
    
    public q() {
        this.versionCode = 1;
        this.rt = "";
        this.ru = -1L;
        this.rv = -1L;
        this.rw = -1;
    }
    
    public static q e(final byte[] array) throws iy {
        return iz.a(new q(), array);
    }
    
    public q a(final iw iw) throws IOException {
    Label_0064:
        while (true) {
            final int fu = iw.fU();
            switch (fu) {
                default: {
                    if (!jb.a(iw, fu)) {
                        break Label_0064;
                    }
                    continue;
                }
                case 0: {
                    break Label_0064;
                }
                case 8: {
                    this.versionCode = iw.fW();
                    continue;
                }
                case 18: {
                    this.rt = iw.readString();
                    continue;
                }
                case 24: {
                    this.ru = iw.fX();
                    continue;
                }
                case 32: {
                    this.rv = iw.fX();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    public void a(final ix ix) throws IOException {
        ix.d(1, this.versionCode);
        ix.b(2, this.rt);
        ix.c(3, this.ru);
        ix.c(4, this.rv);
    }
    
    @Override
    public int cP() {
        return this.rw = 0 + ix.e(1, this.versionCode) + ix.e(2, this.rt) + ix.d(3, this.ru) + ix.d(4, this.rv);
    }
}
