// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.List;

public class t
{
    public static <T> int a(final T t, final List<T> list) {
        int index;
        if (t == null) {
            index = -1;
        }
        else if ((index = list.indexOf(t)) < 0) {
            list.add(t);
            return list.size() - 1;
        }
        return index;
    }
}
