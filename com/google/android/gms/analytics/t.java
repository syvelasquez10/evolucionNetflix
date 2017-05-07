// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeSet;
import java.util.SortedSet;

class t
{
    private static final t ze;
    private SortedSet<t$a> zb;
    private StringBuilder zc;
    private boolean zd;
    
    static {
        ze = new t();
    }
    
    private t() {
        this.zb = new TreeSet<t$a>();
        this.zc = new StringBuilder();
        this.zd = false;
    }
    
    public static t eq() {
        return t.ze;
    }
    
    public void B(final boolean zd) {
        synchronized (this) {
            this.zd = zd;
        }
    }
    
    public void a(final t$a t$a) {
        synchronized (this) {
            if (!this.zd) {
                this.zb.add(t$a);
                this.zc.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(t$a.ordinal()));
            }
        }
    }
    
    public String er() {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder();
            int n = 6;
            int n2 = 0;
            while (this.zb.size() > 0) {
                final t$a t$a = this.zb.first();
                this.zb.remove(t$a);
                while (t$a.ordinal() >= n) {
                    sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(n2));
                    n += 6;
                    n2 = 0;
                }
                n2 += 1 << t$a.ordinal() % 6;
            }
            if (n2 > 0 || sb.length() == 0) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(n2));
            }
            this.zb.clear();
            return sb.toString();
        }
    }
    
    public String es() {
        synchronized (this) {
            if (this.zc.length() > 0) {
                this.zc.insert(0, ".");
            }
            final String string = this.zc.toString();
            this.zc = new StringBuilder();
            return string;
        }
    }
}
