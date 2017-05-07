// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public abstract class kp<M extends kp<M>> extends kt
{
    protected List<kv> adU;
    
    public final <T> T a(final kq<M, T> kq) {
        return kq.f(this.adU);
    }
    
    @Override
    public void a(final ko ko) throws IOException {
        int size;
        if (this.adU == null) {
            size = 0;
        }
        else {
            size = this.adU.size();
        }
        for (int i = 0; i < size; ++i) {
            final kv kv = this.adU.get(i);
            ko.da(kv.tag);
            ko.p(kv.adZ);
        }
    }
    
    protected final boolean a(final kn kn, final int n) throws IOException {
        final int position = kn.getPosition();
        if (!kn.cQ(n)) {
            return false;
        }
        if (this.adU == null) {
            this.adU = new ArrayList<kv>();
        }
        this.adU.add(new kv(n, kn.h(position, kn.getPosition() - position)));
        return true;
    }
    
    @Override
    protected int mx() {
        int size;
        if (this.adU == null) {
            size = 0;
        }
        else {
            size = this.adU.size();
        }
        int i = 0;
        int n = 0;
        while (i < size) {
            final kv kv = this.adU.get(i);
            n = n + ko.db(kv.tag) + kv.adZ.length;
            ++i;
        }
        return n;
    }
}
