// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public final class m
{
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static a h(final Object o) {
        return new a(o);
    }
    
    public static int hashCode(final Object... array) {
        return Arrays.hashCode(array);
    }
    
    public static final class a
    {
        private final List<String> LY;
        private final Object LZ;
        
        private a(final Object o) {
            this.LZ = n.i(o);
            this.LY = new ArrayList<String>();
        }
        
        public a a(final String s, final Object o) {
            this.LY.add(n.i(s) + "=" + String.valueOf(o));
            return this;
        }
        
        @Override
        public String toString() {
            final StringBuilder append = new StringBuilder(100).append(this.LZ.getClass().getSimpleName()).append('{');
            for (int size = this.LY.size(), i = 0; i < size; ++i) {
                append.append(this.LY.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }
}
