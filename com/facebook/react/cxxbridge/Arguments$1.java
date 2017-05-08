// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import java.lang.reflect.Array;
import java.util.AbstractList;

final class Arguments$1 extends AbstractList
{
    final /* synthetic */ Object val$objects;
    
    Arguments$1(final Object val$objects) {
        this.val$objects = val$objects;
    }
    
    @Override
    public Object get(final int n) {
        return Array.get(this.val$objects, n);
    }
    
    @Override
    public int size() {
        return Array.getLength(this.val$objects);
    }
}
