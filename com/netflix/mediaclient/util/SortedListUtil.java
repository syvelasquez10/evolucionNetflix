// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class SortedListUtil<T> extends ArrayList<T>
{
    private static final long serialVersionUID = 1L;
    
    public void insertSorted(final T t) {
        this.add(t);
        final Comparable comparable = (Comparable)t;
        for (int n = this.size() - 1; n > 0 && comparable.compareTo(this.get(n - 1)) < 0; --n) {
            Collections.swap(this, n, n - 1);
        }
    }
}
