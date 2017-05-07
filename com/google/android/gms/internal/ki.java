// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;

public class ki
{
    public static <T> boolean a(final List<T> list, final List<T> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!list2.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
}
