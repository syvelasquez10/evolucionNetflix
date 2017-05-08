// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.util.Collections;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public final class Sets
{
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<E>();
    }
    
    public static <E> Set<E> newIdentityHashSet() {
        return newSetFromMap(new IdentityHashMap<E, Boolean>());
    }
    
    public static <E> Set<E> newSetFromMap(final Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }
}
