// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class DataLayer$a
{
    public final String JH;
    public final Object wq;
    
    DataLayer$a(final String jh, final Object wq) {
        this.JH = jh;
        this.wq = wq;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DataLayer$a) {
            final DataLayer$a dataLayer$a = (DataLayer$a)o;
            if (this.JH.equals(dataLayer$a.JH) && this.wq.equals(dataLayer$a.wq)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Integer[] { this.JH.hashCode(), this.wq.hashCode() });
    }
    
    @Override
    public String toString() {
        return "Key: " + this.JH + " value: " + this.wq.toString();
    }
}
