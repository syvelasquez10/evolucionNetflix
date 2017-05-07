// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public final class ee
{
    public static a e(final Object o) {
        return new a(o);
    }
    
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int hashCode(final Object... array) {
        return Arrays.hashCode(array);
    }
    
    public static final class a
    {
        private final List<String> pN;
        private final Object pO;
        
        private a(final Object o) {
            this.pO = eg.f(o);
            this.pN = new ArrayList<String>();
        }
        
        public a a(final String s, final Object o) {
            this.pN.add(eg.f(s) + "=" + String.valueOf(o));
            return this;
        }
        
        @Override
        public String toString() {
            final StringBuilder append = new StringBuilder(100).append(this.pO.getClass().getSimpleName()).append('{');
            for (int size = this.pN.size(), i = 0; i < size; ++i) {
                append.append(this.pN.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }
}
