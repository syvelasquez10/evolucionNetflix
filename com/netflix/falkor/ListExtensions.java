// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.ArrayList;
import java.util.List;

public class ListExtensions
{
    public static <T> List<T> sub(final List<T> list, final int n) {
        return sub(list, n, list.size() - n);
    }
    
    public static <T> List<T> sub(final List<T> list, final int n, final int n2) {
        final ArrayList<T> list2 = new ArrayList<T>();
        for (int i = n; i < n + n2; ++i) {
            list2.add(list.get(i));
        }
        return list2;
    }
}
