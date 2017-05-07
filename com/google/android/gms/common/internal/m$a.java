// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class m$a
{
    private final List<String> LY;
    private final Object LZ;
    
    private m$a(final Object o) {
        this.LZ = n.i(o);
        this.LY = new ArrayList<String>();
    }
    
    public m$a a(final String s, final Object o) {
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
